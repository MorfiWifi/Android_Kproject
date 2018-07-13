package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Broudcast {

    public static String Broudcast = "broudcast";
    public static String recive_role_id1 = "reciverrole_id";
    public static String matn1 = "matn";
    public static String createdAt = "createdAt";
    public static String sender_id1 = "sender_id";
    public static String header1 = "header";
    public static String senderrole_id1 = "senderrole_id";
    public static String role_name1 = "role_name";






    public static boolean isloaded = false;
    public static List<Broudcast> broudcastList;
    public static List<ParseObject> tempList;

    public String id ;
    public String matn ;
    public String sender_id;
    public String header ;
    public String reciverrole_id;
    public Date created_date;
    public String role_name;
    public String senderrole_id;

    public List<Broudcast> getBroudcastList(Context context) {
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(Broudcast);
                query.whereEqualTo(recive_role_id1 , User.current_user.Role_id);
                tempList =  query.find();  // FIXME: 7/13/2018 Do in background
                Conver_Parse();
                isloaded = true;
            }catch (Exception e){
                broudcastList = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return broudcastList;
            }else {
                try {
                    ParseQuery query = new ParseQuery(Broudcast);
                    query.whereEqualTo(recive_role_id1 , User.current_user.Role_id);
                    tempList =  query.find();  // FIXME: 7/13/2018 Do in background
                    Conver_Parse();
                }catch (Exception e){
                    broudcastList = new ArrayList<>();
                }
            }
        }
        return broudcastList;
    }


    public static void Conver_Parse (){
        if (tempList != null){
            broudcastList = new ArrayList<>();
            for (ParseObject parseObject: tempList ) {
                Broudcast broudcast = new Broudcast();
                broudcast.header = parseObject.get(header1).toString();
                broudcast.id = parseObject.getObjectId();
                broudcast.matn = parseObject.get(matn1).toString();
                broudcast.sender_id = parseObject.get(sender_id1).toString();
                broudcast.reciverrole_id = parseObject.get(recive_role_id1).toString();
                broudcast.created_date = parseObject.getDate(createdAt);
                broudcast.senderrole_id = parseObject.get(senderrole_id1).toString();
                broudcast.role_name = parseObject.get(role_name1).toString();
                broudcastList.add(broudcast);
            }
        }
    }
}
