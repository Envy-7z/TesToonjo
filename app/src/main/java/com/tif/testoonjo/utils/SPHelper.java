package com.tif.testoonjo.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SPHelper {
    public static final String key_token = "token";
    private final String SP_NAME = "Helper";
    public static final String SP_ID = "1";
    private SharedPreferences sp;
    private Context context;
    private SharedPreferences.Editor editor;
    private static SPHelper instance;

    public SPHelper(Context context) {
        this.context = context;
    }

    public static SPHelper getInstance(Context context) {
        if(instance==null){
            instance = new SPHelper(context);
        }
        return instance;
    }

    private void openSP(){
        sp = context.getSharedPreferences(SP_NAME, 0);
        editor = sp.edit();
    }

    public void setData(String key, String value){
        openSP();
        editor.putString(key, value);
        //editor.putString(key, value);
        editor.commit();
    }

    public void setData(String key, Integer value){
        openSP();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setData(String key, Boolean value){
        openSP();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getDataString(String key){
        sp = context.getSharedPreferences(SP_NAME, 0);
        //return sp.getString(key, null);
        return sp.getString(key, "");
    }

    public Integer getDataInt(String key){
        sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getInt(key, 0);
    }

    public Boolean getDataBoolean(String key){
        sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getBoolean(key, false);
    }

}
