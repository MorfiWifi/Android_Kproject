package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.parse.ParsePushBroadcastReceiver;

public class YourBroadcastReceiver  extends ParsePushBroadcastReceiver {

    protected Class<? extends Activity> getActivity(Context context, Intent intent) {
        return LoginActivity.class; // the activity that shows up
    }
}
