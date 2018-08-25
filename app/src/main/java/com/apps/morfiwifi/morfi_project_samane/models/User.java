package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.Date;
import java.util.List;

/**
 * Created by WifiMorfi on 3/20/2018.
 */


//@Entity
public class User   {

    public static final int CODE_SEND = 28;
    public static final int CODE_EXIST_DATA = 29;
//    public static String Ha= 29;

//    @Transient
    public static String class_name = "User"; // name of Table in parse
//    @Transient
    public static  User current_user; // for different tasks
//    @Transient
    public String Role;
//    @Transient
    public String Role_id;
//    @Transient
    public  String id;
    private int type;

    public int getType() {
        return type;
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
    public String UserName;
    public String FName;
    public String LName;
    public String Pass;
    public String Pass_hash;
    public String Kaet_meli;
    public String Student_id = "0" ; // from Uni
    public boolean should_fill_init_forms = true;
    public boolean Active = false;
    public boolean Deleted = false;
    public boolean PreActive = false;
    public int Type;
    public Date inset_date;

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

        /// Debugign thing !
        if (userName.equals("max") && pass.equals("max")){
//            Intent intent = new Intent(activity , SignupStudent2Activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            activity.startActivity(intent);
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


                    // TODO: 7/9/2018 Do what ever tekes its log in !
                    parseUser.getUsername();
//                    Object object =  parseUser.get("role_id");
                    String role_id = parseUser.get("role_id").toString();

//                    Toast.makeText(activity, role_id, Toast.LENGTH_SHORT).show();

                    // GET ROLE
                    try {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("role");
                    ParseObject Role_obj ;
                    String Role_name;


                        ParseObject object = query.get(role_id);

                        { // FIXME: 7/12/2018 Do we have section in JAVA??
                            if (object != null){
                                Role_name  = object.get("name").toString();
                                current_user.Role = Role_name;
                                current_user.Role_id = object.getObjectId();
//                                    Toast.makeText(activity, Role_name, Toast.LENGTH_SHORT).show();
                                if(activity instanceof LoginActivity){
                                    ((LoginActivity) activity).login_server(current_user);
                                }
                            }else {
                                Toast.makeText(activity, "EX " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }

                    } catch (Exception e1) {
                        Init.Terminal(e1.getMessage());
                    }



                    // GET ROLE


                    if (current_user.Role != null){
//                        Toast.makeText(activity, "OK YOU ARE IN !", Toast.LENGTH_SHORT).show();
                    }else {
//                        Init.Terminal(current_user.Role + " Role KEY !");
                    }


                    Init.stop_loading(activity);
                } else {
                   // Toast.makeText(LoginActivity.this, "Error in Log in" + e.getMessage() , Toast.LENGTH_SHORT).show();
                    //Login Fail
                    //get error by calling e.getMessage()
                    // TODO: 7/9/2018  Log in Faild due to message (EX)
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

    public static void chech_student (User user){
        ParseQuery query = new ParseQuery("User");
        query.whereEqualTo(user.UserName , user.UserName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject>  objects, ParseException e) {
                if (e== null){
                    if (objects == null){
                        // TODO: 8/17/2018 EVERY THING OK!
                        Log.d("SEND USER :" , "NULL IS THE LIST ERROR");
                        Result result = new Result(new Exception("NULL DATA RECIVES") , CODE_SEND);
                    }else {
                        if (objects.size() == 0){
                            Log.d("SEND USER :" , "NO PROBLEM OK");
                            Result result = new Result(null , CODE_SEND , true);
                        }else {
                            Log.d("SEND USER :" , "Alredy EXISTING THINGS");
                            Result result = new Result(null , CODE_EXIST_DATA , false);
                        }

                    }
                }else {
                    Result result = new Result(e , CODE_SEND);
                    Log.d("SEND USER :" , e.getMessage());
                }
            }
        });
    }

    private void chech_prop (Properties properties){

    }



}
