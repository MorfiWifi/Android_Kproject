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

public class Thing {
    public String Id;
    public String name;
    public String code;
    public Date createAt;


    private static String class_name = "thing";
    private static String obj_name = "name";
    public static String obj_code = "code";


    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Thing> things ;



    public static List<Thing> load_htings (Context context){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                temp =  query.find();
                convert_parse();
                isloaded = true;
            }catch (Exception e){
                things = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return things;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    temp =  query.find();
                    convert_parse();
                }catch (Exception e){
                    things = new ArrayList<>();
                }
            }
        }
        return things;
    }
    public static void load_things  (final AppCompatActivity activity , final boolean draw_loading){
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
                things = new ArrayList<>();
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
                    things = new ArrayList<>();
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
            things = new ArrayList<>();
            for (ParseObject parseObject : temp) {
                Thing thing = new Thing();
                thing.Id = parseObject.getObjectId();
                thing.createAt = parseObject.getCreatedAt();
                thing.name = parseObject.get(obj_name).toString();
                things.add(thing);
            }
        }else {
            things = new ArrayList<>();
        }

    }

    public static void Clear(){
        things = null;
        temp   = null;
        isloaded = false;
    }


}
