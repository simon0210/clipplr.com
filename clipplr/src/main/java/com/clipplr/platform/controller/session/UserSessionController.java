package com.clipplr.platform.controller.session;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by simon on 4/22/15.
 */
@RestController
@RequestMapping(value = "/api/users/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserSessionController {

    @ApiOperation(value = "Acquire a session for an operator. It means, you can access using this session.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.TEMPORARY_REDIRECT)
    public void userSignIn(
            @ApiParam(required = true, value = "Operator account name for a session.") @RequestParam String username,
            @ApiParam(required = true, value = "Password for a session.") @RequestParam String password
    ) {
        throw new UnsupportedOperationException("Temporarily Unavailable.");
    }

    @ApiOperation(value = "Remove current session. It means you can't access using session anymore.")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.TEMPORARY_REDIRECT)
    public void userSignOut() {
        throw new UnsupportedOperationException("Temporarily Unavailable.");
    }

}
