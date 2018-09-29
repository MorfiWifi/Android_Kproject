package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;

public class MYService extends IntentService {

    public MYService() {
        super("MYService");
    }
//    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
//    public static final String NOTIFICATION = "com.vogella.android.service.receiver";
    public static final String NOTIFICATION = "com.apps.morfiwifi.morfi_project_samane";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            wait(5000);
        }catch (Exception e){
            Log.d("SERVICE :" , e.getMessage());

        }
        publishResults("SOME THING OUT", 56);
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra("FIRST", outputPath);
        intent.putExtra("OK", result);
        sendBroadcast(intent);
        Log.d(this.getPackageName() , "BRoADCAST HAS BEEN SENT >>>");
    }
}
