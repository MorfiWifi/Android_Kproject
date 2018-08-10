package com.apps.morfiwifi.morfi_project_samane.models;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cancellation {
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
    public String reason  = Init.Empty;
    public Date canvle_date  = Calendar.getInstance().getTime();
    public int state_int = 0;
    public Date createAt = Calendar.getInstance().getTime();
    public State state = State.fromInteger(0);

    public static final int CODE = 2;
    public static final int CODE_ALL = 3;
    public static final int CODE_SEND = 4;

    private static String class_name = "cancellation";
    private final static String obj_state = "state";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_sender_id = "sender_id";
    private final static String obj_reason = "reason";
    private final static String obj_cancle_date = "cancle_date";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Cancellation> cancellations;
    private static int limit = 100;
    private static String[] all_params = {obj_state ,obj_reason ,obj_id ,obj_createAt ,obj_sender_id , obj_cancle_date  };

    public static void load_self_canclelations (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);


            isloaded = false; // GETTING NEWER VERSION! NOT READY YET
            ParseQuery query = new ParseQuery(class_name);
            query.whereEqualTo(obj_sender_id , User.current_user.id);
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

    public static void load_cancelations (final AppCompatActivity activity , final boolean draw_loading ){ // laod all of them ...
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

    public static void send_self_cancelation(final AppCompatActivity activity , final boolean draw_loading , String reason , Date date ){
        if (draw_loading)
            Init.start_loading(activity);
        ParseObject report = new ParseObject(class_name);
        String sender_id = Init.Empty;
        if (User.current_user != null)
            sender_id  = User.current_user.id;

        report.put(obj_sender_id , sender_id);
        report.put(obj_cancle_date , date);
        report.put(obj_state , 0);
        report.put(obj_reason , reason);


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


    public static List<Cancellation> load_cancelations_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return cancellations;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Cancellation> cancellationList = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            cancellationList = cancellations;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return cancellationList;
    }
    private static void convert_parse(){
        cancellations = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Cancellation cancellation = new Cancellation();
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
                case obj_reason :
                    t = Init.Empty;
                    object = parseObject.get(obj_reason);
                    if (object != null){
                        t = object.toString();
                    }
                    reason = t;
                    break;
                    case obj_cancle_date :
                        object =  parseObject.getDate(obj_cancle_date);
                        if (object != null){
                            canvle_date = parseObject.getDate(obj_cancle_date);
                        }
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
    public static Cancellation get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (cancellations == null){
            load_cancelations_fog(false);
        }

        Cancellation cancellation1 = new Cancellation();
        if (cancellations != null){
            for (Cancellation cancellation : cancellations) {
                if (cancellation.Id.equals(id)){
                    cancellation1 = cancellation;
                    break;
                }
            }
        }
        return cancellation1;
    }
}
