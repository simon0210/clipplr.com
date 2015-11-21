package com.clipplr.platform.exception.clip;

/**
 * Created by simon on 9/21/15.
 */
public class ContentDoesNotExist extends IllegalStateException {
    private static final long serialVersionUID = 7654422635384472777L;
    private Long clipID;

    public ContentDoesNotExist(Long clipID) {
        this.clipID = clipID;
    }

    @Override
    public String getMessage() {
        return String.format("Clip ID: {%s} does not exist!", clipID);
    }
}
