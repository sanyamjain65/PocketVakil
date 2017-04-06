package com.app.pocketvakil.networkclass;

/**
 * Created by Mohit on 08-01-2016.
 */
public class NetworkKeys {
    public static enum NET_KEY {
        QUESTION("Question"),
        ANSWER("Answer"),
        RESPONSE_STRING("response_str"),
        ERROR_CODE("error_code"),
        RESULT("result"),
        USER_FULL_NAME("user_full_name"),
        USER_PASSWORD("user_password"),
        USER_ID("user_id"),
        USER_EMAIL("user_email"),
        USER_OTP(""),
        PASSWORD("password"),
        TOKEN_TYPE("token_type"),
        NAME("name"),
        DETAILS("details"),
        FORGOT_PASSWORD_URL("forgot_password_url"),
        ID("id"),
        ACCESS_TOKEN("access-token"),
        USER_DEVICE_TOKEN("userDeviceToken"),
        NETWORK_ERROR("Network error"),
        USER_DEVICE_TYPE("userDeviceType");




        public final String KEY;
        NET_KEY(String key) {
            this.KEY = key;
        }

        public static String BASE_URL = "http://pocketvakil.applaurels.com/v1/";
        public static  String SIGNUP_URL=BASE_URL+"signup";
        public  static String LOGIN_IN_URL=BASE_URL+"login";
        public static String FORGOT_PASSWORD = BASE_URL + "forgotpass";
        public  static String RESET_PASSWORD_URL=BASE_URL+"resetpass";
        public  static String OTP_URL=BASE_URL+"otp";
        public  static String LOGOUT_URL=BASE_URL+"logout";
        public static String FAQ_URL=BASE_URL+"faq";
        public static String FAQ_URL_HINDI=BASE_URL+"faqhindi";
        public static String QUARY_URL=BASE_URL+"query";

    }
}


