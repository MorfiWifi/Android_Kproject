package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;

public class MYService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MYService(String name) {
        super(name);
    }




    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
