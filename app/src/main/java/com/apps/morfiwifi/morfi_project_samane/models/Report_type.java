package com.apps.morfiwifi.morfi_project_samane.models;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Entity
public class Report_type {
    // Thise Are just ITEMS FOR CHOOSING !
//    @Id(autoincrement = true)
    public String id = Init.Empty;
    public String name = Init.Empty;
    public String cod = Init.Empty;
    public Date createAt = Calendar.getInstance().getTime();


    public static final int CODE = 8;

    private static String class_name = "gozareshtype";
    private final static String obj_name = "name";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_cod = "cod";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Report_type> report_types;
    private static int limit = 100;

    private static String[] all_params = {obj_name ,obj_cod ,obj_id ,obj_createAt  };

    public static void load_report_types (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
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

                        result = new Result( report_types , CODE , true);
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
            Result result = new Result(report_types , CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static List<Report_type> load_report_types_fog (){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Report_type> report_types_in = new ArrayList<>();
            ParseQuery query = new ParseQuery(class_name);
            query.setLimit(limit);

            try {
                temp = query.find();
                convert_parse();
                report_types_in = report_types;
                isloaded = true;
            } catch (ParseException e) {
                Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
            }
            return report_types_in;
    }


    private static void convert_parse(){
        report_types = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Report_type report_type = new Report_type();
                report_type.null_self_fixer(parseObject);
                report_types.add(report_type);
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
                        id = t;
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
                case obj_cod:
                    // new BUG FOUND parse object retorn null - null to string !!
                    t = Init.Empty;
                    object = parseObject.get(obj_cod);
                    if (object != null){
                        t = object.toString();
                    }
                    cod = t;
                    break;
                default:
                    break;


            }
        }

    }
    public static boolean isloaded_all(){
        return isloaded;
    } // just for STD

    public static void Clear(){
        report_types = null;
        temp   = null;
        isloaded = false;
    }

    @Override
    public String toString() {
        if (name == null){
            name = Init.Empty;
        }
        if (name.isEmpty()){
            return "بی نام!";
        }else {
            return name;
        }
    }


    public static Report_type get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (report_types == null){
            load_report_types(null , false , false);
        }

        Report_type report_type = new Report_type();
        if (report_types != null){
            for (Report_type rep :report_types) {
                if (rep.id.equals(id)){
                    report_type = rep;
                    break;
                }
            }
        }
        return report_type;
    }
}
