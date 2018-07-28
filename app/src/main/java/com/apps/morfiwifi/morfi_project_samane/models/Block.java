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

    public static final int CODE = 2;

    private final static String class_name = "blook";
    private final static String obj_name = "name";
    private final static String obj_code = "code";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_kh_id = "khabgah_id";

    private static String[] all_params = {obj_name ,obj_code ,obj_id ,obj_createAt ,obj_kh_id };

    public String Id = Init.Empty;

    public Date createAt = Calendar.getInstance().getTime();

    public Long id;
    public String name = Init.Empty;
    public String code = Init.Empty;
    public String khabgah_id = Init.Empty;
    public List<Room> rooms;


    @Override
    public String toString() {
        return name;
    }



    public static boolean isloaded = false;

    public static List<ParseObject> temp;

    public static  List<Block> blocks;

    public static void load_blocks (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
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

                        result = new Result( blocks , CODE , true);
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
            Result result = new Result(blocks , CODE , true);
            Init.result_of_query(activity , result);
        }
    }


    private static void convert_parse(){
        blocks = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Block block = new Block();
                block.null_self_fixer(parseObject);
                blocks.add(block);
            }
        }
    }

    public static void Clear(){
        blocks = null;
        temp   = null;
        isloaded = false;
    }

    private void null_self_fixer (ParseObject parseObject){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t;
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
                    case obj_kh_id:
                    t = parseObject.get(obj_kh_id).toString();
                    if (t != null){
                        khabgah_id = t;
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


}
