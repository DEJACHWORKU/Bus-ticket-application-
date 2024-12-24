package com.example.abs_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String DATA_LOGIN = "status_login",
            DATA_AS = "campus",name ="name",phone="phone number";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setName(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(name,data);
        editor.apply();
    }

    public static String getName(Context context){
        return getSharedPreferences(context).getString(name,"");
    }


    public static void setPhone(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(phone,data);
        editor.apply();
    }

    public static String getPhone(Context context){
        return getSharedPreferences(context).getString(phone,"");
    }




    public static void setDataAs(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_AS,data);
        editor.apply();
    }

    public static String getDataAs(Context context){
        return getSharedPreferences(context).getString(DATA_AS,"");
    }

    public static void setDataLogin(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }

    public static boolean getDataLogin(Context context){
        return getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }


}
