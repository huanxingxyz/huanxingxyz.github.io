package com.zhlh.zeus.exception;

public class RemexServiceException extends Exception {

    private String code;

    public RemexServiceException(String message) {
        super(message);
    }

    public RemexServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public RemexServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RemexServiceException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}