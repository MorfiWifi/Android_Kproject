package com.apps.morfiwifi.morfi_project_samane.models;

import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Entity
public class Report {
    public enum  State{
        open , working_on  , finished  ;

        public static Report.State fromInteger(int x) {
            int count = values().length;
            return values()[x % count];
        }

        @Override
        public String toString() {
            switch (this){
                case open:
                    return "باز";
                case finished:
                    return "تمام شده";
                case working_on:
                    return "درحال پیگیری";
                default:
                    return "مشخص نیست";
            }
        }
    }



    public Report_type report_type;

    public static final int CODE = 4;
    public static final int CODE_ALL = 5;
    public static final int CODE_SEND = 23;

    private static String class_name = "report";
    private final static String obj_state = "state";
    private final static String obj_matn = "matn";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_sender_id = "sender_id";
    private final static String obj_reporttype_id = "gozareshtype_id";
    private final static String obj_reporttype = "gozareshtype";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Report> reports;
    private static int limit = 100;

    //bug WE HAVE SELF REPORTS -HAS BEEN SENT -ALL REPORTS HAS BEEN RECIVED

    private static String[] all_params = {obj_reporttype ,obj_state ,obj_matn ,obj_id ,obj_createAt ,obj_sender_id,obj_reporttype_id };


    //    @Transient
    public String Id = Init.Empty;
    //    @Transient
    public Date createAt = Calendar.getInstance().getTime();
    public String matn = Init.Empty;
    public String name = Init.Empty;
    public String sender_id = Init.Empty;
    public State state = State.fromInteger(0);


    public static void load_self_reports (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);

            // WARN CODE >>>>>>>>>>>>>>>>>>>
            if (!Report_type.isloaded_all())
                Report_type.load_report_types(null , false , false);
            // WARN CODE >>>>>>>>>>>>>>>>>>>

            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
            ParseQuery query = new ParseQuery(class_name);
            query.whereEqualTo(obj_sender_id , User.current_user.id);
            query.setLimit(limit);
            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        if (!Report_type.isloaded_all())
                            Report_type.load_report_types_fog();


                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result( reports , CODE , true);
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
            Result result = new Result(reports , CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_reports (final AppCompatActivity activity , final boolean draw_loading ){ // laod all of them ...
        // NO cache advised
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

                        result = new Result( reports , CODE_ALL , true);
                        Init.result_of_query(activity , result);
                        isloaded = true;

                        if (draw_loading)
                            Init.stop_loading(activity);

                    }else {
                        result = new Result(e , CODE_ALL);
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Init.result_of_query(activity , result);
                    }
                }
            });

    }

    public static void send_self_report(final AppCompatActivity activity , final boolean draw_loading , String matn , String type ){
        if (draw_loading)
            Init.start_loading(activity);
        ParseObject report = new ParseObject(class_name);
        String sender_id = Init.Empty;
        if (User.current_user != null)
            sender_id  = User.current_user.id;

        report.put(obj_sender_id , sender_id);
        report.put(obj_matn , matn);
        report.put(obj_state , 0);
        report.put(obj_reporttype_id , type);


        report.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Result res;
                if (e== null){
                    res = new Result( "DON" , CODE , true);
                    Init.result_of_query(activity , res);

                    if (draw_loading)
                        Init.stop_loading(activity);
                }else {
                    res = new Result(e , CODE);
                    if (draw_loading)
                        Init.stop_loading(activity);
                    Init.result_of_query(activity , res);
                }
            }
        });

    }

    private static void convert_parse(){
        reports = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Report room = new Report();
                room.null_self_fixer(parseObject);
                reports.add(room);
            }
        }
    }

    private void null_self_fixer (ParseObject parseObject){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t;
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

                case obj_state:
                    m = parseObject.getInt(obj_state);
                    state = State.fromInteger(m);
                    break;
                case obj_matn:
                    t = parseObject.get(obj_matn).toString();
                    if (t != null){
                        matn = t;
                    }
                    break;
                case obj_sender_id:
                    t = parseObject.get(obj_sender_id).toString();
                    if (t != null){
                        sender_id = t;
                    }
                    break;
                case obj_reporttype_id:
                    t = parseObject.get(obj_reporttype_id).toString();
                    if (t != null){
                        report_type = Report_type.get(t);
                    }
                    report_type = Report_type.get(parseObject.get(obj_reporttype_id).toString());
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
        reports = null;
        temp   = null;
        isloaded = false;
    }

    @Override
    public String toString() {
        return name;
    }




}
