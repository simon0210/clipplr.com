package com.clipplr.platform.exception.business.user;

import com.clipplr.platform.exception.IllegalNamedArgumentException;

/**
 * Created by simon on 7/17/15.
 */
public class UserPasswordDoesNotMatchException extends IllegalNamedArgumentException {


    private static final long serialVersionUID = -6118756353154145020L;

    public UserPasswordDoesNotMatchException(String password) {
        super("Password: '" + password + "' does not match!");
    }

}
