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



public class Khabgah {

    public static final int CODE = 8;

    private final static String class_name = "khabgah";
    private final static String obj_name = "name";
    private final static String obj_code = "code";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";

    private static String[] all_params = {obj_name ,obj_code ,obj_id ,obj_createAt};



    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    public static  List<Khabgah> khabgahs;
    private static int limit = 100;

    //    @Transient
    public String Id = Init.Empty;
//    @Transient
    private Date createAt = Calendar.getInstance().getTime();

//    @Id(autoincrement = true)
    public Long id;
    public String name = Init.Empty;
    public String code = Init.Empty;
//    @ToMany(referencedJoinProperty = "id")
    public List<Block> blocks;



    public static void load_Khabgahs (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
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

                        result = new Result( khabgahs , CODE , true);
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
            Result result = new Result(khabgahs , CODE , true);
            Init.result_of_query(activity , result);
        }
    }


    private static  void convert_parse(){
        khabgahs = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Khabgah khabgah = new Khabgah();
                khabgah.null_self_fixer(parseObject);
                khabgahs.add(khabgah);
            }
        }
    }

    public static void Clear(){
        khabgahs = null;
        temp   = null;
        isloaded = false;
    }


    private  void null_self_fixer (ParseObject parseObject){
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
                default:
                    break;


            }
        }

    }

    public static boolean isloaded_all(){
        return isloaded;
    }


    public static List<Khabgah> load_khabgahs_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return khabgahs;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Khabgah> khabgahArrayList = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            khabgahArrayList = khabgahs;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return khabgahArrayList;
    }

    public static Khabgah get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (khabgahs == null){
            load_khabgahs_fog(false);

        }
        Khabgah khabgah1 = new Khabgah();
        if (khabgahs != null){
            for (Khabgah khabgah :khabgahs) {
                if (khabgah.Id.equals(id)){
                    khabgah1 = khabgah;
                    break;
                }
            }
        }
        return khabgah1;
    }

    @Override
    public String toString() {
        return name;
    }
}
