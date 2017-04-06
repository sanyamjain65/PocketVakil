package com.app.pocketvakil.networkclass;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.app.pocketvakil.bean.ForgotPasswordReponseBean;
import com.app.pocketvakil.bean.LogOutResponseBean;
import com.app.pocketvakil.bean.LoginResponseBean;
import com.app.pocketvakil.bean.OtpResponseBean;
import com.app.pocketvakil.bean.QuaryResponseBean;
import com.app.pocketvakil.bean.ResetPasswordBean;
import com.app.pocketvakil.bean.SingUpResponseBean;
import com.app.pocketvakil.parser.NetworkParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by appinventiv on 5/4/16.
 */
public class NetworkCall {

    private static NetworkCall instance = null;
    private NetworkParser parserResponse;
    private int responseCode;
    private String access_token;
   private String blank;
    private NetworkCall(Context context) {
        parserResponse = new NetworkParser(context.getApplicationContext());
    }

    public static NetworkCall getInstance(Context context) {
        if (instance == null)
            instance = new NetworkCall(context);
        return instance;
    }

    /**
     * Method to hit Login WEbservice
     **/
    public LoginResponseBean loginData(String requestURL, HashMap<String, String> postDataParameters,String blank) {
        String result = performPostCall(requestURL, postDataParameters,blank);
        return parserResponse.parseLoginData(result, responseCode);
    }

    /**
     * Method to hit SignUp WEbservice
     **/
    public SingUpResponseBean signupData(String requestURL, HashMap<String, String> postDataParameters,String blank) {
        String result = performPostCall(requestURL, postDataParameters,blank);
        Log.d("Response: ", "> " + result);

        return parserResponse.parseSignUpData(result, responseCode);
    }

    /**
     * Method to hit ForgotPassword WEbservice
     **/
    public ForgotPasswordReponseBean ForgotPwdData(String requestURL, HashMap<String, String> postDataParameters,String blank) {
        String result = performPostCall(requestURL, postDataParameters,blank);
        Log.d("Response: ", "> " + result);
        return parserResponse.parseForgotData(result, responseCode);
    }

    /**
     *
     Method to hit Reset Password WebService
     */
    public ResetPasswordBean resetData(String requestURL, HashMap<String, String> postDataParameters,String blank) {
        String result = performPostCall(requestURL, postDataParameters,blank);
        Log.d("Response: ", "> " + result);

        return parserResponse.parseResetData(result, responseCode);
    }

    /**
     * Method to hit Otp WEbservice
     **/
    public OtpResponseBean OtpData(String requestURL, HashMap<String, String> postDataParameters,String blank) {
        String result = performPostCall(requestURL, postDataParameters,blank);
        return parserResponse.parseOtpData(result, responseCode);
    }

    /**
     * Method to hit Otp WEbservice
     **/
    public LogOutResponseBean logoutData(String requestURL, HashMap<String, String> postDataParameters,String access_token) {
        String result = performPostCall(requestURL, postDataParameters,access_token);
        return parserResponse.parseLogOutData(result, responseCode);
    }

    /**
     * Method to hit Otp WEbservice
     **/
    public QuaryResponseBean quaryData(String requestURL, HashMap<String, String> postDataParameters,String access_token) {
        String result = performPostCall(requestURL, postDataParameters,access_token);
        return parserResponse.parseQuaryData(result, responseCode);
    }



    /**
     * Method to hit Login WEbservice
     **/
   /* public SignUpResponseBean signUpData(String requestURL, HashMap<String, String> postDataParameters) {
        String result = performPostCall(requestURL, postDataParameters);
        return parserResponse.parseSignUpData(result);

    }
*/
    /**
     * Generic method to hit connection with post method
     **/
    private String performPostCall(String requestURL, HashMap<String, String> postDataParams,String access_token) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            String api_key=access_token;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(25000);
            conn.setConnectTimeout(25000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

//            conn.addRequestProperty("Authorization", "Basic IHN1c3RhaW5tb2JpbGl0eS1hbmRyb2lkOg==");

            // for basic authentication
            if(api_key!="")
            {
                conn.addRequestProperty("access-token", api_key);
                String authentication = "pocketvakil"+":"+"12345!@#QWE";
                String encodedAuthentication = Base64.encodeToString(authentication.getBytes(), Base64.NO_WRAP);

                conn.setRequestProperty("Authorization", "Basic " + encodedAuthentication);
            }
            else if (api_key=="")
            {
                conn.addRequestProperty("api-key", "2994583b1183157b95131e9f656129e4");
                String authentication = "pocketvakil"+":"+"12345!@#QWE";
                String encodedAuthentication = Base64.encodeToString(authentication.getBytes(), Base64.NO_WRAP);

                conn.setRequestProperty("Authorization", "Basic " + encodedAuthentication);
            }



            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * Helper method to create connection
     **/
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();

    }
}
