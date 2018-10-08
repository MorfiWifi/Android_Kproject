package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.content.PeriodicSync;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.ui.student.BroadcastActivity;
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
import java.util.Objects;

import static com.google.android.gms.tasks.Tasks.await;
import static com.google.android.gms.tasks.Tasks.call;

/**
 * Created by WifiMorfi on 3/20/2018.
 */

public class Properties {

    public static final int CODE = 9; // just for indexing !
    public static final int CODE_OTHER = 78; // just for indexing !
    public static final int CODE_ALL = 10; // just for list of all
    public static final int CODE_SEND = 25;
    public static final int CODE_EXIST_DATA = 26;
    public static final int CODE_SEND_ERROR = 27;
    public static final int COD_CHECKING = 97;

    // PRE GIVE VALUE FOR NULL FIX FUTURE
    public Date createAt = Calendar.getInstance().getTime();
    public Boolean use_khabgah = false;
    public boolean is_studying = false;
    public String kh_id = Init.Empty ;
    public String user_id = Init.Empty;
    public String id = Init.Empty;
    public String blook_id = Init.Empty;
    public String room_id = Init.Empty;
    public String national_cod = Init.Empty;
    public String father_name = Init.Empty;
    public String real_name = Init.Empty;
    public String real_lastname = Init.Empty;
    public String adress = Init.Empty;
    public String phone = Init.Empty;
    public boolean isnighty = false;
    public String std_cod = Init.Empty;



    public final static String class_name = "property";
    public final static String obj_user_id = "std_id";
    public final static String obj_kh_id = "kh_id";
    public final static String obj_blook_id = "bl_id";
    public final static String obj_room_id = "oth_id";
    public final static String obj_national_cod = "kod_meli";
    public final static String obj_father_name = "father_name";
    public final static String obj_createAt = "crateAt";
    public final static String obj_id = "id";
    public final static String obj_is_studyng = "isstudying";
    public final static String obj_is_using_kh = "use_kh";
    public final static String obj_real_name = "real_name";
    public final static String obj_real_lastname = "real_lastname";
    public final static String obj_isnighty = "isnighty";
    public final static String obj_std_code = "std_code";
    public final static String obj_adrsss = "adress";
    public final static String obj_phone = "phone";

    private static String[] all_params = {obj_createAt ,obj_id ,obj_user_id ,obj_kh_id ,obj_blook_id ,obj_room_id,obj_national_cod,obj_father_name,obj_is_studyng,obj_is_using_kh,obj_real_name,obj_real_lastname , obj_adrsss , obj_phone };


    //bug we NEED ONE FOR STUDENTS AND LIST FOR HIGH RANKS !!!
    private static boolean isloaded = false;
    private static boolean is_other_loaded = false;
    private static boolean isloaded_all = false;
    private static List<ParseObject> temp;
    private static  List<Properties> propertiesList ; // For loading list of all - SECURITY FIXES REQUIRED
    private static  Properties properties ; // additional information about user !
    private static  Properties properties_other ; // additional information about user !
    private static  ParseObject tempi ; // additional information about user !
    private static  ParseObject temp_other ; // additional information about user !

    //NOTE USE LOADING FROM Init.start_loading(activity); => THIS DOSE'NT CONSIDER TYPE OF ACTIVITY !



    public static void load_properties (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){ // laod all of them ...
        if (force_new || !isloaded_all){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            isloaded_all = false;
            ParseQuery query = new ParseQuery(class_name);
            query.whereExists(obj_user_id );

            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        temp = objects;
                        if (objects.size() <= 0)
                            temp = new ArrayList<ParseObject>();
                        convert_parse();

                        result = new Result( propertiesList , CODE_ALL , true);
                        Init.result_of_query(activity , result);
                        isloaded_all = true;

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


        }else {
            if (draw_loading)
                Init.stop_loading(activity);
            Result result = new Result(propertiesList , CODE_ALL , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_self_properties (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){
        if (force_new || !isloaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            isloaded = false;
            ParseQuery query = new ParseQuery(class_name);
            query.whereEqualTo(obj_user_id , User.current_user.id);

            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        if (objects.size() > 0)
                            tempi = objects.get(0);
                        else
                            tempi = new ParseObject(class_name);
                        convert_self_parse();
                        result = new Result(properties , CODE , true);
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
            Result result = new Result(properties , CODE , true);
            Init.result_of_query(activity , result);
        }
    }

    public static void load_properties (final AppCompatActivity activity , final boolean draw_loading , boolean force_new , String user_id){
        if (force_new || !is_other_loaded){ // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            is_other_loaded = false;
            ParseQuery query = new ParseQuery(class_name);
            query.whereEqualTo(obj_user_id , user_id);

            query.findInBackground(new FindCallback<ParseObject>(){
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Result result;
                    if (e == null){
                        if (objects.size() > 0)
                            temp_other = objects.get(0);
                        else
                            temp_other = new ParseObject(class_name);
                        convert_other_parse();
                        result = new Result(properties_other , CODE_OTHER , true);
                        Init.result_of_query(activity , result);
                        is_other_loaded = true;

                        if (draw_loading)
                            Init.stop_loading(activity);

                    }else {
                        result = new Result(e , CODE_OTHER);
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Init.result_of_query(activity , result);
                    }
                }
            });


        }else {
            if (draw_loading)
                Init.stop_loading(activity);
            if (!properties_other.user_id.equals(user_id)){
                load_properties(activity , true , true , user_id); // waset wat wee want
                return;
            }
            Result result = new Result(properties_other , CODE_OTHER , true);
            Init.result_of_query(activity , result);
        }
    }

    private static void convert_self_parse() {
        properties = new Properties();
        properties.null_self_fixer(tempi);
    }
    private static void convert_other_parse() {
        properties_other = new Properties();
        properties_other.null_self_fixer(temp_other);
    }



    private static void convert_parse(){
        propertiesList = new ArrayList<>();
        if (temp != null){
            for (ParseObject parseObject : temp) {
                Properties properties = new Properties();
                properties.null_self_fixer(parseObject);
                propertiesList.add(properties);
            }
        }
    }

    public static void Clear(){
        propertiesList = null;
        temp   = null;
        tempi =  null;
        properties = null;
        isloaded = false;
        isloaded_all = false;
    }



    private void null_self_fixer (ParseObject parseObject){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t = "";
        Object object ;
        for (String param: all_params) {
            switch (param){
                case obj_std_code:
                    object = parseObject.get(obj_std_code);
                    if (object != null){
                        std_cod  = parseObject.get(obj_std_code).toString();
                    }
                    break;
                case obj_isnighty :
                    isnighty = parseObject.getBoolean(obj_isnighty);
                    break;
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

                case obj_blook_id:
                    object = parseObject.get(obj_blook_id);
                    if (object != null){
                        blook_id = parseObject.get(obj_blook_id).toString();
                    }
                    break;
                case obj_kh_id:
                    object = parseObject.get(obj_kh_id);
                    if (object != null){
                        kh_id = parseObject.get(obj_kh_id).toString();
                    }
                    break;
                case obj_room_id:
                    object = parseObject.get(obj_room_id);
                    if (object != null){
                        room_id = parseObject.get(obj_room_id).toString();
                    }
                    break;
                case obj_national_cod:
                    object =parseObject.get(obj_national_cod);
                    if (object != null){
                        national_cod = parseObject.get(obj_national_cod).toString();
                    }
                    break;
                case obj_father_name:
                    object = parseObject.get(obj_father_name);
                    if (object != null){
                        father_name = parseObject.get(obj_father_name).toString();
                    }
                    break;
                case obj_user_id:
                    object = parseObject.get(obj_user_id);
                    if (object != null){
                        user_id = parseObject.get(obj_user_id).toString();
                    }
                    break;
                case obj_is_studyng :
                    boolean b = parseObject.getBoolean(obj_is_studyng);
                    is_studying = b;
                    break;
                case obj_is_using_kh :
                    boolean c = parseObject.getBoolean(obj_is_using_kh);
                    use_khabgah = c;
                    break;
                case obj_real_name :
                    object  = parseObject.get(obj_real_name);
                    if (object != null){
                        real_name =  parseObject.get(obj_real_name).toString();
                    }

                    break;
                case obj_real_lastname :
                    object =  parseObject.get(obj_real_lastname);
                    if (object != null ){
                        real_lastname = parseObject.get(obj_real_lastname).toString();
                    }
                    break;
                case obj_adrsss :
                    object =  parseObject.get(obj_adrsss);
                    if (object != null ){
                        adress = parseObject.get(obj_adrsss).toString();
                    }
                    break;
                case obj_phone :
                    object =  parseObject.get(obj_phone);
                    if (object != null ){
                        phone = parseObject.get(obj_phone).toString();
                    }
                    break;
                    default:
                        break;


            }
        }

    }


    public static boolean isloaded_self(){
        return isloaded;
    }

    public static boolean isloaded_all(){
        return isloaded_all;
    }


    public static void check_properties(final AppCompatActivity activity , final boolean draw_loading , Properties properties){


        ParseQuery<ParseObject> national_cod_query = ParseQuery.getQuery(class_name);
        national_cod_query.whereEqualTo(obj_national_cod , properties.national_cod);

        ParseQuery<ParseObject> std_cod_query = ParseQuery.getQuery(class_name);
        std_cod_query.whereEqualTo(obj_std_code , properties.std_cod);

        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(national_cod_query);
        queries.add(std_cod_query);

        if (draw_loading)
            Init.start_loading(activity);

        ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
        mainQuery.findInBackground(new FindCallback<ParseObject>()
            { public void done(List<ParseObject> results, ParseException e) {
                // results has the list of players that win a lot or haven't won much.
                if (e == null){
                    // TODO: 8/17/2018 NO error ! check if there was any thing !
                    if (results == null ){
                        Log.d("Properties Send :" , "NOK THIS IS ERROR !");
                        Result result = new Result( new Exception("NULL DATA") , COD_CHECKING);
                        Init.result_of_query(activity , result);
                        if (draw_loading)
                            Init.stop_loading(activity);
                    }else {
                        if (results.size() == 0){
                            Log.d("Properties SEND :" , "OK - THERE IS NO PROBLEM");
                            Result result = new Result(null , COD_CHECKING , true);
                            Init.result_of_query(activity , result);
                            if (draw_loading)
                                Init.stop_loading(activity);
                        }else {
                            Log.d("Properties SEND :" , "NO THER IS EXISTINGS !");
                            Result result = new Result( null , CODE_EXIST_DATA , false);
                            Init.result_of_query(activity , result);
                            if (draw_loading)
                                Init.stop_loading(activity);
                            // Nat cod or STD cod exists
                        }
                    }

                }else {
                    Log.d("Properties Send EX 2:" , e.getMessage());
                    Result result = new Result(e , COD_CHECKING);
                    Init.result_of_query(activity , result);
                    if (draw_loading)
                        Init.stop_loading(activity);
                }
            }
            });

    }
    public static void insert_properties(final AppCompatActivity activity , final boolean draw_loading , Properties properties){

        ParseObject object = new ParseObject(class_name);
        object.put(obj_national_cod , properties.national_cod);
        object.put(obj_is_studyng , properties.is_studying);
        object.put(obj_is_using_kh , properties.use_khabgah);
        object.put(obj_father_name , properties.father_name);
        object.put(obj_std_code , properties.std_cod);
        object.put(obj_isnighty , properties.isnighty);
        object.put(obj_user_id , properties.user_id);
        object.put(obj_kh_id , properties.kh_id);
        object.put(obj_blook_id , properties.blook_id);
        object.put(obj_room_id , properties.room_id);
        object.put(obj_real_name , properties.real_name);
        object.put(obj_real_lastname , properties.real_lastname);
        object.put(obj_adrsss , properties.adress);
        object.put(obj_phone , properties.phone);

        // TODO: 8/30/2018 check properites and upload them too

        if (draw_loading)
            Init.start_loading(activity);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Result result = new Result(CODE_SEND , CODE_SEND , true);
                    Init.result_of_query(activity , result);
                    if (draw_loading)
                        Init.stop_loading(activity);
                }else {
                    Result result = new Result(e , CODE_SEND );
                    Init.result_of_query(activity , result);
                    if (draw_loading)
                        Init.stop_loading(activity);
                }

            }
        });

    }

    public static void clear_other() {
        is_other_loaded = false;
    }
}
