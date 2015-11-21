package com.clipplr.platform.exception;

/**
 * Created by simon on 6/27/15.
 */
public class IllegalNamedArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 8346160079538946056L;

    private String argumentName = null;

    public IllegalNamedArgumentException(String message) {
        super(message);
    }

    public IllegalNamedArgumentException(String message, String argumentName) {
        super(message);
        this.argumentName = argumentName;
    }

    public String getArgumentName() {
        if (argumentName == null) {
            String className = getClass().getSimpleName();
            if (className.endsWith("NotFoundException")) {
                argumentName = className.substring(0, className.length() - "NotFoundException".length());
            }
        }
        return argumentName;
    }
}
