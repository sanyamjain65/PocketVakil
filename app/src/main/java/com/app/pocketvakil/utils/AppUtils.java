package com.app.pocketvakil.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by appinventiv on 11/5/16.
 */
public class AppUtils extends Application {
    public static final String MyPREFERENCES = "LogInInfo" ;
    public static final String UserId = "IdKey";
    public static final String User_Email = "mailKey";
    public static final String User_Full_Name = "fullnameKey";
    public static final String Access_token = "tokenKey";
    public static AppUtils appUtils;
    static SharedPreferences.Editor editor;
    private static SharedPreferences sharedpreferences;
    //SharedPreferences sharedpreferences;

    /**
     * this method is used to hide keyboard
     */

    public static void hideKeyBoard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            //	e.printStackTrace();
        }
    }

    public static void showSnackBar(View v,String message){
        Snackbar snackbar = Snackbar
                .make(v, message, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appUtils=this;
       sharedpreferences = getBaseContext().getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        editor=sharedpreferences.edit();
        //SharedPreferences.Editor editor = sharedpreferences.edit();
    }

    public static SharedPreferences getPreferences()
    {
        if (sharedpreferences == null)
            sharedpreferences =appUtils.getBaseContext().getSharedPreferences(MyPREFERENCES, Context.MODE_MULTI_PROCESS);
        return sharedpreferences;
    }
    public static SharedPreferences.Editor editor()
    {
        if (editor == null) editor = getPreferences().edit();
        return editor;
    }


   /* public static void LogInData(int userId,String user_email,String user_full_name,String access_token,Context context)
    {
        //SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(UserId, userId);
        editor.putString(User_Email, user_email);
        editor.putString(User_Full_Name, user_full_name);
        editor.putString(Access_token,access_token);
        editor.commit();
    }*/
    /*public static void ForgotData(int userId,Context context)
    {

       // editor = sharedpreferences.edit();
        //SharedPreferences.Editor editor = prefs.edit();
        editor().putInt(UserId, userId).commit();
     //   editor.commit();
    }*/

    public static int GetForgotData(String key,Context context)

    {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
       return prefs.getInt(key,0);
    }
}
