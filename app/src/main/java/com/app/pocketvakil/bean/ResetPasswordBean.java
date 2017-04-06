package com.app.pocketvakil.bean;

/**
 * Created by appbulous on 24/5/16.
 */
public class ResetPasswordBean {



    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getResponseString() {
        return ResponseString;
    }

    public void setResponseString(String responseString) {
        ResponseString = responseString;
    }

    public  int ErrorCode;
    public int UserId;

        public String ResponseString;

}
