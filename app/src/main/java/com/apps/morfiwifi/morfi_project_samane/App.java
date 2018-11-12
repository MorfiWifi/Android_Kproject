package com.apps.morfiwifi.morfi_project_samane;

import com.apps.morfiwifi.morfi_project_samane.util.Security;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
//import com.onesignal.OneSignal;
import com.parse.Parse;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import java.io.FileInputStream;

public class App extends Application {

//    static {
//        System.loadLibrary("keys");
//    }
//
//    public static native String getNativeKey1();
//    public static native String getNativeKey2();
//    public static native String getserver();


    @Override
    public void onCreate() {
        super.onCreate();

        // Remove for production, use to verify FCM is working
        // Look for ParseFCM: FCM registration success messages in Logcat to confirm.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
//    Log.d("THE KEY1 :" , new String(Base64.decode(getNativeKey1(),Base64.DEFAULT)));
//    Log.d("THE KEY1 :" , getNativeKey1());
//    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey1()));
//    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey1()));
//    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey2()));
//    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getserver()));



    try {
        FileInputStream serviceAccount =
                new FileInputStream("path/to/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("firebase-adminsdk-lzwhq@exam1-4e724.iam.gserviceaccount.com")
                .build();

        FirebaseApp.initializeApp(getApplicationContext());

    }catch (Exception e){
        Log.d("FIERBASE : " , e.getMessage());
    }

    init_parse(getApplicationContext());

/*

        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId(Security.Decript_STR(getNativeKey1()))
                .applicationId(getNativeKey1())
                // .applicationId("first")
//                .clientKey(Security.Decript_STR(getNativeKey2()))
                .clientKey(getNativeKey2())

//                .clientKey("QE5Pwhr60WAfd24VtGcFIV3KHDStpK1Q2WgtP4yr") // MASTER
                .server(getserver())
//                .server(Security.Decript_STR(getserver()))

                //.server("http://192.168.1.110:1337/parse/")
                .build()
        );*/

        /*OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();*/

    }

    static void init_parse( Context context){
        Parse.initialize(new Parse.Configuration.Builder(context)
//                .applicationId(Security.Decript_STR(getNativeKey1()))
//                        .applicationId("z8ABZI7P44AzqQHSShS09qtKpcVjx8Dk8F9WipRB")
                         .applicationId("first")
//                .clientKey(Security.Decript_STR(getNativeKey2()))
//                        .clientKey("fmbYAhCnAHlXvk9p8B2UBPK8vaeyTm1ZOZ1zPxq9")

//                .clientKey("QE5Pwhr60WAfd24VtGcFIV3KHDStpK1Q2WgtP4yr") // MASTER
//                        .server("https://parseapi.back4app.com/")
//                .server(Security.Decript_STR(getserver()))

                        .server("http://192.168.1.105:1337/parse/")
//                        .server("http://192.168.1.45:1337/parse/")
                        .build()
        );

    }



}
