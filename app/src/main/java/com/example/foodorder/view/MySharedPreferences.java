package com.example.foodorder.view;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String UserName= "username";
    private static final String PassWord= "password";
    private Context mContext;

    public MySharedPreferences(Context mContext) {
        this.mContext = mContext;
    }


    public void putUserName(String key,String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(UserName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getUserName(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(UserName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
    public void putPass(String key,String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PassWord,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getPassWord(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PassWord,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
