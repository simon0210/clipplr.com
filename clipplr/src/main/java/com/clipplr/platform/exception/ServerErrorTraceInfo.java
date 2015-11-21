package com.clipplr.platform.exception;

/**
 * Created by simon on 6/5/15.
 */
public class ServerErrorTraceInfo {
    public String exceptionMessage;
    public StackTraceElement[] stack;

    public ServerErrorTraceInfo(Throwable t) {
        if (t == null) {
            exceptionMessage = "Invalid";
            stack = new Throwable().fillInStackTrace().getStackTrace();
        } else {
            Throwable cause = t.getCause();
            if (cause != null) {
                exceptionMessage = t.getMessage() + " by " + cause.getMessage();
            } else {
                exceptionMessage = t.getMessage();
            }
            stack = t.getStackTrace();
        }
    }
}
