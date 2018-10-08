package com.apps.morfiwifi.morfi_project_samane.models;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Feedback {
    public static void set_new_state(final AppCompatActivity activity, String id, final int ordinal) {


        ParseQuery query = new ParseQuery(class_name);
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null){
                    object.put(obj_state , ordinal);

                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e== null){
                                Result result = new Result("CANCELATION ENHANCED" , CODE_CHAINGED, true);
                                Init.result_of_query(activity ,result );
                            }else {

                            }
                        }
                    });



                }else {

                }
            }
        });
    }

    public enum  State{
        open , working_on  , finished  ;

        public static State fromInteger(int x) {
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

    public String Id = Init.Empty;
    public String sender_id  = Init.Empty;
    public String content  = Init.Empty;
    public String header   = Init.Empty;
    public int state_int = 0;
    public Date createAt = Calendar.getInstance().getTime();
    public State state = State.fromInteger(0);

    public static final int CODE = 5;
    public static final int CODE_ALL = 6;
    public static final int CODE_SEND = 7;
    public static final int CODE_CHAINGED = 2007;

    private static String class_name = "feedback";
    private final static String obj_state = "state";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_sender_id = "sender_id";
    private final static String obj_header = "header";
    private final static String obj_content = "content";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Feedback> cancellations;
    private static int limit = 100;
    private static String[] all_params = {obj_state ,obj_header ,obj_id ,obj_createAt ,obj_sender_id , obj_content  };

    public static void load_self_feedbacks (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);


            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
            ParseQuery query = new ParseQuery(class_name);
            query.whereEqualTo(obj_sender_id , User.current_user.id);
            query.setLimit(limit);
            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){

                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result(cancellations, CODE , true);
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
            Result result = new Result(cancellations, CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_feedbacks (final AppCompatActivity activity , final boolean draw_loading ){ // laod all of them ...
        // NO cache advised
        if (draw_loading)
            Init.start_loading(activity);
        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>(){
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Result result;
                if (e == null){

                    temp = objects;
                    if (objects.size() <= 0)
                        temp = new ArrayList<ParseObject>();
                    convert_parse();

                    result = new Result(cancellations, CODE_ALL , true);
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

    public static void send_self_feedback(final AppCompatActivity activity , final boolean draw_loading , String header , String content ){
        if (draw_loading)
            Init.start_loading(activity);
        ParseObject report = new ParseObject(class_name);
        String sender_id = Init.Empty;
        if (User.current_user != null)
            sender_id  = User.current_user.id;

        report.put(obj_sender_id , sender_id);
        report.put(obj_header ,header);
        report.put(obj_state , 0);
        report.put(obj_content , content);


        report.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Result res;
                if (e== null){
                    res = new Result( "DON" , CODE_SEND , true);
                    Init.result_of_query(activity , res);

                    if (draw_loading)
                        Init.stop_loading(activity);
                }else {
                    res = new Result(e , CODE_SEND);
                    if (draw_loading)
                        Init.stop_loading(activity);
                    Init.result_of_query(activity , res);
                }
            }
        });

    }


    public static List<Feedback> load_feedback_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return cancellations;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Feedback> feedbackList = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.orderByDescending("createdAt");
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            feedbackList = cancellations;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return feedbackList;
    }
    private static void convert_parse(){
        cancellations = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Feedback cancellation = new Feedback();
                cancellation.null_self_fixer(parseObject);
                cancellations.add(cancellation);
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

                case obj_state:
                    object = parseObject.get(obj_state);
                    if (object != null){
                        state_int = parseObject.getInt(obj_state);
                        state = State.fromInteger(state_int);
                    }
                    break;
                case obj_sender_id:
                    t = Init.Empty;
                    object = parseObject.get(obj_sender_id);
                    if (object != null){
                        t = object.toString();
                    }
                    sender_id = t;
                    break;
                case obj_header :
                    t = Init.Empty;
                    object = parseObject.get(obj_header);
                    if (object != null){
                        t = object.toString();
                    }
                    header = t;
                    break;
                case  obj_content:
                    t = Init.Empty;
                    object = parseObject.get(obj_content);
                    if (object != null){
                        t = object.toString();
                    }
                    content = t;
                    break;
                default:
                    break;


            }
        }

    }
    public static void Clear(){
        cancellations = null;
        temp   = null;
        isloaded = false;
    }
    public static Feedback get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (cancellations == null){
            load_feedback_fog(false);
        }

        Feedback feedback = new Feedback();
        if (cancellations != null){
            for (Feedback feedback1 : cancellations) {
                if (feedback1.Id.equals(id)){
                    feedback = feedback1;
                    break;
                }
            }
        }
        return feedback;
    }
}
