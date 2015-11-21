package com.clipplr.platform.security;

import javax.servlet.http.HttpServletRequest;

public class WebAuthenticationDetails extends org.springframework.security.web.authentication.WebAuthenticationDetails {
    private static final long serialVersionUID = 571050481925663659L;

    public WebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        address = request.getRemoteAddr();
    }

    private final String address;

    public String getAddress() {
        return address;
    }

}
