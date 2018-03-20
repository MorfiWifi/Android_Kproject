package com.apps.morfiwifi.morfi_project_samane.models

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by WifiMorfi on 3/19/2018.
 */
class Message {
    val provider = RetrofitDataProvider()
    var mTService : RetrofitDataService = provider.tService

    var Id:String
    var Send_Date:String
    var Recive_Date:String
    var Tags:String
    var Matn:String
    var Sender_ID:String
    var Reciver_ID:String
    var Readed:String
    var Reciver_Type:kind

    constructor(){
        Id = ""
        Send_Date = ""
        Recive_Date = ""
        Tags = ""
        Matn = ""
        Sender_ID = ""
        Reciver_ID = ""
        Readed = ""
        Reciver_Type = kind.Student
    }

    fun hellow(context: Context): String {
        return ""
    }

    fun  GetMessages (context: Context, activity : AppCompatActivity): String {
        mTService = provider.tService

        val call = mTService.GetMessages(TokenModel.TokenSTR)

        call.enqueue(object : Callback<List<Message>> {
            override fun onFailure(call: Call<List<Message>>?, t: Throwable?) {
                println("Cant Read Message (ERROR)")
            }

            override fun onResponse(call: Call<List<Message>>?, response: Response<List<Message>>) {
                if (response.isSuccessful){
                    if (activity is MessageActivity){
                        activity.update_user(STR(response.body()))
                    }
                    println("Success Message Read")
                }else{
                    println("Faild Read Message")
                }
            }


        })
        return "no Way its Async!!"
    }
    fun  InsertMessages (context: Context, activity : AppCompatActivity , mess : Message): String {
        mTService = provider.tService

        val call = mTService.InsertMessages(TokenModel.TokenSTR , mess)

        call.enqueue(object : Callback<Message> {
            override fun onFailure(call: Call<Message>?, t: Throwable?) {
                println("Cant Read Message (ERROR)")
            }

            override fun onResponse(call: Call<Message>?, response: Response<Message>) {
                if (response.isSuccessful){
                    if (activity is MessageActivity){

                    }
                    println("Success Message Read")
                }else{
                    println("Faild Read Message")
                }
            }




        })
        return "no Way its Async!!"
    }

    fun STR (messages : List<Message>): String {
        var str = ""
        var i = 0
        while (i <= messages.size){
            str = str + messages[i].Matn
            i++
        }
        return str
    }


}