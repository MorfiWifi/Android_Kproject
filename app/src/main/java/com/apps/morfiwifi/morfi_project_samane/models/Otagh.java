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
public class Otagh {

    public static String class_name = "otagh";
    public static String obj_name = "name";
    public static String obj_code = "code";

    public static boolean isloaded = false;
    public static List<ParseObject> temp;
    public static  List<Otagh> otaghs;


//    @Transient
    public String Id;
//    @Transient
    public Date createAt;

//    @Id(autoincrement = true)
    public Long id;
    public String code;
    public String name = "";



    public static List<Otagh> load_otaghs (Context context){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                temp =  query.find();
                convert_parse();
                isloaded = true;
            }catch (Exception e){
                otaghs = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return otaghs;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    temp =  query.find();
                    convert_parse();
                }catch (Exception e){
                    otaghs = new ArrayList<>();
                }
            }
        }
        return otaghs;
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
                otaghs = new ArrayList<>();
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
                    otaghs = new ArrayList<>();
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
            otaghs = new ArrayList<>();
            for (ParseObject parseObject : temp) {
                Otagh otagh = new Otagh();
                otagh.Id = parseObject.getObjectId();
                otagh.createAt = parseObject.getCreatedAt();
                otagh.name = parseObject.get(obj_name).toString();
                otaghs.add(otagh);
            }
        }else {
            otaghs = new ArrayList<>();
        }

    }

    public static void Clear(){
        otaghs = null;
        temp   = null;
        isloaded = false;
    }
}
