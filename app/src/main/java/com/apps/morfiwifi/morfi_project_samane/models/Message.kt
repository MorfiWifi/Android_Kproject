package com.apps.morfiwifi.morfi_project_samane.models

/**
 * Created by WifiMorfi on 3/19/2018.
 */
class Message {
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



}