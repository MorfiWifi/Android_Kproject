package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

//@Entity
public class Room {

    public static final int CODE = 18;

    public static String class_name = "otagh";
    private final static String obj_name = "name";
    private final static String obj_code = "code";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_blook_id = "blook_id";

    public static boolean isloaded = false;
    public static List<ParseObject> temp;
    public static  List<Room> rooms;


    private static String[] all_params = {obj_name ,obj_code ,obj_id ,obj_createAt ,obj_blook_id };
    private static int limit = 100;


    //    @Transient
    public String Id = Init.Empty;
//    @Transient
    public Date createAt = Calendar.getInstance().getTime();

//    @Id(autoincrement = true)
    public Long id;
    public String code = Init.Empty;
    public String name = Init.Empty;
    public String blook_id = Init.Empty;


    public static void load_rooms (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
            ParseQuery query = new ParseQuery(class_name);
            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result( rooms , CODE , true);
                        Init.result_of_query(activity , result);
                        isloaded = true;

                        if (draw_loading)
                            Init.stop_loading(activity);

                    }else {
                        result = new Result(e , CODE);
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Init.result_of_query(activity , result);
                    }
                }
            });


        }else {
            if (draw_loading)
                Init.stop_loading(activity);
            Result result = new Result(rooms , CODE , true);
            Init.result_of_query(activity , result);
        }
    }


    private static void convert_parse(){
        rooms = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Room room = new Room();
                room.null_self_fixer(parseObject);
                rooms.add(room);
            }
        }
    }
    private void null_self_fixer (ParseObject parseObject){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t;
        Object temp;
        for (String param: all_params) {
            switch (param){
                case obj_id :
                    t = parseObject.getObjectId();
                    if (t != null){
                        Id = t;
                    }
                    break;
                case obj_createAt:
                    Date date = parseObject.getCreatedAt();
                    if (date != null){
                        createAt = date;
                    }
                    break;

                case obj_name:
                    t = parseObject.get(obj_name).toString();
                    if (t != null){
                        name = t;
                    }
                    break;
                case obj_code:
                    t = parseObject.get(obj_code).toString();
                    if (t != null){
                        code = t;
                    }
                    break;
                case obj_blook_id:
                    t = parseObject.get(obj_blook_id).toString();
                    if (t != null){
                        blook_id = t;
                    }
                    break;
                default:
                    break;


            }
        }

    }
    public static boolean isloaded_all(){
        return isloaded;
    }

    public static void Clear(){
        rooms = null;
        temp   = null;
        isloaded = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Room> load_rooms_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return rooms;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Room> khabgahArrayList = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            khabgahArrayList = rooms;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return khabgahArrayList;
    }

    public static Room get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (rooms == null){
            load_rooms_fog(false);

        }
        Room khabgah1 = new Room();
        if (rooms != null){
            for (Room khabgah :rooms) {
                if (khabgah.Id.equals(id)){
                    khabgah1 = khabgah;
                    break;
                }
            }
        }
        return khabgah1;
    }
}
