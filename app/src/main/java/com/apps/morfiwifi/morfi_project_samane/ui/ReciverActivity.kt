package com.apps.morfiwifi.morfi_project_samane.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.models.Message

class ReciverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciver)

        Message.GetMessages(applicationContext , this);

    }



}
