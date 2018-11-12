package com.apps.morfiwifi.morfi_project_samane.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.utility.Init
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_message

class ReciverActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciver)


        //todo Activate Reciving Messages From Server After Testing Offline

        //Message.GetMessages(applicationContext , this);


        RecyclerAdapter_message.Init(Init.get_messages_dao(this), this)

    }





}
