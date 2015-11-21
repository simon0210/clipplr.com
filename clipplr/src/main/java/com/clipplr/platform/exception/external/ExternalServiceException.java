package com.clipplr.platform.exception.external;

/**
 * Created by simon on 10/16/15.
 */
public class ExternalServiceException extends RuntimeException {

    private static final long serialVersionUID = -681292390903267612L;

    public ExternalServiceException() {
    }

    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(Throwable cause) {
        super(cause);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
