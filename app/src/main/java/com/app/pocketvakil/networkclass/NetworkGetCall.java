package com.app.pocketvakil.networkclass;

import android.content.Context;
import android.util.Base64;

import com.app.pocketvakil.bean.FaqResponseBean;
import com.app.pocketvakil.parser.NetworkParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by appbulous on 26/5/16.
 */
public class NetworkGetCall {

    private static NetworkGetCall instance = null;
    private NetworkParser parserResponse;
    private int responseCode;
    private String access_token;
    private String blank;
    private NetworkGetCall(Context context) {
        parserResponse = new NetworkParser(context.getApplicationContext());
    }

    public static NetworkGetCall getInstance(Context context) {
        if (instance == null)
            instance = new NetworkGetCall(context);
        return instance;
    }



    /**
     * Method to hit FAQ WEbservice
     **/
    public FaqResponseBean faqData(String requestURL)
    {
        String result = hitGetFaqData(requestURL);
        return parserResponse.parseFaqDataData(result, responseCode);
    }


    private String performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            String api_key=access_token;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(25000);
            conn.setConnectTimeout(25000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

//            conn.addRequestProperty("Authorization", "Basic IHN1c3RhaW5tb2JpbGl0eS1hbmRyb2lkOg==");

            // for basic authentication


                conn.setRequestProperty("api-key", "2994583b1183157b95131e9f656129e4");
                String authentication = "pocketvakil"+":"+"12345!@#QWE";
                String encodedAuthentication = Base64.encodeToString(authentication.getBytes(), Base64.NO_WRAP);

                conn.setRequestProperty("Authorization", "Basic " + encodedAuthentication);


            responseCode = conn.getResponseCode();
            /*OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
           // writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();*/
            //os.close();
            //responseCode = conn.getResponseCode();

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

    public String hitGetFaqData(String url) {
        String response = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("api-key", "2994583b1183157b95131e9f656129e4");
            String authentication = "pocketvakil"+":"+"12345!@#QWE";
            String encodedAuthentication = Base64.encodeToString(authentication.getBytes(), Base64.NO_WRAP);

            connection.setRequestProperty("Authorization", "Basic " + encodedAuthentication);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuilder mResponse = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    mResponse.append(line + "\n");
                }
                br.close();
                response = mResponse.toString();
                return response;

            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
