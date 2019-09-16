package com.namcooper.mvp_master.http;

/**
 * Project: MvpDemo
 * Author:  NamCooper
 * Version:  1.0
 * Date:    2019/8/9
 * Copyright notice:
 */
public class ApiException{
    private int errCode;
    private String errMsg;

    public ApiException(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
