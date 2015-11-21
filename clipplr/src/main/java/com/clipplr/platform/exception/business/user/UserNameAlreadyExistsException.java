package com.clipplr.platform.exception.business.user;

import com.clipplr.platform.exception.IllegalNamedArgumentException;

/**
 * Created by simon on 7/17/15.
 */
public class UserNameAlreadyExistsException extends IllegalNamedArgumentException {

    private static final long serialVersionUID = 395502089240780728L;

    public UserNameAlreadyExistsException(String useranme) {
        super("Username '" + useranme + "' already exists!");
    }
}
