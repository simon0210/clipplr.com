package com.clipplr.platform.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by simon on 15. 6. 25.
 */
public class WebAuthenticationDetailsSource extends org.springframework.security.web.authentication.WebAuthenticationDetailsSource {

    @Override
    public org.springframework.security.web.authentication.WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new WebAuthenticationDetails(request);
    }
}