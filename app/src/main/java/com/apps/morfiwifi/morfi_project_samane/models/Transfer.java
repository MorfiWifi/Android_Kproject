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

public class Transfer {
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


    public Khabgah current_kh = new Khabgah();
    public Khabgah new_kh = new Khabgah();
    public Block current_bl = new Block();
    public Block new_bl = new Block();
    public Room current_room = new Room();
    public Room new_room = new Room();
    public String Id = Init.Empty;
    public String sender_id  = Init.Empty;
    public String current_kh_id  = Init.Empty;
    public String new_kh_id  = Init.Empty;
    public String current_block_id  = Init.Empty;
    public String new_block_id  = Init.Empty;
    public String current_room_id  = Init.Empty;
    public String new_room_id  = Init.Empty;
    public Date transfer_date  = Calendar.getInstance().getTime();
    public int state_int = 0;
    public Date createAt = Calendar.getInstance().getTime();
    public State state = State.fromInteger(0);

    public static final int CODE = 20;
    public static final int CODE_ALL = 21;
    public static final int CODE_SEND = 22;

    private static String class_name = "transfer";
    private final static String obj_state = "state";
    private final static String obj_id = "id";
    private final static String obj_createAt = "crateAt";
    private final static String obj_sender_id = "sender_id";
    private final static String obj_current_kh = "current_kh";
    private final static String obj_current_blook = "current_blook";// Block is correct
    private final static String obj_current_room = "current_room";
    private final static String obj_new_kh = "new_kh";
    private final static String obj_new_blook = "new_blook";
    private final static String obj_new_room = "new_room";
    private final static String obj_transfer_date = "date_of_transfer";

    private static boolean isloaded = false;
    private static List<ParseObject> temp;
    private static  List<Transfer> transfers;
    private static int limit = 100;
    private static String[] all_params = {obj_state ,obj_current_kh ,obj_id ,obj_createAt ,obj_sender_id
            , obj_current_blook ,obj_current_room ,obj_new_kh,obj_new_blook,obj_new_room,obj_transfer_date};

    public static void load_self_transfers (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
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

                        result = new Result(transfers, CODE , true);
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
            Result result = new Result(transfers, CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_transfers (final AppCompatActivity activity , final boolean draw_loading ){ // laod all of them ...
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

                    result = new Result(transfers, CODE_ALL , true);
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

    public static void send_self_transfer(final AppCompatActivity activity , final boolean draw_loading , String current_kh_id ,
            String current_block_id , String current_room_id , String new_kh_id , String new_block_id ,
            String new_room_id, Date date ){

        if (draw_loading)
            Init.start_loading(activity);
        ParseObject report = new ParseObject(class_name);
        String sender_id = Init.Empty;
        if (User.current_user != null)
            sender_id  = User.current_user.id;

        report.put(obj_sender_id , sender_id);
        report.put(obj_current_kh , current_kh_id);
        report.put(obj_current_blook , current_block_id);
        report.put(obj_current_room , current_room_id);
        report.put(obj_new_kh , new_kh_id);
        report.put(obj_new_blook , new_block_id);
        report.put(obj_new_room , new_room_id);
        report.put(obj_transfer_date , date);
        report.put(obj_state , 0);



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


    public static List<Transfer> load_transfers_fog (boolean force){ // laod all of them FORCED FORGE GROUND BLOCK >>>>
        if (isloaded && !force){
            return transfers;
        }

        isloaded = false; // GETTING NEWER VERSION! NOT READY YET
        List <Transfer> transferList = new ArrayList<>();
        ParseQuery query = new ParseQuery(class_name);
        query.setLimit(limit);

        try {
            temp = query.find();
            convert_parse();
            transferList = transfers;
            isloaded = true;
        } catch (ParseException e) {
            Log.e("EXCEPTiON ON RECIVE :", e.getMessage());
        }
        return transferList;
    }
    private static void convert_parse(){
        transfers = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Transfer transfer = new Transfer();
                transfer.null_self_fixer(parseObject);
                transfers.add(transfer);
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
                case obj_current_kh:
                    t = Init.Empty;
                    object = parseObject.get(obj_current_kh);
                    if (object != null){
                        t = object.toString();
                    }
                    current_kh_id = t;
                    current_kh = Khabgah.get(t);
                    break;
                case obj_current_blook:
                    t = Init.Empty;
                    object = parseObject.get(obj_current_blook);
                    if (object != null){
                        t = object.toString();
                    }
                    current_block_id = t;
                    current_bl =Block.get(t);
                    break;
                case obj_current_room:
                    t = Init.Empty;
                    object = parseObject.get(obj_current_room);
                    if (object != null){
                        t = object.toString();
                    }
                    current_room_id = t;
                    current_room = Room.get(t);
                    break;
                case obj_new_kh:
                    t = Init.Empty;
                    object = parseObject.get(obj_new_kh);
                    if (object != null){
                        t = object.toString();
                    }
                    new_kh_id = t;
                    new_kh = Khabgah.get(t);
                    break;
                case obj_new_blook:
                    t = Init.Empty;
                    object = parseObject.get(obj_new_blook);
                    if (object != null){
                        t = object.toString();
                    }
                    new_block_id = t;
                    new_bl =Block.get(t);
                    break;
                case obj_new_room:
                    t = Init.Empty;
                    object = parseObject.get(obj_new_room);
                    if (object != null){
                        t = object.toString();
                    }
                    new_room_id = t;
                    new_room = Room.get(t);
                    break;
                case obj_transfer_date:
                    object = parseObject.getDate(obj_transfer_date);
                    if (object != null){
                        transfer_date = parseObject.getDate(obj_transfer_date);
                    }
                    break;
                default:
                    break;


            }
        }

    }
    public static void Clear(){
        transfers = null;
        temp   = null;
        isloaded = false;
    }
    public static Transfer get(String id) {
        // t is id
        // if is loaded find where eq else query force foreground
        if (transfers == null){
            load_transfers_fog(false);
        }

        Transfer transfer1 = new Transfer();
        if (transfers != null){
            for (Transfer transfer : transfers) {
                if (transfer.Id.equals(id)){
                    transfer1 = transfer;
                    break;
                }
            }
        }
        return transfer1;
    }
}
