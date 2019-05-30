package com.example.amir.rehave.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static String TYPE_PREF="type";
    public static String ADMIN_TYPE="Admin";
    public static String NAME_PREF="name";
    public static String ID_PREF="id";
    public static String DATE_PREF="date";
    public static String USER_TYPE="JSONPost";
    public static String IS_NOT_INTIAL_LUNCH="is_not_initial_lunch";
    public static String TOTAL_PREF="total";

    private SharedPreferences sharedPreferences;

    private static SharedPrefManager sharedPrefManager;

    private static final String SHARED_PREF_NAME = "rehubPreference";

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (sharedPrefManager == null) {
            sharedPrefManager = new SharedPrefManager(context);
        }
        return sharedPrefManager;
    }

    public void setString(String name,String value){
        sharedPreferences.edit().putString(name, value).commit();
    }

    public String getString(String name){

        return sharedPreferences.getString(name, null);
    }

    public void setInt(String name,int value){
        sharedPreferences.edit().putInt(name, value).commit();
    }

    public int getint(String name){

        return sharedPreferences.getInt(name, 0);
    }

    public void setBoolean(String name,Boolean value){
        sharedPreferences.edit().putBoolean(name, value).commit();
    }

    public Boolean getBoolean(String name){

        return sharedPreferences.getBoolean(name, false);
    }

    public void clear(){
        sharedPreferences.edit().clear().commit();
    }

}
