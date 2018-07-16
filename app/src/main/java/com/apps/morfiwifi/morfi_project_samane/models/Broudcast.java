package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.ui.student.BroadcastActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.technical.TechnicalActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.broudcast_RecyclerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

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






    private static boolean isloaded = false;
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


    public static void send_broundcast (@Nullable final AppCompatActivity activity , final boolean display_loading , Broudcast broudcast , User user){
       try {
           ParseObject parseObject = new ParseObject(Broudcast);
           parseObject.put(matn1 , broudcast.matn);
           parseObject.put(header1 ,broudcast.header );
           parseObject.put(role_name1 , broudcast.role_name);
           parseObject.put(senderrole_id1 , user.Role_id);
           parseObject.put(sender_id1 , user.id);
           parseObject.put(recive_role_id1 , broudcast.reciverrole_id);

           parseObject.saveInBackground(new SaveCallback() {
               @Override
               public void done(ParseException e) {
                   if (e != null){
                       Init.Terminal("SUCCSEE IN SENDIG BROUDCAST >>>>>>>>>>");
                       if (activity != null && display_loading){
                           if (activity instanceof TechnicalActivity){
                               // TODO: 7/14/2018  Refresh list of sent broudcastes
                           }
                       }

                   }
               }
           });
       }catch (Exception e  ){
           Init.Terminal("Some Wrong in sending Broud cast (Creating one)");
           Log.e("SAMANE" , "Some Wrong in sending Broud cast (Creating one)");
       }



    }
    public static List<Broudcast> getBroudcastList(Context context) {
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(Broudcast);
                query.whereEqualTo(recive_role_id1 , User.current_user.Role_id);
                tempList =  query.find();
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
                    tempList =  query.find();
                    Conver_Parse();
                }catch (Exception e){
                    broudcastList = new ArrayList<>();
                }
            }
        }
        return broudcastList;
    }

    public static void getBroudcastList(final AppCompatActivity activity , final boolean display_loading) {
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(Broudcast);
                query.whereEqualTo(recive_role_id1 , User.current_user.Role_id);
                if (activity instanceof  BroadcastActivity){
                    ((BroadcastActivity) activity).start_loading();
                }
                 query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            tempList = objects;
                            Conver_Parse();
                            isloaded = true;
                            if (activity instanceof BroadcastActivity && display_loading){
                                broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                ((BroadcastActivity) activity).stop_loading();
                            }
                        }else {
                            Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            if (activity instanceof BroadcastActivity && display_loading){
                                broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                ((BroadcastActivity) activity).stop_loading();
                            }
                        }
                    }
                });
            }catch (Exception e){
                broudcastList = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(activity)){
                return ;
            }else {
                try {
                    ParseQuery query = new ParseQuery(Broudcast);
                    query.whereEqualTo(recive_role_id1 , User.current_user.Role_id);
                    if (activity instanceof  BroadcastActivity &&  display_loading){
                        ((BroadcastActivity) activity).start_loading();
                    }
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List objects, ParseException e) {
                            if (e == null){
                                tempList = objects;
                                Conver_Parse();
                                isloaded = true;
                                if (activity instanceof BroadcastActivity && display_loading){
                                    broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                    ((BroadcastActivity) activity).stop_loading();
                                }
                            }else {
                                Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            }
                        }
                    });
                }catch (Exception e){
                    broudcastList = new ArrayList<>();
                    if (activity instanceof BroadcastActivity && display_loading){
                        broudcast_RecyclerAdapter.Init(broudcastList , activity);
                        ((BroadcastActivity) activity).stop_loading();
                    }
                }
            }
        }
        return ;
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
                broudcast.created_date = parseObject.getCreatedAt();
                broudcast.senderrole_id = parseObject.get(senderrole_id1).toString();
                broudcast.role_name = parseObject.get(role_name1).toString();
                broudcastList.add(broudcast);
            }
        }
    }
    public static void Clear(){
        broudcastList = null;
        tempList = null;
        isloaded = false;
    }
}
