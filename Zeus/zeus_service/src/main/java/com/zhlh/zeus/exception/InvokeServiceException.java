package com.zhlh.zeus.exception;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/4 17:00
 * @comment
 */
public class InvokeServiceException extends Exception {
    private String code;

    public InvokeServiceException(String message) {
        super(message);
    }

    public InvokeServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public InvokeServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public InvokeServiceException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
