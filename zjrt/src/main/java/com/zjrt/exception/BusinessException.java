package com.zjrt.exception;

/**
 * 业务异常
 * Created by Apple on 2018/2/10.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -2780823944154925080L;
    private Long errorCode;//错误代码
    private String msg;//错误信息

    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    public BusinessException(String message, Long errorCode) {
        super(message);
        this.msg = message;
        this.errorCode = errorCode;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
