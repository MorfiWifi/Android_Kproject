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

public class Thing {
    public String Id = Init.Empty;
    public String name = Init.Empty;
    public String code = Init.Empty;
    public boolean iscountable = false;
    public Date createAt = Calendar.getInstance().getTime();


    public static final int CODE = 19;

    private static String class_name = "thing";
    private final static String obj_name = "name";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_cod = "cod";
    private final static String obj_iscountable = "countable";



    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Thing> things ;
    private static int limit = 100;
    private static String[] all_params = {obj_name ,obj_cod ,obj_id ,obj_createAt ,obj_iscountable };


    public static void load_things (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
            ParseQuery query = new ParseQuery(class_name);
            query.setLimit(limit);
            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result( things , CODE , true);
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
            Result result = new Result(things , CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static List<Thing> load_things_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return things;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Thing> thing_in = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            thing_in = things;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return thing_in;
    }

    private static void convert_parse(){
        things = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Thing thing = new Thing();
                thing.null_self_fixer(parseObject);
                things.add(thing);
            }
        }
    }

    private void null_self_fixer (ParseObject parseObject){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t;
        Object object;
        int m = 0;
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
                    object = parseObject.get(obj_name);
                    if (object != null){
                        name = object.toString();
                    }
                    break;
                case obj_cod:
                    t = Init.Empty;
                    object = parseObject.get(obj_cod);
                    if (object != null){
                        t = object.toString();
                    }
                    code = t;
                    break;
                case obj_iscountable:
                    iscountable = parseObject.getBoolean(obj_iscountable);
                default:
                    break;


            }
        }

    }

    public static void Clear(){
        things = null;
        temp   = null;
        isloaded = false;
    }

    public static Thing get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (things == null){
            load_things_fog(false);
        }

        Thing thing1 = new Thing();
        if (things != null){
            for (Thing thing :things) {
                if (thing.Id.equals(id)){
                    thing1 = thing;
                    break;
                }
            }
        }
        return thing1;
    }

    public static boolean isIsloaded(){
        return isloaded;
    }

    @Override
    public String toString() {
        return name;
    }
}
