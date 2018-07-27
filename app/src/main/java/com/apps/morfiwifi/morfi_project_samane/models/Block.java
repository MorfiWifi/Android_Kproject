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




public class Block {

    public static String class_name = "blook";
    public static String obj_name = "name";
    public static String obj_code = "code";


    public String Id = Init.Empty;

    public Date createAt = Calendar.getInstance().getTime();

    public Long id;
    public String name = Init.Empty;
    public String code = Init.Empty;
    public List<Room> rooms;


    @Override
    public String toString() {
        return name;
    }



    public static boolean isloaded = false;

    public static List<ParseObject> temp;

    public static  List<Block> blocks;



    public static List<Block> load_blocks (Context context){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                temp =  query.find();
                convert_parse();
                isloaded = true;
            }catch (Exception e){
                blocks = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return blocks;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    temp =  query.find();
                    convert_parse();
                }catch (Exception e){
                    blocks = new ArrayList<>();
                }
            }
        }
        return blocks;
    }
    public static void load_blocks  (final AppCompatActivity activity , final boolean draw_loading){
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
                blocks = new ArrayList<>();
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
                    blocks = new ArrayList<>();
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
            blocks = new ArrayList<>();
            for (ParseObject parseObject : temp) {
                Block block = new Block();
                block.Id = parseObject.getObjectId();
                block.createAt = parseObject.getCreatedAt();
                block.name = parseObject.get(obj_name).toString();
                blocks.add(block);
            }
        }else {
            blocks = new ArrayList<>();
        }

    }

    public static void Clear(){
        blocks = null;
        temp   = null;
        isloaded = false;
    }
    //Pares server THINGS ......
    
}
