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
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class Khabgah {

    public static String class_name = "khabgah";
    public static String obj_name = "name";
    public static String obj_code = "code";

    public static boolean isloaded = false;
    public static List<ParseObject> temp;
    public static  List<Khabgah> things ;

//    @Transient
    public String Id = Init.Empty;
//    @Transient
    public Date createAt = Calendar.getInstance().getTime();

//    @Id(autoincrement = true)
    public Long id;
    public String name = Init.Empty;
    public String code = Init.Empty;
//    @ToMany(referencedJoinProperty = "id")
    public List<Block> blocks;

    public static List<Khabgah> load_Khabgahs (Context context){
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
    public static void load_Khabgahs  (final AppCompatActivity activity , final boolean draw_loading){
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
                Khabgah khabgah = new Khabgah();
                khabgah.Id = parseObject.getObjectId();
                khabgah.createAt = parseObject.getCreatedAt();
                khabgah.name = parseObject.get(obj_name).toString();
                things.add(khabgah);
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
