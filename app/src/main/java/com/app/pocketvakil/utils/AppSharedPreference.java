package com.app.pocketvakil.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by appinventiv on 17/5/16.
 */
public class AppSharedPreference {

    public static enum PREF_KEY
    {
        HOME_ADRESS("home_address"),
        WORK_ADDRESS("work_address"),
        DAYS("days"),
        MyPREFERENCES  ("LogInInfo") ,
        HIT_COUNT("hit_count"),
        UserId ("user_id"),
        User_Email ("user_email"),
        User_Full_Name ("user_full_name"),
        Access_token ("access-token"),
        TIME_TO_OFFICE("time_to_office"),
        TIME_FROM_OFFICE("time_from_office"),
        TRAVEL_MODE("travel_mode"),
        NO_OF_PEOPLE("no_of_people"),
        OTP("otp"),
        TRANSIT_TYPE("transit_type"),
        TRANSIT_COST("transit_cost");

        public final String KEY;
        PREF_KEY(String key) {
            this.KEY = key;
        }
    }


    public static int getInt(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        int value = sharedPref.getInt(key.KEY, 0);
        return value;
    }

    public static void putInt(Context context, PREF_KEY key, int value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key.KEY, value);
        editor.commit();
    }

    public static void putLong(Context context, PREF_KEY key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key.KEY, value);
        editor.commit();
    }

    public static long getLong(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        long value = sharedPref.getLong(key.KEY, 0);
        return value;
    }

    public static void putString(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);
        editor.commit();
        editor.apply();
    }

    public static String getString(Context context, PREF_KEY key)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return  sharedPref.getString(key.KEY, null);
    }

    public static void putBoolean(Context context, PREF_KEY key, boolean value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key.KEY, value);
        editor.commit();
        editor.apply();
    }

    public static boolean getBoolean(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager .getDefaultSharedPreferences(context);
        return  sharedPref.getBoolean(key.KEY, false);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(key,null);
    }

    public static void clearAllPrefs(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("hit_count");
        editor.remove("user_id");
        editor.remove("user_email");
        editor.remove("user_full_name");
        editor.remove("access-token");
        editor.remove("otp");
        editor.commit();
    }
    public static void clearInt(Context context, PREF_KEY key)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key.KEY);
        editor.commit();
    }

}
