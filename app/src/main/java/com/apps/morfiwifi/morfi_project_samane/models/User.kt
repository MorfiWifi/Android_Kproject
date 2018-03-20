package com.apps.morfiwifi.morfi_project_samane.models

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.models.TokenModel.TokenSTR
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by WifiMorfi on 3/19/2018.
 */
class User   {
    val provider = RetrofitDataProvider()
    var mTService : RetrofitDataService = provider.tService
    var user= User()

    var Id:String
    var FName:String
    var LName:String
    var Pass:String
    var Pass_hash:String
    var Kaet_meli:String
    var Type:kind

    constructor(){
        Id = ""
        FName = ""
        LName = ""
        Pass = ""
        Pass_hash = ""
        Kaet_meli = ""
        Type = kind.Student
    }

    fun GetUser ( context: Context , activity :AppCompatActivity ): String {
        mTService = provider.tService

        val call = mTService.GetUser(TokenSTR)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable?) {
                println("Cant Read User (ERROR)")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    user = response.body()
                    if (activity is MessageActivity){
                        activity.update_user(user)
                    }
                    println("Success User Read")
                }else{
                    println("Faild Read User")
                }
            }
        })
        return "no Way its Async!!"
    }
}

enum class kind {
    Student, Master, Technical, Site_Master, Self_Service, Admin
}