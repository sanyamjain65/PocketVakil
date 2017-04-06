package com.app.pocketvakil.bean;

/**
 * Created by appbulous on 24/5/16.
 */
public class ForgotPasswordReponseBean {
    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public  int ErrorCode;

    public String getResponseString() {
        return ResponseString;
    }

    public void setResponseString(String responseString) {
        ResponseString = responseString;
    }

    String ResponseString;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int UserId;

}
