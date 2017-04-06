package com.app.pocketvakil.bean;

/**
 * Created by appbulous on 25/5/16.
 */
public class OtpResponseBean {
    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getResponseString() {
        return ResponseString;
    }

    public void setResponseString(String responseString) {
        ResponseString = responseString;
    }

    public  int ErrorCode;
    public String ResponseString;
}
