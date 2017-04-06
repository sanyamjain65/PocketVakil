package com.app.pocketvakil.parser;

import android.content.Context;

import com.app.pocketvakil.bean.FaqResponseBean;
import com.app.pocketvakil.bean.ForgotPasswordReponseBean;
import com.app.pocketvakil.bean.LogOutResponseBean;
import com.app.pocketvakil.bean.LoginResponseBean;
import com.app.pocketvakil.bean.OtpResponseBean;
import com.app.pocketvakil.bean.QuaryResponseBean;
import com.app.pocketvakil.bean.ResetPasswordBean;
import com.app.pocketvakil.bean.SingUpResponseBean;
import com.app.pocketvakil.networkclass.NetworkKeys;
import com.app.pocketvakil.preference.SharedPreference;
import com.app.pocketvakil.utils.AppSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appinventiv on 5/4/16.
 */
public class NetworkParser {

    private String responseString;
    private int error_code_forgot;
    private int errorCode;
    private Context context;
    public int userId;
    private List ques;
    public SharedPreference sharedPreference;

    public String user_email,user_full_name,access_token;




    public NetworkParser(Context context) {
        this.context = context;
    }



    /*
    *  * Method to parse login data*/
    public LoginResponseBean parseLoginData(String response, int statusCode) {

        LoginResponseBean loginResponseBean = new LoginResponseBean();

        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                errorCode=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);
                if(errorCode==0)
                {
                    JSONObject obj=object.getJSONObject(NetworkKeys.NET_KEY.RESULT.KEY);
                    userId=obj.getInt(NetworkKeys.NET_KEY.USER_ID.KEY);
                    user_email=obj.getString(NetworkKeys.NET_KEY.USER_EMAIL.KEY);
                    user_full_name=obj.getString(NetworkKeys.NET_KEY.USER_FULL_NAME.KEY);
                    access_token=obj.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);


                    AppSharedPreference.putInt(context, AppSharedPreference.PREF_KEY.UserId,userId);
                    AppSharedPreference.putString(context, AppSharedPreference.PREF_KEY.User_Email, user_email);
                    AppSharedPreference.putString(context, AppSharedPreference.PREF_KEY.User_Full_Name, user_full_name);
                    AppSharedPreference.putString(context, AppSharedPreference.PREF_KEY.Access_token, access_token);





                    loginResponseBean.setErrorCode(errorCode);
                    loginResponseBean.setResponseString(responseString);
                }
                else
                {
                    loginResponseBean.setErrorCode(errorCode);
                    loginResponseBean.setResponseString(responseString);

                }


            }

            else
            {
                loginResponseBean.setErrorCode(errorCode);
                loginResponseBean.setResponseString(responseString);



            }
        }

        catch (JSONException e) {
            loginResponseBean.setErrorCode(100);
            loginResponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return loginResponseBean;
    }



    public SingUpResponseBean parseSignUpData(String response, int statusCode) {

        SingUpResponseBean singUpResponseBean = new SingUpResponseBean();

        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                errorCode=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);

                /*userId=obj.getInt(NetworkKeys.NET_KEY.USER_ID.KEY);
                user_email=obj.getString(NetworkKeys.NET_KEY.USER_EMAIL.KEY);
                user_full_name=obj.getString(NetworkKeys.NET_KEY.USER_FULL_NAME.KEY);
                access_token=obj.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
*/                singUpResponseBean.setErrorCode(errorCode);
                singUpResponseBean.setResponseString(responseString);
                if (errorCode == 0) {

                   /* "access_token": "w3HPKOodd6qzmvu7aScjJVs3On0ZsgqDvMTJtdUs",
                            "token_type": "Bearer",
                            "expires_in": 36000,
                            "partners": {
                        "id": "1"
                    /*String access_token = object.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
                    String token_type = object.getString(NetworkKeys.NET_KEY.TOKEN_TYPE.KEY);
                    JSONObject partners = object.getJSONObject(NetworkKeys.NET_KEY.USER_DEVICE_TOKEN.KEY);
                    String id = object.getString(NetworkKeys.NET_KEY.ID.KEY);

                    loginResponseBean.setAccess_token(access_token);
                    loginResponseBean.setId(id);
                    loginResponseBean.setToken_type(token_type);*/
//                    loginResponseBean.setUser(deviceToken);

                }
            }else {
                singUpResponseBean.setErrorCode(errorCode);
                singUpResponseBean.setResponseString(responseString);



            }
        } catch (JSONException e) {
            singUpResponseBean.setErrorCode(100);
            singUpResponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return singUpResponseBean;
    }







    public ForgotPasswordReponseBean parseForgotData(String response, int statusCode)

    {
        ForgotPasswordReponseBean forgotPasswordReponseBean=new ForgotPasswordReponseBean();
        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                error_code_forgot=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);

                if(error_code_forgot==0)
                {
                    JSONObject jobj=object.getJSONObject(NetworkKeys.NET_KEY.RESULT.KEY);
                    sharedPreference=SharedPreference.getInstance(context);
                    int fogot_user_id=jobj.getInt(NetworkKeys.NET_KEY.USER_ID.KEY);
                    AppSharedPreference.putInt(context, AppSharedPreference.PREF_KEY.UserId,fogot_user_id);
                    forgotPasswordReponseBean.setErrorCode(error_code_forgot);
                    forgotPasswordReponseBean.setResponseString(responseString);
                    forgotPasswordReponseBean.setUserId(fogot_user_id);

                }
                else

                {
                    forgotPasswordReponseBean.setErrorCode(error_code_forgot);
                    forgotPasswordReponseBean.setResponseString(responseString);




                }


            }


        } catch (JSONException e) {
            forgotPasswordReponseBean.setErrorCode(100);
            forgotPasswordReponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return forgotPasswordReponseBean;
    }

    public ResetPasswordBean parseResetData(String response, int statusCode)

    {
        ResetPasswordBean resetPasswordBean=new ResetPasswordBean();
        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                error_code_forgot=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);

                //JSONObject obj=object.getJSONObject("result");
               /* userId=obj.getInt("user_id");
                user_email=obj.getString("user_email");
                user_full_name=obj.getString("user_full_name");
                access_token=obj.getString("access-token");
                AppUtils.LogInData(userId,user_email,user_full_name,access_token,context);*/
                resetPasswordBean.setErrorCode(error_code_forgot);
                resetPasswordBean.setResponseString(responseString);
                //resetPasswordBean.setErrorCode(error_code_forgot);
                //resetPasswordBean.setUserId(userId);
                if (errorCode == 0) {

                   /* "access_token": "w3HPKOodd6qzmvu7aScjJVs3On0ZsgqDvMTJtdUs",
                            "token_type": "Bearer",
                            "expires_in": 36000,
                            "partners": {
                        "id": "1"
                    /*String access_token = object.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
                    String token_type = object.getString(NetworkKeys.NET_KEY.TOKEN_TYPE.KEY);
                    JSONObject partners = object.getJSONObject(NetworkKeys.NET_KEY.USER_DEVICE_TOKEN.KEY);
                    String id = object.getString(NetworkKeys.NET_KEY.ID.KEY);

                    loginResponseBean.setAccess_token(access_token);
                    loginResponseBean.setId(id);
                    loginResponseBean.setToken_type(token_type);*/
//                    loginResponseBean.setUser(deviceToken);

                }
            }else {
                resetPasswordBean.setErrorCode(error_code_forgot);
                resetPasswordBean.setResponseString(responseString);
               // resetPasswordBean.setUserId(userId);



            }
        } catch (JSONException e) {
            resetPasswordBean.setErrorCode(100);
            resetPasswordBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return resetPasswordBean;
    }


    public OtpResponseBean parseOtpData(String response, int statusCode)

    {
        OtpResponseBean otpResponseBean=new OtpResponseBean();
        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                error_code_forgot=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);

                //JSONObject obj=object.getJSONObject("result");
               /* userId=obj.getInt("user_id");
                user_email=obj.getString("user_email");
                user_full_name=obj.getString("user_full_name");
                access_token=obj.getString("access-token");
                AppUtils.LogInData(userId,user_email,user_full_name,access_token,context);*/
                otpResponseBean.setErrorCode(error_code_forgot);
                otpResponseBean.setResponseString(responseString);
                //resetPasswordBean.setErrorCode(error_code_forgot);
                //resetPasswordBean.setUserId(userId);
                if (errorCode == 0) {

                   /* "access_token": "w3HPKOodd6qzmvu7aScjJVs3On0ZsgqDvMTJtdUs",
                            "token_type": "Bearer",
                            "expires_in": 36000,
                            "partners": {
                        "id": "1"
                    /*String access_token = object.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
                    String token_type = object.getString(NetworkKeys.NET_KEY.TOKEN_TYPE.KEY);
                    JSONObject partners = object.getJSONObject(NetworkKeys.NET_KEY.USER_DEVICE_TOKEN.KEY);
                    String id = object.getString(NetworkKeys.NET_KEY.ID.KEY);

                    loginResponseBean.setAccess_token(access_token);
                    loginResponseBean.setId(id);
                    loginResponseBean.setToken_type(token_type);*/
//                    loginResponseBean.setUser(deviceToken);

                }
            }else {
                otpResponseBean.setErrorCode(error_code_forgot);
                otpResponseBean.setResponseString(responseString);
                // resetPasswordBean.setUserId(userId);



            }
        } catch (JSONException e) {
            otpResponseBean.setErrorCode(100);
            otpResponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return otpResponseBean;
    }





    /*
  *  * Method to parse login data*/
    public LogOutResponseBean parseLogOutData(String response, int statusCode) {

        LogOutResponseBean logOutResponseBean = new LogOutResponseBean();

        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                errorCode=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);
                logOutResponseBean.setErrorCode(errorCode);
                logOutResponseBean.setResponseString(responseString);
                if (errorCode == 0) {

                   /* "access_token": "w3HPKOodd6qzmvu7aScjJVs3On0ZsgqDvMTJtdUs",
                            "token_type": "Bearer",
                            "expires_in": 36000,
                            "partners": {
                        "id": "1"
                    /*String access_token = object.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
                    String token_type = object.getString(NetworkKeys.NET_KEY.TOKEN_TYPE.KEY);
                    JSONObject partners = object.getJSONObject(NetworkKeys.NET_KEY.USER_DEVICE_TOKEN.KEY);
                    String id = object.getString(NetworkKeys.NET_KEY.ID.KEY);

                    loginResponseBean.setAccess_token(access_token);
                    loginResponseBean.setId(id);
                    loginResponseBean.setToken_type(token_type);*/
//                    loginResponseBean.setUser(deviceToken);

                }
            }else {
                logOutResponseBean.setErrorCode(errorCode);
                logOutResponseBean.setResponseString(responseString);



            }
        } catch (JSONException e) {
            logOutResponseBean.setErrorCode(100);
            logOutResponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return logOutResponseBean;
    }




    public FaqResponseBean parseFaqDataData(String response, int statusCode) {

        FaqResponseBean faqResponseBean = new FaqResponseBean();
        List<FaqResponseBean> faqList = new ArrayList<FaqResponseBean>();


        try {
            if (response!="") {
                JSONObject jsonObject=new JSONObject(response);
                JSONArray jsonArray=jsonObject.getJSONArray(NetworkKeys.NET_KEY.RESULT.KEY);

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);


                    FaqResponseBean bean = new FaqResponseBean();

                    String answer = jsonobject.getString(NetworkKeys.NET_KEY.ANSWER.KEY);
                    String question = jsonobject.getString(NetworkKeys.NET_KEY.QUESTION.KEY);

                    bean.setQuestion(question);
                    bean.setAnswer(answer);
                    faqList.add(bean);
                }
                faqResponseBean.setFaqList(faqList);
            }

            else
            {
                faqResponseBean.setErrorCode(100);
                faqResponseBean.setResponseString("Network Error");
            }


        }
        catch (JSONException e) {

            e.printStackTrace();
        }
        return faqResponseBean;
    }



    /*
*  * Method to parse quary data*/
    public QuaryResponseBean parseQuaryData(String response, int statusCode) {

        QuaryResponseBean quaryResponseBean = new QuaryResponseBean();

        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);

                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                errorCode=object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);
                quaryResponseBean.setErrorCode(errorCode);
                quaryResponseBean.setResponseString(responseString);
                if (errorCode == 0) {

                   /* "access_token": "w3HPKOodd6qzmvu7aScjJVs3On0ZsgqDvMTJtdUs",
                            "token_type": "Bearer",
                            "expires_in": 36000,
                            "partners": {
                        "id": "1"
                    /*String access_token = object.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);
                    String token_type = object.getString(NetworkKeys.NET_KEY.TOKEN_TYPE.KEY);
                    JSONObject partners = object.getJSONObject(NetworkKeys.NET_KEY.USER_DEVICE_TOKEN.KEY);
                    String id = object.getString(NetworkKeys.NET_KEY.ID.KEY);

                    loginResponseBean.setAccess_token(access_token);
                    loginResponseBean.setId(id);
                    loginResponseBean.setToken_type(token_type);*/
//                    loginResponseBean.setUser(deviceToken);

                }
            }else {
                quaryResponseBean.setErrorCode(errorCode);
                quaryResponseBean.setResponseString(responseString);



            }
        } catch (JSONException e) {
            quaryResponseBean.setErrorCode(100);
            quaryResponseBean.setResponseString(NetworkKeys.NET_KEY.NETWORK_ERROR.KEY);
            e.printStackTrace();
        }
        return quaryResponseBean;
    }


    /*
    *  * Method to parse signup data*/
   /* public SignUpResponseBean parseSignUpData(String response) {
        SignUpResponseBean signUpResponseBean = new SignUpResponseBean();

        try {
            if (response!=null) {
                JSONObject object = new JSONObject(response);
                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
                errorCode = object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);
                signUpResponseBean.setErrorCode(errorCode);
                signUpResponseBean.setResponseString(responseString);
                if (errorCode == 0) {
                    JSONObject result = object.getJSONObject(NetworkKeys.NET_KEY.RESULT.KEY);

                    String user_id = result.getString(NetworkKeys.NET_KEY.USER_ID.KEY);
                    String user_email = result.getString(NetworkKeys.NET_KEY.USER_EMAIL.KEY);
                    String accessToken = result.getString(NetworkKeys.NET_KEY.ACCESS_TOKEN.KEY);

                    signUpResponseBean.setUser_id(user_id);
                    signUpResponseBean.setUserEmail(user_email);
                    signUpResponseBean.setAccess_token(accessToken);

                }
            }else {
                signUpResponseBean.setErrorCode(errorCode);
                signUpResponseBean.setResponseString(responseString);
            }
        } catch (JSONException e) {
            signUpResponseBean.setErrorCode(100);
            signUpResponseBean.setResponseString("Network error");
            e.printStackTrace();
        }
        return signUpResponseBean;
    }*/

    /*public PartnerResponseBean parseClientProfileData(String response, int errorCode) {
        PartnerResponseBean bean=new PartnerResponseBean();
        try {
            if (response!=null){
                JSONObject object = new JSONObject(response);
//                responseString = object.getString(NetworkKeys.NET_KEY.RESPONSE_STRING.KEY);
//                errorCode = object.getInt(NetworkKeys.NET_KEY.ERROR_CODE.KEY);
                bean.setErrorCode(errorCode);
//                bean.setResponseString(responseString);
                if (errorCode == 200) {

                    String message = object.getString(NetworkKeys.NET_KEY.MESSAGE.KEY);
                    *//*JSONObject data = object.getJSONObject(NetworkKeys.NET_KEY.DATA.KEY);
                    String partener_id = data.getString(NetworkKeys.NET_KEY.PARTENER_ID.KEY);
                    String name = data.getString(NetworkKeys.NET_KEY.NAME.KEY);
                    String details= data.ge
                    String title = user_data.getString(NetworkKeys.NET_KEY.TITLE.KEY);
                    String forgot_password_url = user_data.optString(NetworkKeys.NET_KEY.FORGOT_PASSWORD_URL.KEY);

                    bean.setAccessToken(accessToken);
                    bean.setDeviceToken(deviceToken);
                    bean.setLoginStatus(loginStatus);
                    bean.setUserName(userName);
                    bean.setUserAddress(userAddress);
                    bean.setNickName(nickName);
                    bean.setHairType(hairType);
                    bean.setExtraInfo(extraInfo);*//*
                    bean.setMessage(message);
                }
            }else {
                bean.setErrorCode(errorCode);
                bean.setResponseString(responseString);
            }

        } catch (JSONException e) {
            bean.setErrorCode(100);
            bean.setResponseString("Network error");

            e.printStackTrace();
        }

        return bean;

    }*/

}