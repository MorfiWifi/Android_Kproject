package com.apps.morfiwifi.morfi_project_samane.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.models.Message
import com.apps.morfiwifi.morfi_project_samane.models.User
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
    }


    fun recive_messages(view:View){
        //Message.GetMessages(applicationContext ,this )
    }

    fun send_message (view:View){
        /*val mes = Message()
        mes.Id = 0L // Dosent Matter (Server Fills!)
        mes.Matn = et_matn.text.toString()
        mes.Readed = ErrorUtils.No
        mes.Reciver_ID = et_revicer.text.toString()
        mes.Sender_ID = et_sender.text.toString()
        mes.Reciver_Type = User.Kind.Student
        mes.Recive_Date = ""
        mes.Tags = ""
        Message.InsertMessages(applicationContext ,this , mes)*/

    }
    fun update_recived (messages : List<Message>){


    }

    fun update_user (user : User){


    }

    fun update_messages (messages : String){
        tv_recived_messages.setText(messages)
    }

}
