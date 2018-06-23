package com.apps.morfiwifi.morfi_project_samane;

import com.parse.Parse;
import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Remove for production, use to verify FCM is working
        // Look for ParseFCM: FCM registration success messages in Logcat to confirm.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("z8ABZI7P44AzqQHSShS09qtKpcVjx8Dk8F9WipRB")
                .clientKey("fmbYAhCnAHlXvk9p8B2UBPK8vaeyTm1ZOZ1zPxq9")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
