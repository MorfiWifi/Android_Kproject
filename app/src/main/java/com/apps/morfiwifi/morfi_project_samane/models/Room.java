package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.ui.student.BroadcastActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
public class Room {

    public static String class_name = "otagh";
    public static String obj_name = "name";
    public static String obj_code = "code";

    public static boolean isloaded = false;
    public static List<ParseObject> temp;
    public static  List<Room> rooms;


//    @Transient
    public String Id;
//    @Transient
    public Date createAt;

//    @Id(autoincrement = true)
    public Long id;
    public String code;
    public String name = "";



    public static List<Room> load_otaghs (Context context){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                temp =  query.find();
                convert_parse();
                isloaded = true;
            }catch (Exception e){
                rooms = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return rooms;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    temp =  query.find();
                    convert_parse();
                }catch (Exception e){
                    rooms = new ArrayList<>();
                }
            }
        }
        return rooms;
    }
    public static void load_otaghs  (final AppCompatActivity activity , final boolean draw_loading){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                if (activity instanceof BroadcastActivity && draw_loading){
                    ((BroadcastActivity) activity).start_loading();
                }
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            temp = objects;
                            convert_parse();
                            isloaded = true;
                            if (activity instanceof BroadcastActivity && draw_loading){
//                                broudcast_RecyclerAdapter.Init(roles , activity);
                                ((BroadcastActivity) activity).stop_loading();
                            }
                        }else {
                            Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            if (activity instanceof BroadcastActivity && draw_loading ){
//                                broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                ((BroadcastActivity) activity).stop_loading();
                            }
                        }
                    }
                });
            }catch (Exception e){
                rooms = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(activity)){
                return ;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    if (activity instanceof  BroadcastActivity && draw_loading){
                        ((BroadcastActivity) activity).start_loading();
                    }
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List objects, ParseException e) {
                            if (e == null){
                                temp = objects;
                                convert_parse();
                                isloaded = true;
                                if (activity instanceof BroadcastActivity && draw_loading){
//                                    broudcast_RecyclerAdapter.Init(roles , activity);
                                    ((BroadcastActivity) activity).stop_loading();
                                }
                            }else {
                                Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            }
                        }
                    });
                }catch (Exception e){
                    rooms = new ArrayList<>();
                    if (activity instanceof BroadcastActivity && draw_loading){
//                        broudcast_RecyclerAdapter.Init(roles , activity);
                        ((BroadcastActivity) activity).stop_loading();
                    }
                }
            }
        }
        return ;

    }
    private static void convert_parse(){
        if (temp != null){
            rooms = new ArrayList<>();
            for (ParseObject parseObject : temp) {
                Room room = new Room();
                room.Id = parseObject.getObjectId();
                room.createAt = parseObject.getCreatedAt();
                room.name = parseObject.get(obj_name).toString();
                rooms.add(room);
            }
        }else {
            rooms = new ArrayList<>();
        }

    }

    public static void Clear(){
        rooms = null;
        temp   = null;
        isloaded = false;
    }
}
