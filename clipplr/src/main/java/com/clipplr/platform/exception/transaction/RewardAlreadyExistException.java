package com.clipplr.platform.exception.transaction;

import com.clipplr.platform.exception.IllegalNamedArgumentException;

/**
 * Created by simon on 9/21/15.
 */
public class RewardAlreadyExistException extends IllegalNamedArgumentException {


    public RewardAlreadyExistException(String username) {
        super("Username '" + username + "' already get tutorial rewards!");
    }

}
