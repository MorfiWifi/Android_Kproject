package com.apps.morfiwifi.morfi_project_samane;

import android.app.Application;
import android.content.Context;

/**
 * extend application class
 */
public class TApplication extends Application {

    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
