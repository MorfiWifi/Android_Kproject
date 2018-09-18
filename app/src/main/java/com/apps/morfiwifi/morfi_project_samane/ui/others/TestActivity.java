package com.apps.morfiwifi.morfi_project_samane.ui.others;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.ui.notification.MessageNotification;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void do_the_test(View view) {
        MessageNotification messageNotification = new MessageNotification();
        MessageNotification.notify(this , "MY STRING " , 1);
    }
}
