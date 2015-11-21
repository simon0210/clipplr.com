package com.clipplr.platform.exception.handler;

import com.clipplr.platform.exception.business.user.UserPasswordDoesNotMatchException;
import com.clipplr.platform.exception.clip.ContentDoesNotExist;
import com.clipplr.platform.exception.transaction.NotEnoughRubyException;
import com.clipplr.platform.exception.transaction.RewardAlreadyExistException;
import com.clipplr.platform.exception.ErrorMessageEnum;
import com.clipplr.platform.exception.ErrorStatus;
import com.clipplr.platform.exception.ServerErrorTraceInfo;
import com.clipplr.platform.exception.business.user.UserNameAlreadyExistsException;
import com.clipplr.platform.exception.transaction.NotEnoughChipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;


/**
 * Created by simon on 4/19/15.
 */
@SuppressWarnings("deprecation")
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);


    public RestResponseEntityExceptionHandler() {
        super();
    }

    // 400
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus httpStatus, final WebRequest request) {
        return handleExceptionInternal(ex, request, HttpStatus.BAD_REQUEST, ErrorMessageEnum.INVALID_PARAMETER.ordinal());
    }

    // 401
    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentialException(final BadCredentialsException ex, final WebRequest request) {
        return handleExceptionInternal(ex, request, HttpStatus.UNAUTHORIZED, ErrorMessageEnum.BAD_CREDENTIAL.getValue());
    }

    // 401
    @ExceptionHandler({AuthenticationException.class})
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex, final WebRequest request) {
        if (ex instanceof BadCredentialsException) {
            String developerMessage = "Invalid credential";
            if (ex.getMessage() != null) {
                developerMessage += " of user " + ex.getMessage();
            }
            Object detail = null;
            return handleExceptionInternal(ex, request, HttpStatus.UNAUTHORIZED, ErrorMessageEnum.BAD_CREDENTIAL.getValue(), null, developerMessage, detail);
        } else if (ex instanceof LockedException) {
            return handleExceptionInternal(ex, request, HttpStatus.UNAUTHORIZED, ErrorMessageEnum.ACCOUNT_LOCKED.getValue());
        } else if (ex instanceof AuthenticationServiceException) {
            for (Throwable e = ex; e != null; e = e.getCause()) {
                if (e instanceof UsernameNotFoundException) {
                    return handleExceptionInternal(ex, request, HttpStatus.UNAUTHORIZED, ErrorMessageEnum.INVALID_USERNAME.getValue());
                }
            }
        }

        return handleExceptionInternal(ex, request, HttpStatus.UNAUTHORIZED, ErrorMessageEnum.ACCESS_DENIED.getValue());
    }

    // 403
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {
        return handleExceptionInternal(ex, request, new HttpHeaders(), HttpStatus.FORBIDDEN, ErrorMessageEnum.ACCESS_DENIED.getValue(), ex.getMessage(), null, null);
    }

    @ExceptionHandler({UserPasswordDoesNotMatchException.class})
    protected ResponseEntity<Object> handleUserPasswordDoesNotMatchException(final RuntimeException ex, final WebRequest request) {
        UserPasswordDoesNotMatchException userPasswordDoesNotMatchException = (UserPasswordDoesNotMatchException) ex;
        return handleExceptionInternal(ex, request, HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessageEnum.BAD_CREDENTIAL.getValue(), userPasswordDoesNotMatchException.getMessage(), null, null);
    }

    @ExceptionHandler({UserNameAlreadyExistsException.class})
    protected ResponseEntity<Object> handleUserNameAlreadyExistsException(final RuntimeException ex, final WebRequest request) {
        UserNameAlreadyExistsException userNameAlreadyExistsException = (UserNameAlreadyExistsException) ex;
        return handleExceptionInternal(ex, request, HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getValue(), userNameAlreadyExistsException.getMessage(), null, null);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
        String developerMessage = ex.getConstraintViolations().toString();
        String message = "" + ex.getConstraintViolations().size() + " arguments are invalid";
        return handleExceptionInternal(ex, request, HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessageEnum.INVALID_PARAMETER.getValue(), message, developerMessage, null);
    }

    @ExceptionHandler({ContentDoesNotExist.class})
    protected ResponseEntity<Object> handleNotEnoughRubyException(final RuntimeException ex, final WebRequest request) {
        ContentDoesNotExist contentDoesNotExist = (ContentDoesNotExist) ex;
        return handleExceptionInternal(ex, request, HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessageEnum.CONTENT_DOES_NOT_EXIST.getValue(), contentDoesNotExist.getMessage(), null, null);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String developerMessage = "Invalid property type of property " + ex.getPropertyName() + " because " + ex.getMostSpecificCause();
        String message = ex.getPropertyName();
        ErrorMessageEnum errorEnum = ErrorMessageEnum.INVALID_PARAMETER_TYPE;

//        for (Throwable e = ex; e != null; e = e.getCause()) {
//
//            if (e instanceof PartnerNotFoundException) { // argument 로 들어온 partner code conversion 실패시
//                message = e.getMessage();
//                errorEnum = ErrorMessageEnum.PARTNER_NOT_FOUND;
//
//                break;
//            }
//
//        }

        if (StringUtils.isEmpty(message)) {
            message = ex.getRequiredType().getSimpleName();
        }
        HashMap<String, Object> detail = new HashMap<>();
        detail.put("value", ex.getValue());
        return handleExceptionInternal(ex, request, HttpStatus.BAD_REQUEST, errorEnum.ordinal(), message, developerMessage, detail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus httpStatus, final WebRequest request) {
        return handleExceptionInternal(ex, request, HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessageEnum.INVALID_PARAMETER.getValue());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object bodyOfResponse, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.debug("Handled error {}\nRequest: {}", bodyOfResponse, request, ex);

        if (bodyOfResponse == null) {
            bodyOfResponse = new ErrorStatus(status, ErrorMessageEnum.INVALID_REQUEST.ordinal(), ex.getMessage(), null, null);
        }

        return super.handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final WebRequest request, HttpStatus status, int error) {
        return handleExceptionInternal(ex, request, new HttpHeaders(), status, error, ex.getMessage(), ex.getMessage(), null);
    }

    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final WebRequest request, HttpStatus status, int error, String message, String developerMessage, Object detail) {
        return handleExceptionInternal(ex, request, new HttpHeaders(), status, error, message, developerMessage, detail);
    }

    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final WebRequest request, HttpHeaders headers, HttpStatus status, int error, String message, String developerMessage, Object detail) {
        ServerErrorTraceInfo traceInfo = null;
        String pragma = request.getHeader("Pragma");
        if (HttpStatus.INTERNAL_SERVER_ERROR == status || (pragma != null && pragma.contains("server-trace"))) {
            traceInfo = new ServerErrorTraceInfo(ex);
        }
        ErrorStatus resbody = new ErrorStatus(status, error, message, developerMessage, detail, traceInfo);
        return handleExceptionInternal(ex, resbody, headers, status, request);
    }

}
