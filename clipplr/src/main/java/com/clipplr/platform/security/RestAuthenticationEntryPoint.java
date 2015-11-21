package com.clipplr.platform.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by simon on 6/5/15.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private AuthenticationFailureHandler authenticationExceptionHandler;

    public RestAuthenticationEntryPoint(AuthenticationFailureHandler handler) {
        this.authenticationExceptionHandler = handler;
    }

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException ex) throws IOException, ServletException {
        authenticationExceptionHandler.onAuthenticationFailure(request, response, ex);
    }
}
