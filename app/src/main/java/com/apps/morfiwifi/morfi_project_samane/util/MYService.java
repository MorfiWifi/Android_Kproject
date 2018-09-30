package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.ui.notification.MessageNotification;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.Parse;
import com.parse.ParseCloud;

import java.nio.channels.Channel;
import java.util.HashMap;

public class MYService extends IntentService {

    private Handler handler;

    public MYService() {
        super("test-Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static final String ACTION = "com.codepath.example.servicesdemo.MyTestService";

    //    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
//    public static final String NOTIFICATION = "com.vogella.android.service.receiver";
    public static final String NOTIFICATION = "com.apps.morfiwifi.morfi_project_samane";




    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("SERVICE :" , "ON HANDLE CALLED!");
        /*try {
            wait(5000);
        }catch (Exception e){
            Log.d("SERVICE :" , e.getMessage());

        }*/
        // Extract the receiver passed into the service
        ResultReceiver rec = intent.getParcelableExtra("receiver");
        // Extract additional values from the bundle
        String val = intent.getStringExtra("foo");
        // To send a message to the Activity, create a pass a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);
        // Here we call send passing a resultCode and the bundle of extras
        rec.send(Activity.RESULT_OK, bundle);
        Log.d("SERVICE :" , "HANDLING SOME INTENT");
//        publishResults("SOME THING OUT", 56);


        MessageNotification.notify(getApplicationContext() , "ASDADAASDSA" , 45);


        handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
//                tv.append("Hello World");
//                handler.postDelayed(this, 1000);

                Log.d("SERVICE :" , "NOTIFICATION NOTYFIED");
            }
        };

        handler.postDelayed(r, 1000);
        Log.d("SERVICE :" , "NOTIFICATION WILL RUB IN 1s");


    }

//    private void publishResults(String outputPath, int result) {
//        Intent intent = new Intent(NOTIFICATION);
//        intent.putExtra("FIRST", outputPath);
//        intent.putExtra("OK", result);
//        sendBroadcast(intent);
//        Log.d(this.getPackageName() , "BRoADCAST HAS BEEN SENT >>>");
//
//// prepare intent which is triggered if the
//// notification is selected
//
////        Intent intent = new Intent(this, NotificationReceiver.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//// build notification
//// the addAction re-use the same intent to keep the example short
//        Notification n  = new Notification.Builder(this)
//                .setContentTitle("New mail from " + "test@gmail.com")
//                .setContentText("Subject")
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentIntent(pIntent)
//                .setAutoCancel(true)
//                .addAction(R.drawable.ic_account_billing, "Call", pIntent)
//                .addAction(R.drawable.ic_3dot, "More", pIntent)
//                .addAction(R.drawable.ic_account, "And more", pIntent).build();
//
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, n);
//
//
//
//    }
}
