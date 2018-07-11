package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.content.SharedPreferences;

public class Setting {
    // FIXME: 7/11/2018 pre load affects some data not All of Them (sequrity and size matter)
    private static String preload = "PRELOAD";

    public static boolean isPreload (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Samane_app" , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(preload , true);
    }

    public static void setPreload (boolean actice , Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Samane_app" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editoer = sharedPreferences.edit();
        editoer.putBoolean(preload , actice);
        editoer.apply();
    }


}
