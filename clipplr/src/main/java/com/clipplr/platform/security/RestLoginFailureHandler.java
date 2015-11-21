package com.clipplr.platform.security;

import com.clipplr.platform.exception.handler.RestResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by simon on 6/5/15.
 */
public class RestLoginFailureHandler extends RestResponseEntityExceptionHandler implements AccessDeniedHandler, AuthenticationFailureHandler {

    @Autowired
    public MappingJackson2HttpMessageConverter messageConverter;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ResponseEntity<Object> responseEntity = handleAccessDeniedException(accessDeniedException, new ServletWebRequest(request));
        handleResponse(response, responseEntity);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException,
            ServletException {
        ResponseEntity<Object> responseEntity = handleAuthenticationException(exception, new ServletWebRequest(request));
        handleResponse(response, responseEntity);
    }

    protected void handleResponse(HttpServletResponse response, ResponseEntity<Object> resbody) throws IOException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpResponse.setStatusCode(resbody.getStatusCode());
        httpResponse.getHeaders().putAll(resbody.getHeaders());
        messageConverter.write(resbody.getBody(), MediaType.APPLICATION_JSON, httpResponse);
    }
}
