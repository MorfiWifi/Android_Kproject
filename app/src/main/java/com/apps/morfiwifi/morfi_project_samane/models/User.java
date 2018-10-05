package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.ui.others.TestActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by WifiMorfi on 3/20/2018.
 */


//@Entity
public class User   {

    private static  String maxiwell;
    static {
        User.maxiwell = "Adsda";
    }

    public static final int CODE_SEND = 99;
    public static final int COD_CHECKING = 98;
    public static final int CODE_EXIST_DATA = 29;
    public static final int CODE_NON_ACTIVE_STD = 75;
    public static final int CODE_STD_ACTIVATED = 56;
    public static final int CODE_MUSER = 466;
    public static final int CODE_STD_DELETED = 86; // not really - disabled
    public static String OK = "OK";


//    public static String Ha= 29;

//    @Transient
    public static final String class_name = "User"; // name of Table in parse
    public static final String obj_muser = "MUSER"; // name of Table in parse USER MASTER INFO
//    private static final String obj_name = "User"; // name of Table in parse
    private static final String obj_id = "id"; // name of Table in parse
//    private static final String obj_Lname = "User"; // name of Table in parse
    private static final String obj_Uname = "username"; // name of Table in parse
    private static final String obj_createAt = "CreateAt"; // name of Table in parse
    private static final String obj_role_id = "role_id"; // name of Table in parse
    private static final String obj_activate = "activate"; // name of Table in parse
    private static final String obj_preactive = "preactive"; // name of Table in parse
    private static final String obj_email = "email"; // name of Table in parse
    private static final String obj_password = "password"; // name of Table in parse
    private static final String obj_isdelete= "isdelete"; // name of Table in parse
    private static final String obj_user_id= "user_id"; // name of Table in parse
    private static String[] all_params = {obj_createAt,obj_Uname ,obj_role_id ,obj_id , obj_activate,obj_preactive , obj_email , obj_password,obj_isdelete };


//    @Transient
    public static  User current_user; // for different tasks
    public   ParseObject current_muser_pars; // for different tasks

    public static  MUSER curent_muser; // for different tasks
//    @Transient
    public String Role;
//    @Transient
    public String Role_id;
//    @Transient
    public  String id;
    public  String email;
    public boolean deleted = false;
    public Date createAt = Calendar.getInstance().getTime();
    private int type;
    private static List<ParseUser> temp_nonactive;
    public MUSER muser = new MUSER();

    public int getType() {
        return type;
    }
    private  static boolean isloaded_std_nonactive =false;

    public static void active_std(final AppCompatActivity activity , final boolean draw_loading, User user ) {
//        final ParseQuery parseUser = ParseUser.getQuery();
        if (draw_loading)
            Init.start_loading(activity);

        user.current_muser_pars.put(obj_activate , true);
        user.current_muser_pars.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    // GOOD JOB ! SIR
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Result result = new Result("ACTIVATED" , CODE_STD_ACTIVATED , true);
                        Init.result_of_query(activity , result);
                    } else {
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Result result = new Result(e , CODE_STD_ACTIVATED );
                        Init.result_of_query(activity , result);
                }
            }
        });
    }

    public static void delete_std(final AppCompatActivity activity , final boolean draw_loading, User user ) {
//        final ParseQuery parseUser = ParseUser.getQuery();
        if (draw_loading)
            Init.start_loading(activity);

        user.current_muser_pars.put(obj_isdelete , true);
        user.current_muser_pars.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    // GOOD JOB ! SIR
                    if (draw_loading)
                        Init.stop_loading(activity);
                    Result result = new Result("ACTIVATED" , CODE_STD_DELETED , true);
                    Init.result_of_query(activity , result);
                } else {
                    if (draw_loading)
                        Init.stop_loading(activity);
                    Result result = new Result(e , CODE_STD_DELETED );
                    Init.result_of_query(activity , result);
                }
            }
        });
    }

    public static void user_info_dialogue(final AppCompatActivity activity, final String user_id) {
        ParseQuery query = ParseUser.getQuery();

        Init.start_loading(activity);

        query.getInBackground(user_id, new GetCallback<ParseUser>() {

            @Override
            public void done(final ParseUser object, ParseException e) {
                if (e == null ){
                    ParseQuery query1 = new ParseQuery(Properties.class_name);
                    query1.whereEqualTo(Properties.obj_user_id , user_id);
                    query1.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List objects, ParseException e) {
                            if (e == null){
                                Properties properties = new Properties();
                                if (objects.size() < 1){ // we hame no props

                                }else { // we hame more then 0 props
//                                    Properties properties = new Properties();
                                    ParseObject first = (ParseObject) objects.get(0);
                                    properties.use_khabgah = first.getBoolean(Properties.obj_is_using_kh);
                                    properties.id = first.getObjectId();
                                    properties.real_name = first.get(Properties.obj_real_name).toString();
                                    properties.real_lastname = first.get(Properties.obj_real_lastname).toString();
                                    properties.father_name = first.get(Properties.obj_father_name).toString();
                                    properties.national_cod = first.get(Properties.obj_national_cod).toString();
                                }

                                Init.stop_loading(activity);
                                User user = new User();
                                user.UserName = object.getUsername();

                                Dialogue.load_user_info(activity , user , properties);

                            }else {
                                Init.stop_loading(activity);
                                Log.d("GETting prop inf :" , e.getMessage());
                            }

                        }

                    });



                }else {
                    Init.stop_loading(activity);
                    Log.d("GETting USER inf :" , e.getMessage());
                }
            }
        });






    }


    public enum Kind {
        Student, Master, Technical, Site_Master, Self_Service, Admin ;


        public static Kind fromInteger(int x) {
            int count = values().length;
            return values()[x % count];
        }

        @Override
        public String toString() {
            switch (this){
                case Student:
                    return "دانشجو";
                case Master:
                    return "استاد";
                case Technical:
                    return "مسئول فنی";
                case Site_Master:
                    return "مسئول سایت";
                case Self_Service:
                    return "مسئول سلف";
                case Admin:
                    return "ادمین سیستم";
                default:
                    return "مشخص نیست";
            }

            //return super.toString();
        }
    }

    public Kind get_Type (){
        return Kind.fromInteger(this.Type);
    }

    public void set_Type (Kind kind){
        this.Type = kind.ordinal();
    }


    //private static RetrofitDataService mTService;
//    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    public Long Id;
    public String UserName = Init.Empty;
    public String FName = Init.Empty;
    public String LName = Init.Empty;
    public String Pass = Init.Empty;
    public String Pass_hash = Init.Empty;
    public String Kaet_meli = Init.Empty;
    public String Student_id = "0" ; // from Uni
    public boolean should_fill_init_forms = true;
    public boolean Active = false;
    public boolean Deleted = false;
    public boolean PreActive = false;
    public int Type;
    public Date inset_date;
    public int cod = 0;
    private static  List<User> std_nonactive;
    private static  List<ParseObject> temp_musers;
    private static  List<MUSER> std_nonactive_muser;
    public List<Samane> samanes;



//    @Generated(hash = 316889759)
    public User(Long Id, String UserName, String FName, String LName, String Pass, String Pass_hash,
            String Kaet_meli, String Student_id, boolean should_fill_init_forms, boolean Active,
            boolean Deleted, boolean PreActive, int Type, Date inset_date) {
        this.Id = Id;
        this.UserName = UserName;
        this.FName = FName;
        this.LName = LName;
        this.Pass = Pass;
        this.Pass_hash = Pass_hash;
        this.Kaet_meli = Kaet_meli;
        this.Student_id = Student_id;
        this.should_fill_init_forms = should_fill_init_forms;
        this.Active = Active;
        this.Deleted = Deleted;
        this.PreActive = PreActive;
        this.Type = Type;
        this.inset_date = inset_date;
    }

//    @Generated(hash = 586692638)
    public User() {
    }




    public void setType(int Type) {
        this.Type = Type;
    }

    public Kind getKind() {
        return Kind.values()[getType() % Kind.values().length];
    }

    public void setkind(Kind kinde){
        Kind[] vals = Kind.values();
        setType(0);
        for (int i = 0; i <vals.length ; i++) {
            if (vals[i].equals(kinde)){
                setType(i);
                break;
            }
        }
    }


    

    //  PART FOT USING PARSE SERVER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static  void login(String userName , final String pass , final AppCompatActivity activity){

        Init.start_fresh();
        /// Debugign thing !
        if (userName.equals("max") && pass.equals("max")){
            Intent intent = new Intent(activity , TestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            return;
        }



        Init.start_loading(activity);


        ParseUser.logInInBackground(userName, pass, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    current_user  = new User();
                    current_user.Role = null;
                    current_user.UserName =(parseUser.getUsername()); // TODO: 7/10/2018  Fild,s You need in User
                    current_user.id = parseUser.getObjectId();
                    current_user.PreActive = parseUser.getBoolean("preactive");
                    current_user.Active = parseUser.getBoolean("activate");


                    // TODO: 7/9/2018 Do what ever tekes its log in !
                    parseUser.getUsername();
//                    Object object =  parseUser.get("role_id");
                    String role_id = parseUser.get("role_id").toString();
                    Properties.load_self_properties(activity , true , false);

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("role");
                    ParseObject Role_obj ;
                    String Role_name;
//                    Init.stop_loading(activity);

                    query.getInBackground(role_id, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (e == null){

                                if (object != null) {
                                    current_user.Role = object.get("name").toString();
                                    current_user.Role_id = object.getObjectId();
                                    current_user.cod = object.getInt("cod");

                                    final ParseQuery mus = new ParseQuery(obj_muser);
                                    mus.whereEqualTo(obj_user_id , current_user.id);
                                    mus.getFirstInBackground(new GetCallback<ParseObject>() {
                                        @Override
                                        public void done(ParseObject object, ParseException e) {
                                            if (e == null){
                                                User.curent_muser = new MUSER(object.getBoolean(obj_activate) ,
                                                        object.getBoolean(obj_preactive) , object.getBoolean(obj_isdelete));
                                                current_user.muser =curent_muser;
                                                Init.stop_loading(activity);
                                                if(activity instanceof LoginActivity){
                                                    ((LoginActivity) activity).login_server(current_user);
                                                }
                                            }else {
                                                Toast.makeText(activity, "خطا در ورود", Toast.LENGTH_SHORT).show();
                                                Log.d("MUSER get EX" , e.getMessage());
                                                Init.stop_loading(activity);
                                            }
                                        }
                                    });
                                }
                            }else {
                                Toast.makeText(activity, "خطا در ورود", Toast.LENGTH_SHORT).show();
                                Log.d("ROLE get EX" , e.getMessage());
                                Init.stop_loading(activity);
                            }
                        }
                    });

                    // GET ROLE


                    if (current_user.Role != null){
//                        Toast.makeText(activity, "OK YOU ARE IN !", Toast.LENGTH_SHORT).show();
                    }else {
                        Init.stop_loading(activity);
//                        Init.Terminal(current_user.Role + " Role KEY !");
                    }



                } else {
                   // Toast.makeText(LoginActivity.this, "Error in Log in" + e.getMessage() , Toast.LENGTH_SHORT).show();
                    //Login Fail
                    //get error by calling e.getMessage()
                    // TODO: 7/9/2018  Log in Faild due to message (EX)
                    Log.d("EX IN LOG IN" , e.getMessage());
                    Toast.makeText(activity, "خطا در ورود", Toast.LENGTH_SHORT).show();
                    Init.stop_loading(activity);
                }
            }
        });


    }

    public static void getall_active_users (){
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(class_name);
            query.whereEqualTo("activate" , true);

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null){

                    }else {
                     Init.Terminal(e.getMessage() + " exception in get all users list");
                    }
                }
            });

            // TODO: 7/11/2018 Do what ever it takes from users


        } catch (Exception e1) {
            Init.Terminal(e1.getMessage());
        }



    }

    public static void getall_need_active_students (final AppCompatActivity activity , final boolean draw_loading , boolean force_new){

        if (force_new || !isloaded_std_nonactive) { // don't have cache or forced
            if (draw_loading)
                Init.start_loading(activity);
            ParseQuery<ParseObject> query = new ParseQuery(obj_muser);
            query.whereNotEqualTo(obj_activate, true);
            query.whereNotEqualTo(obj_isdelete, true);

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(final List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();

                        for (ParseObject parseObject : objects) {
                            ParseQuery<ParseUser> query1 = ParseUser.getQuery();
                            Log.d("USER ID NO ACTIVE :" , parseObject.get(obj_user_id).toString() );
                            query1.whereEqualTo("objectId", parseObject.get(obj_user_id).toString());
                            queries.add(query1);
                        }

                        if (queries.size() == 0){
                            if (draw_loading)
                                Init.stop_loading(activity);
                            Result result = new Result(std_nonactive = new ArrayList<>(), CODE_NON_ACTIVE_STD, true);
                            Init.result_of_query(activity, result);
                            Log.d("NON ACTIVE STD :" , "THE LIST OF QUERYES IS EMPTY");
                            return;
                        }

                        ParseQuery<ParseUser> mainQuery = ParseQuery.or(queries);
                        mainQuery.findInBackground(new FindCallback<ParseUser>() {
                            public void done(List<ParseUser> results, ParseException e) {
                                if (e == null) {

                                    temp_nonactive = results;
                                    convert_parse_nonactive();
                                    isloaded_std_nonactive = true;

                                    if (draw_loading)
                                        Init.stop_loading(activity);
                                    for (User use: std_nonactive) {
                                        for (ParseObject mus: objects) {
                                            if (mus.get(obj_user_id).toString().equals(use.id)){
                                                use.current_muser_pars = mus;
                                            }
                                        }
                                    }



                                    Result result = new Result(std_nonactive, CODE_NON_ACTIVE_STD, true);
                                    Init.result_of_query(activity, result);



                                } else {
                                    if (draw_loading)
                                        Init.stop_loading(activity);
                                    Result result = new Result(e, CODE_NON_ACTIVE_STD);
                                    Init.result_of_query(activity, result);
                                    Init.Terminal(e.getMessage() + " exception in get all users list");
                                }
                            }
                        });


                    } else {
                        if (draw_loading)
                            Init.stop_loading(activity);
                        Result result = new Result(e, CODE_NON_ACTIVE_STD);
                        Init.result_of_query(activity, result);
                        Init.Terminal(e.getMessage() + " exception in get all users list");
                    }
                }
            });
        }
    }

    public void inser_student (){
        User user ;
        Properties properties;
        // First WE insert User THEN Properties
    }

    private String sequre (String input){
        return input;
    }

    private String nu_sequre (String input){
        return input;
    }

    public static void chech_user (User user){
        ParseQuery query = new ParseQuery("User");
        query.whereEqualTo("User_name" , user.UserName);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e!= null){
                    if (objects == null){
                        // TODO: 8/17/2018 EVERY THING OK!

                    }else {

                    }
                }else {
                    // TODO: 8/17/2018 SOME EROR IN USERS
                }
            }
        });
    }

    public static void insert_user (final AppCompatActivity activity , final boolean draw_loading  , User user , final Properties properties ){
        if (properties == null) {
            // its a normal User insert non Student!
        }

        final ParseUser parseUser = new ParseUser();
        parseUser.setPassword(user.Pass);
        parseUser.setUsername(user.UserName);
        parseUser.put(obj_activate, user.Active);
        parseUser.put(obj_preactive , user.PreActive);
        parseUser.put(obj_role_id , user.Role_id);
        if (properties != null){
//            parseUser.setACL(new ParseACL());
            ParseACL acl =new ParseACL();
            acl.setPublicWriteAccess(true);
            acl.setPublicReadAccess(true);
            parseUser.setACL(acl);//bug this SHOULD BE RE MAINTAINDE _SEQURITY BRIDGE !
        }


        // TODO: 8/30/2018 check properites and upload them too

        if (draw_loading)
            Init.start_loading(activity);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null){
                    Log.d("INSER USER ID : " ,parseUser.getObjectId() );





                    Result result = new Result(OK , CODE_SEND , true);
                    Init.result_of_query(activity , result);
                    Log.d("INSERT USER :" , "SUCSESS USER INSERT");
                    if (draw_loading)
                        Init.stop_loading(activity);



                    if (properties != null) {
                        User.insert_muser (activity ,false , false , false, false , parseUser.getObjectId() );
                        properties.user_id = parseUser.getObjectId();
                        Properties.insert_properties(activity , true , properties);
                    }else {
                        User.insert_muser (activity ,false , true , false, false , parseUser.getObjectId() );
                    }

                }else {
                    Log.d("INSERT USER :" , e.getMessage());
                    Result result = new Result(e , CODE_SEND );
                    Init.result_of_query(activity , result);
                    if (draw_loading)
                        Init.stop_loading(activity);
                }
            }
        });
    }

    private static void insert_muser(final AppCompatActivity activity , boolean draw_loading , boolean active, boolean preActive, boolean deleted  , String user_id ) {
        if (draw_loading)
            Init.start_loading(activity);


        ParseObject parseObject = new ParseObject(obj_muser);
        parseObject.put(obj_preactive , preActive);
        parseObject.put(obj_user_id , user_id);
        parseObject.put(obj_activate , active);
        parseObject.put(obj_isdelete , deleted);

        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e== null){
                    Result result = new Result("MUSER SAVED",CODE_MUSER ,true );
                    Init.result_of_query(activity , result);
                    Log.d("INSERT MUSER :" , "muser saved");
                }else {
                    // abort thing's if you can
                    Log.d("INSERT MUSER EX :" , e.getMessage());
                }
            }
        });

    }

    public static void chech_student (final AppCompatActivity activity , final boolean draw_loading  ,User user){
        ParseQuery query = new ParseQuery("User");
        query.whereEqualTo(User.obj_Uname , user.UserName);
        if (draw_loading)
            Init.start_loading(activity);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject>  objects, ParseException e) {
                if (e== null){
                    if (objects == null){
                        // TODO: 8/17/2018 EVERY THING OK!
                        Log.d("SEND USER :" , "NULL IS THE LIST ERROR");
                        Result result = new Result(new Exception("NULL DATA RECIVES") , COD_CHECKING);
                        Init.result_of_query(activity , result);
                        if (draw_loading)
                            Init.stop_loading(activity);
                    }else {
                        if (objects.size() == 0){
                            Log.d("SEND USER :" , "NO PROBLEM OK");
                            Result result = new Result(null , COD_CHECKING , true);
                            Init.result_of_query(activity , result);
                            if (draw_loading)
                                Init.stop_loading(activity);





                        }else {
                            Log.d("SEND USER :" , "Alredy EXISTING THINGS");
                            Result result = new Result(null , CODE_EXIST_DATA , false);
                            Init.result_of_query(activity , result);
                            if (draw_loading)
                                Init.stop_loading(activity);
                        }

                    }
                }else {
                    Result result = new Result(e , CODE_SEND);
                    Log.d("SEND USER :" , e.getMessage());
                    Init.result_of_query(activity , result);
                    if (draw_loading)
                        Init.stop_loading(activity);
                }
            }
        });
    }

    private void chech_prop (Properties properties){

    }

    private static void convert_parse_nonactive(){
        std_nonactive = new ArrayList<>();
        if (temp_nonactive != null){
            for (ParseUser parseUser : temp_nonactive) {
                User user = new User();
                user.null_self_fixer(parseUser);
                std_nonactive.add(user);
            }
        }
    }

    private void null_self_fixer (ParseUser parseUser){
        // TODO: 7/25/2018 COMPLET OBJECT BUILDIGN SYS
        String t;
        Object object;
        int m = 0;
        for (String param: all_params) {
            switch (param){
                case obj_id :
                    t = parseUser.getObjectId();
                    if (t != null){
                        id = t;
                    }
                    break;
                case obj_email :
                    t = Init.Empty;
                    object = parseUser.get(obj_email);
                    if (object != null){
                        t = object.toString();
                    }
                    email = t;
                    break;
                case obj_createAt:
                    Date date = parseUser.getCreatedAt();
                    if (date != null){
                        createAt = date;
                    }
                    break;
                case obj_isdelete:
                    deleted = parseUser.getBoolean(obj_isdelete);
                    break;
                case obj_activate:
                    Active = parseUser.getBoolean(obj_activate);
                    break;
                case obj_role_id:
                    t = Init.Empty;
                    object = parseUser.get(obj_role_id);
                    if (object != null){
                        t = object.toString();
                    }
                    Role_id = t;
                    break;
                case obj_Uname:
                    t = Init.Empty;
                    object = parseUser.get(obj_Uname);
                    if (object != null){
                        t = object.toString();
                    }
                    UserName = t;
                    break;
                case obj_password:
                    t = Init.Empty;
                    object = parseUser.get(obj_password);
                    if (object != null){
                        t = object.toString();
                    }
                    Pass = t; // CANT GET THIS DOSENT EXIST ! - HASH JUST HA HA
                    break;
                default:
                    break;


            }
        }

    }



}
