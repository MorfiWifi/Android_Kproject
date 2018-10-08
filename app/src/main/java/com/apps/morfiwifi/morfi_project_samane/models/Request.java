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

import javax.crypto.Cipher;

import static com.apps.morfiwifi.morfi_project_samane.models.Cancellation.CODE_CHAINGED;

//@Entity
public class Request {

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
    public int count = 0;
    public String sender_id  = Init.Empty;
    public String thing_id  = Init.Empty;
    public int state_int = 0;
    public Thing thing = new Thing();
    public boolean iscountable = false;
    public Date createAt = Calendar.getInstance().getTime();
    public State state = State.fromInteger(0);

    public static final int CODE = 15;
    public static final int CODE_ALL = 16;
    public static final int CODE_SEND = 17;
    public static final int CODE_CHAINGED = 1994;

    private static String class_name = "request";
    private final static String obj_state = "state";
    private final static String obj_count = "count";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_sender_id = "sender_id";
    private final static String obj_thing_id = "thing_id";
    private final static String obj_iscountable = "iscounti";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Request> requests;
    private static int limit = 100;
    private static String[] all_params = {obj_state ,obj_count ,obj_id ,obj_createAt ,obj_sender_id , obj_thing_id , obj_iscountable };

    public static void load_self_requests (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);

            // WARN CODE >>>>>>>>>>>>>>>>>>>
            if (!Thing.isIsloaded())
                Thing.load_things(null , false , false);
            // WARN CODE >>>>>>>>>>>>>>>>>>>

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
                        if (!Thing.isIsloaded()) {
                            Thing.load_things_fog(false);
                        }


                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result( requests , CODE , true);
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
            Result result = new Result(requests , CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_requests (final AppCompatActivity activity , final boolean draw_loading ){ // laod all of them ...
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
                    if (!Thing.isIsloaded()) {
                        Thing.load_things_fog(false);
                    }
                    temp = objects;
                    if (objects.size() <= 0)
                        temp = new ArrayList<ParseObject>();
                    convert_parse();

                    result = new Result( requests , CODE_ALL , true);
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

    public static void send_self_report(final AppCompatActivity activity , final boolean draw_loading , int count , String thing_id ){
        if (draw_loading)
            Init.start_loading(activity);
        ParseObject report = new ParseObject(class_name);
        String sender_id = Init.Empty;
        if (User.current_user != null)
            sender_id  = User.current_user.id;

        report.put(obj_sender_id , sender_id);
        report.put(obj_count , count);
        report.put(obj_state , 0);
        report.put(obj_thing_id , thing_id);


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


    public static List<Request> load_requests_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return requests;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Request> requests_in = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.orderByDescending("createdAt");
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            requests_in = requests;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return requests_in;
    }
    private static void convert_parse(){
        requests = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Request request = new Request();
                request.null_self_fixer(parseObject);
                requests.add(request);
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
                case obj_thing_id:
                    t = Init.Empty;
                    object = parseObject.get(obj_thing_id);
                    if (object != null){
                        t = object.toString();
                    }
                    thing_id = t;
                    thing = Thing.get(thing_id);
                    break;
                case obj_count:
                    count = parseObject.getInt(obj_count);
                    break;
                case obj_iscountable:
                    if (thing != null)
                        iscountable = thing.iscountable;
                default:
                    break;


            }
        }

    }
    public static void Clear(){
        requests = null;
        temp   = null;
        isloaded = false;
    }
    public static Request get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (requests == null){
            load_requests_fog(false);
        }

        Request request1 = new Request();
        if (requests != null){
            for (Request request :requests) {
                if (request.Id.equals(id)){
                    request1 = request;
                    break;
                }
            }
        }
        return request1;
    }


}
