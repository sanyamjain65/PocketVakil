package com.app.pocketvakil.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by appbulous on 25/5/16.
 */
public class SharedPreference {
    private SharedPreferences preferences;
    private static   SharedPreferences.Editor prefsEditor;
    private static SharedPreference instance;
    public static final String MyPREFERENCES = "LogInInfo" ;
    public static final String UserId = "IdKey";

    private SharedPreference() {
    }

    private SharedPreference(Context context) {
        preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        prefsEditor = preferences.edit();
    }
    public static SharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreference(context);
        }
        return instance;
    }
    public static void ForgotData(int userId,Context context)
    {

        // editor = sharedpreferences.edit();
        //SharedPreferences.Editor editor = prefs.edit();
                //   editor.commit();
        prefsEditor.putInt(UserId, userId);
        prefsEditor.commit();

    }

}
