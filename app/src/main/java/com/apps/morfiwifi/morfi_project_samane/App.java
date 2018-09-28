package com.apps.morfiwifi.morfi_project_samane;

import com.apps.morfiwifi.morfi_project_samane.util.Security;
import com.parse.Parse;
import android.app.Application;
import android.util.Base64;
import android.util.Log;

public class App extends Application {

    static {
        System.loadLibrary("keys");
    }

    public native String getNativeKey1();
    public native String getNativeKey2();
    public native String getserver();
    @Override
    public void onCreate() {
        super.onCreate();

        // Remove for production, use to verify FCM is working
        // Look for ParseFCM: FCM registration success messages in Logcat to confirm.
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
//    Log.d("THE KEY1 :" , new String(Base64.decode(getNativeKey1(),Base64.DEFAULT)));
    Log.d("THE KEY1 :" , getNativeKey1());
    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey1()));
//    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey1()));
    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getNativeKey2()));
    Log.d("THE KEY1 DEC :" , Security.Decript_STR(getserver()));


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
        );
    }

}
