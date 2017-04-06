package com.app.pocketvakil.bean;

/**
 * Created by appbulous on 24/5/16.
 */
public class LoginResponseBean {

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

    int ErrorCode; String ResponseString;
}
