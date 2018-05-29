package com.apps.morfiwifi.morfi_project_samane.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.models.Message
import com.apps.morfiwifi.morfi_project_samane.utility.Init
import com.apps.morfiwifi.morfi_project_samane.view.message_RecyclerAdapter

class ReciverActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciver)


        //todo Activate Reciving Messages From Server After Testing Offline

        //Message.GetMessages(applicationContext , this);


        message_RecyclerAdapter.Init(Init.get_messages_dao(this), this)

    }





}
