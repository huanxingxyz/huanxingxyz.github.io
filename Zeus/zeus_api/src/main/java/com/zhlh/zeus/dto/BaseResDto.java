package com.zhlh.zeus.dto;

import java.io.Serializable;

/**
 * @author liutianyuan
 * @version 1.0
 * @date 创建时间：16/4/13 17:17
 * @comment 基础Dto，封装code和msg
 */
public class BaseResDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer errCode = 0; // 响应编码，0表示正常，非0表示错误
    private String errMsg = ""; // 响应说明

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
