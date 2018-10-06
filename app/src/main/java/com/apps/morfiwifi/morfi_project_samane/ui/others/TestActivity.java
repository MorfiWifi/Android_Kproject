package com.apps.morfiwifi.morfi_project_samane.ui.others;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.ui.notification.MessageNotification;
import com.apps.morfiwifi.morfi_project_samane.util.MYService;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void do_the_test(View view) {
//        MessageNotification messageNotification = new MessageNotification();
//        MessageNotification.notify2(this , "MY STRING " , 1);
        Dialogue.about_us(this , "LAB !");

    }

    public void do_service(){
        Intent mServiceIntent = new Intent();
        mServiceIntent.putExtra("download_url", "WWW.GOOGLE.COM");
//        MYService.enqueueWork(getApplicationContext(), MYService.class, 100, mServiceIntent);
    }
}
