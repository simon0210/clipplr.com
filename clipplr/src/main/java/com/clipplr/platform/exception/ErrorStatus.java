package com.clipplr.platform.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;



public class ErrorStatus {
    private int status;

    private int code;

    private Object message;

    private Object developerMessage;

    private Object moreInfo;

    @JsonIgnore
    private ServerErrorTraceInfo serverTraceInfo;

    public ErrorStatus() {
    }

    public ErrorStatus(HttpStatus httpStatus, int code, Object message, Object developerMessage, Object moreInfo) {
        this(httpStatus, code, message, developerMessage, moreInfo, null);
    }

    public ErrorStatus(HttpStatus httpStatus, int code, Object message, Object developerMessage, Object moreInfo, ServerErrorTraceInfo traceInfo) {
        this(httpStatus.value(), code, message, developerMessage, moreInfo, traceInfo);
    }

    public ErrorStatus(int status, int code, Object message, Object developerMessage, Object moreInfo) {
        this(status, code, message, developerMessage, moreInfo, null);
    }

    public ErrorStatus(int status, int code, Object message, Object developerMessage, Object moreInfo, ServerErrorTraceInfo traceInfo) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.moreInfo = moreInfo;
        this.serverTraceInfo = traceInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(Object developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Object getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(Object moreInfo) {
        this.moreInfo = moreInfo;
    }

    @JsonProperty("serverTraceInfo")
    public ServerErrorTraceInfo getServerTraceInfo() {
        return serverTraceInfo;
    }

    @JsonIgnore
    public void setServerTraceInfo(ServerErrorTraceInfo serverTraceInfo) {
        this.serverTraceInfo = serverTraceInfo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Status: ").append(status).append(", ");
        builder.append("Code: ").append(code).append(", ");
        builder.append("Message: ").append(message).append(", ");
        builder.append("Debug: ").append(developerMessage).append(", ");
        builder.append("Detail: ").append(moreInfo);
        return builder.toString();
    }

}
