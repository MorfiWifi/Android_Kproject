package com.apps.morfiwifi.morfi_project_samane.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.SignupStudentsActivity;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Result;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.ActiveStudentActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.AddUserActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.CancelationQeueActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.Site_ProfileActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.StatesticActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.CancelationActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.EnteghadActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.SamanehaActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.TransferActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.ReportActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentProfileActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.technical.ProfileTechActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.technical.StdReportActivity;
import com.apps.morfiwifi.morfi_project_samane.util.MYService;
import com.apps.morfiwifi.morfi_project_samane.util.MyTestReceiver;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
///////////////////////////////////////////////////////////////////////////
// BLOOCK ZERO >> !
///////////////////////////////////////////////////////////////////////////
/**
 * Created by WifiMorfi on 12/20/2017.
 */
public class Init {
    public static boolean ADVANCE_MOD = true;
    public static String Parse_serevr = "pars";
    public static final String FRAGMENT = "FRAGMENT";
    public static String ASP_server = "ASP";
    public static String Offline = "offline";
    public static String MOD = "pars" ;
    public final static String Empty = "خالی" ;
    public final static String EXEPTION  = "SAMANE EXCEPTION : " ;

    public static String notNull (Object s){
        if (s != null){
            return s.toString();
        }else {
            return Empty;
        }
    }

    public static int  notNullInteger (Object s){
        if (s != null) {
            try {
                int x =
                        Integer.parseInt(s.toString());
                return x;
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    public static String notNull (Object s , String def){
        if (s != null){
            return s.toString();
        }else {
            return def;
        }
    }

    public static void test_notif(AppCompatActivity activity) {
        /*HashMap<String, String> test = new HashMap<>();
        test.put("message", "testing");
        test.put("customData", "abc");
        ParseCloud.callFunctionInBackground("pushChannelTest", test, new FunctionCallback<Object>() {
            @Override
            public void done(Object object, ParseException e) {
                if (e == null ){
                    Log.d("TEST NOTIF" , "CALL BACK NO ERROR");
                }else {
                    Log.d("TEST NOTIF" , e.getMessage());
                }
            }
        });*/

       /* JobScheduler jobScheduler = (JobScheduler) activity.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo jobInfo = new JobInfo.Builder(11, new ComponentName(activity, MyJobService.class))
                // only add if network access is required
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setExtras("sada" , "asdas");
                .build();

        jobScheduler.schedule(jobInfo);*/

        // Construct our Intent specifying the Service
        Intent i = new Intent(activity, MYService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        i.putExtra("receiver", receiverForTest);
        // Start the service
        activity.startService(i);

//        MessageNotification.notify(activity , "ADSSDAasda" , 65);

    }

    public static MyTestReceiver receiverForTest;
    public static void setupServiceReceiver(@NotNull final LoginActivity loginActivity) {
        receiverForTest = new MyTestReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        receiverForTest.setReceiver(new MyTestReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == loginActivity.RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(loginActivity, resultValue, Toast.LENGTH_SHORT).show();
                    Log.d("SERVICE :" , "RECIVER IN INIT CLASS !");
                }
            }
        });
    }


/*

    public static void FIX_PROP_ID(User user_glob, Properties properties_glob) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username" , user_glob.UserName);
        try {
            List<ParseUser> users = new ArrayList<>();
            users = query.find();
            String id = users.get(0).getObjectId();

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("property");
            query2.whereEqualTo("std_code" , properties_glob.std_cod);
            List<ParseObject> props = new ArrayList<>();
            props = query2.find();

            String id_p = props.get(0).getObjectId();
            props.get(0).put("std_id" , user_glob.id);
            props.get(0).save();


        }catch (Exception e){
            Log.d("EXCEPTION", e.getMessage() );

        }


        ParseUser parseUser = new ParseUser();
    }
*/

    public enum Mod {
        cancelation , feedback , transfer , request
    }

    public static User current_login;
    private static ProgressDialog loading;


    public static  void Print_mark(){
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MNssssssyMMMMMhssssssmMMMMMMMdssssshMMMMMMMNssssyyyyyydmNMMM");
        System.out.println("Mm       sMMMm       sMMMMMMm`     `dMMMMMMd            -sNM");
        System.out.println("Mm       `NMM/       sMMMMMN-   `   .NMMMMMd     +o+/`    :N");
        System.out.println("Mm    +   oMd   +    sMMMMM/   `h`   :MMMMMd     dMMMm`    o");
        System.out.println("Mm    h:  `m:  `m    sMMMMs    sMs    oMMMMd     dMMMM/    :");
        System.out.println("Mm    hd   :   oM    sMMMd`   .hdh.    yMMMd     dMMMM:    :");
        System.out.println("Mm    hM/     `NM    sMMm.             `mMMd     dNNms     y");
        System.out.println("Mm    hMm     sMM`   sMM:    :+++++:    -NMd     ...`    `sM");
        System.out.println("MN````hMM+```-NMM.```yMs````-NMMMMMN-````+Md````````..-/smMM");
        System.out.println("MMNNNNMMMMNNNNMMMNNNNMMNNNNNNMMMMMMMNNNNNNMMNNNNNNNNNNNMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        // THis is a Memory From the PAST..................................................
    }

    public static void Activate_Token(AppCompatActivity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SAMANE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("USE_TOKEN" , true);
        editor.apply();
    }

    public static void Deactivate_Token(AppCompatActivity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SAMANE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("USE_TOKEN" , false);
        editor.apply();
    }

    public static boolean IsTokenActive (AppCompatActivity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SAMANE" , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("USE_TOKEN" , false);
    }

    public static void SaveToken(AppCompatActivity activity , String token) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SAMANE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TOKEN" , token);
        editor.apply();
    }

    public static String GetToken(AppCompatActivity activity ) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SAMANE" , Context.MODE_PRIVATE);
        return sharedPreferences.getString("TOKEN" , Init.Empty);
    }

    public  static  String Token = "";
    public static boolean CheckInternet (){
        try {
            try {
                URL url = new URL("http://www.google.com");
                //System.out.println(url.getHost());
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.connect();
                if (con.getResponseCode() == 200){
                    return true;
                    //System.out.println("Connection established!!");
                }
            } catch (Exception exception) {
                return false;
                //System.out.println("No Connection");
            }
        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return false;
    }
    public static void Println (String passin){
        System.out.println(passin);
    }
    public static void Toas (Context context , String Text){
        Toast.makeText(context ,Text , Toast.LENGTH_SHORT).show();
    }

    public static List<Message> get_messages_dao (AppCompatActivity activity){
        List<Message> messages = new ArrayList<>();
        DaoSession session = Repository.GetInstant(activity);
//        messages = session.getMessageDao().loadAll();
        return messages;
    }



    @Nullable
    public static User log_in (String fname , String pass , AppCompatActivity activity){
        DaoSession session = Repository.GetInstant(activity);
//        List<User> users = session.getUserDao().loadAll();

        /*for (User user:users) {
            if (user.getFName().equals(fname) && user.getPass().equals(pass)){
                return user;
            }
        }*/
        return null;
    }

    public static void Terminal (String s){
        System.out.println(s);
    }

    public static void Login_Signup ( final AppCompatActivity activity){
        TextView textView = (TextView) activity.findViewById(R.id.tv_go_signup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity , SignupStudentsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });
    }



    public static void test (){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("role");
        ParseObject Role_obj ;
        String Role_name;
        query.whereMatches("role_id" , "Tvdsj24");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

            }
        });
    }

    public static void test_time(){

        String TAG = "YOUR_APP_TAG";
        String TIME_SERVER = "0.europe.pool.ntp.org";

        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            long setverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();

            // store this somewhere and use to correct system time
            long timeCorrection = System.currentTimeMillis()-setverTime;
        } catch (Exception e) {
            Log.v(TAG,"Time server error - "+e.getLocalizedMessage());
        }
    }

    public static void start_fresh (){
        // TODO: 7/16/2018 Clear all Classes Brfore full Loging in
        role.Clear();
        Broudcast.Clear();
        Thing.Clear();
        Room.Clear();
        Khabgah.Clear();
        Block.Clear();
        Request.Clear();
        Report.Clear();
        Transfer.Clear();
        Feedback.Clear();
        Properties.Clear();





    }

    static int addUser_stop_count = 0;
    public static void start_loading( AppCompatActivity activity){
        if (activity != null){
            if (loading != null){
                stop_loading(activity);
            }

            loading = new ProgressDialog(activity);
            loading.setMessage("در حال پردازش");
            loading.setCancelable(false);
            loading.show();
        }
    }

    public static void stop_loading( AppCompatActivity activity){
        if (activity != null){
            if (activity instanceof TransferActivity){
                if (!((TransferActivity) activity).isIsloaded()){
                    if (loading != null){
                        loading.dismiss();
                    }
                    return;
                }
            }

            /*if (activity instanceof AddUserActivity){
                Log.d("STOP count :" , addUser_stop_count + " tryes!");
                if (addUser_stop_count < 5){
                    addUser_stop_count ++ ;
                    return;
                }
                addUser_stop_count = 100;
                Log.d("STOP count :" , addUser_stop_count + " got out!");
            }*/

            if (activity instanceof SignupStudentsActivity){
                if (((SignupStudentsActivity) activity).isfirst()){
                    // while first time run noormally
                    if (loading != null){
                        loading.dismiss();
                    }
                }
            }

            if (activity instanceof SignupStudentsActivity){
                if (((SignupStudentsActivity) activity).isuserloaded() || ((SignupStudentsActivity) activity).isproploaded()){
                    if (loading != null){
                        loading.dismiss();
                    }
                }
            }


            if (loading != null){
                loading.dismiss();
            }
        }else {
            Log.d("STOP count :" , "NULL ACTIVITY !!");
        }
    }

    public static void user_prop (AppCompatActivity activity , Result result){
        if (result.exception != null){
            Log.e(EXEPTION , result.getCode() + " EX CODE"); // Exception Code
            Log.e(EXEPTION , result.exception.getMessage()); // SOME THING WRONG IN THERE !
            Toast.makeText(activity, "خطا", Toast.LENGTH_SHORT).show();


        }else {

            if (activity == null)
                return;

            switch (result.getCode()){
                case User.CODE_SEND :
                    if (activity.getClass().getName().equals(SignupStudentsActivity.class.getName())){

                    }

                    break;
                case User.CODE_EXIST_DATA :

                    break;
                case Properties.CODE_SEND :

                    break;
                case  Properties.CODE_EXIST_DATA:

                    break;
                default:
                    Log.d("User_prop_Unknown_COD :" ,result.getCode()+" = COD" );
                    break;



            }


        }



    }

    public static void result_of_query (AppCompatActivity activity , Result result){
        if (result.exception != null){
            Log.e(EXEPTION , result.getCode() + " EX CODE"); // Exception Code
            Log.e(EXEPTION , result.exception.getMessage()); // SOME THING WRONG IN THERE !
            Toast.makeText(activity, "خطا", Toast.LENGTH_SHORT).show();

            if (activity != null){
                if (activity instanceof Site_ProfileActivity){
                    ((Site_ProfileActivity) activity).set_properties(null);
                }
            }

        }else {

            if (activity == null){
                return; // hell we dont need doing any thing...
            }


            switch (result.getCode()){

                case Request.CODE_CHAINGED:
                    Log.d("Statistic :" , "REQUESt CODE CHAINGED");
                    if (activity instanceof StatesticActivity)
                        ((StatesticActivity) activity).refresh_view();


                    break;
                case Transfer.CODE_CHAINGED:
                    Log.d("Statistic :" , "TRANSFER CODE CHAINGED");
                    if (activity instanceof StatesticActivity)
                        ((StatesticActivity) activity).refresh_view();

                    break;


                case Report.CODE_CHAINGE:
                    if (activity instanceof  StatesticActivity)
                        ((StatesticActivity) activity).refresh_view();
                    if (activity instanceof  StdReportActivity)
                        ((StdReportActivity) activity).report_chainged();
                    break;

                case User.CODE_STD_ACTIVATED:
                    if (activity instanceof ActiveStudentActivity){
                        ((ActiveStudentActivity) activity).ACTIVATED();
                        ((ActiveStudentActivity) activity).refresh_view();// NOT SHOWING THIS ONE!
                    }

                    break;
                case User.CODE_STD_DELETED:
                    if (activity instanceof ActiveStudentActivity){
                        ((ActiveStudentActivity) activity).DELETED();
                        ((ActiveStudentActivity) activity).refresh_view();// NOT SHOWING THIS ONE!
                    }

                    break;

                case Properties.CODE_OTHER:
                    if (activity instanceof ActiveStudentActivity){
                        ((ActiveStudentActivity) activity).load_other_properties((Properties) result.getMessage());
                    }
                    break;
                case User.CODE_NON_ACTIVE_STD:
                    if (activity instanceof ActiveStudentActivity){
                        Log.d("NON ACTICE USERS :" ,"RECIVE SUCCESS");
                        ((ActiveStudentActivity) activity).put_users((List<User>) result.getMessage());
                    }

                    break;
                case User.CODE_EXIST_DATA :
                    if (activity instanceof SignupStudentsActivity){
                        ((SignupStudentsActivity) activity).say_exsists_user();
                    }

                    if (activity instanceof AddUserActivity){
                        ((AddUserActivity) activity).say_exsists_user();
                    }
                    break;

                    case User.COD_CHECKING:
                        if (activity instanceof SignupStudentsActivity){
                            ((SignupStudentsActivity) activity).user_isok();
                        }

                        if (activity instanceof AddUserActivity){
                            ((AddUserActivity) activity).user_isok();
                        }
                        break;
                case Properties.CODE_EXIST_DATA:
                    if (activity instanceof SignupStudentsActivity){
                        ((SignupStudentsActivity) activity).say_exsists_prop();
                    }

                    if (activity instanceof AddUserActivity){
                        ((AddUserActivity) activity).prp_exists();
                    }

                    break;
                    case Properties.COD_CHECKING:
                        if (activity instanceof SignupStudentsActivity){
                            ((SignupStudentsActivity) activity).prop_isok();
                        }

                        if (activity instanceof AddUserActivity){
                            ((AddUserActivity) activity).prop_isok();
                        }
                        break;
                        case Properties.CODE_SEND_ERROR:
                            if (activity instanceof SignupStudentsActivity){
                                ((SignupStudentsActivity) activity).say_error();
                            }
                            break;
                case Properties.CODE_SEND:
                    if (activity instanceof SignupStudentsActivity){
                        ((SignupStudentsActivity) activity).prop_inserted();
                    }

                    if (activity instanceof AddUserActivity){
                        ((AddUserActivity) activity).prop_inserted();
                    }
                    break;

                case User.CODE_SEND:
                    if (activity instanceof SignupStudentsActivity){
                        ((SignupStudentsActivity) activity).user_inserted();
                    }

                    if (activity instanceof AddUserActivity){
                        ((AddUserActivity) activity).user_inserted();
                    }
                    break;


                case Properties.CODE :
                    if (activity instanceof SamanehaActivity){
                        ((SamanehaActivity) activity).set_property((Properties) result.getMessage());
                    }
                    if (activity instanceof StudentProfileActivity){
                        ((StudentProfileActivity) activity).loadproperties((Properties) result.getMessage());
                    }if (activity instanceof TransferActivity){
                        ((TransferActivity) activity).put_proprtties((Properties) result.getMessage());
                    }
                    if (activity instanceof Site_ProfileActivity){
                        ((Site_ProfileActivity)activity).set_properties((Properties) result.getMessage());
                    }

                    if (activity instanceof ProfileTechActivity){
                        ((ProfileTechActivity)activity).set_properties((Properties) result.getMessage());
                    }

                    break;
                    case Properties.CODE_ALL:

                        break;
                case Khabgah.CODE:
                    if (activity instanceof TransferActivity){
                        ((TransferActivity) activity).put_khabgahs((ArrayList<Khabgah>) result.getMessage());

                        if (!((TransferActivity) activity).isIsloaded()){

//                            ((TransferActivity) activity).load_steppers();

                            // OK DO THE THING manage data in activity !
                        }
                    }

                    if (activity instanceof  SignupStudentsActivity)
                        ((SignupStudentsActivity) activity).put_kh((List<Khabgah>) result.getMessage());

                    if (activity instanceof  AddUserActivity)
                        ((AddUserActivity) activity).set_kh((List<Khabgah>) result.getMessage());

                    break;
                case Room.CODE:
                    if (activity instanceof TransferActivity){
                        ((TransferActivity) activity).put_rooms((ArrayList<Room>) result.getMessage());

                        if (!((TransferActivity) activity).isIsloaded()){


//                            ((TransferActivity) activity).load_steppers();
                            // OK DO THE THING manage data in activity !
                        }

                    }
                    if (activity instanceof SignupStudentsActivity)
                        ((SignupStudentsActivity) activity).put_rooms((List<Room>) result.getMessage());

                    if (activity instanceof AddUserActivity)
                        ((AddUserActivity) activity).set_rooms((List<Room>) result.getMessage());
                    break;
                case Block.CODE:
                    if (activity instanceof TransferActivity){
                        ((TransferActivity) activity).put_blooks((ArrayList<Block>) result.getMessage());

                        if (!((TransferActivity) activity).isIsloaded()){


//                            ((TransferActivity) activity).load_steppers();
                            // OK DO THE THING manage data in activity !
                        }
                    }
                    if (activity instanceof SignupStudentsActivity)
                        ((SignupStudentsActivity) activity).put_blocks((List<Block>) result.getMessage());

                    if (activity instanceof AddUserActivity)
                        ((AddUserActivity) activity).set_bls((List<Block>) result.getMessage());
                    break;

                case Report.CODE_SEND :
                    Log.d("Report : " , "SENDED");
                    if (result.isok())
                        Toast.makeText(activity, "ارسال شد", Toast.LENGTH_SHORT).show();
                    if (activity != null ){
                        if (activity  instanceof ReportActivity){
                            ((ReportActivity) activity).report_sent();
                        }

                    }
                    break;
                case Report_type.CODE:
                    if (result.isok())
                        Log.d("Report_types :" , "Load succsess");
                    if (activity != null){
                        if (activity instanceof ReportActivity) {
                            ((ReportActivity)activity).load_types((ArrayList<Report_type>)result.getMessage());
                        }
                    }
                    break;
                case Report.CODE :
                    if (result.isok())
                        Log.d("Report_self :" , "Load succsess");
                    if (activity != null){
                        if (activity instanceof ReportActivity) {
                            ((ReportActivity)activity).load_reports((ArrayList<Report>)result.getMessage() , Report_type.load_report_types_fog(false) );
                        }
                    }
                    break;
                    case Report.CODE_ALL:
                        if (activity instanceof StdReportActivity){
                            ((StdReportActivity) activity).loadstd_reports((ArrayList<Report>) result.getMessage());
                        }
                        break;
                case Thing.CODE:
                    if (activity.getClass().getName().equals(DarkhastActivity.class.getName())){
                        ((DarkhastActivity) activity).setThings((List<Thing>) result.getMessage());
                    }


                    break;
                case Request.CODE:
                    if (activity.getClass().getName().equals(DarkhastActivity.class.getName())){
                        ((DarkhastActivity) activity).load_requests((List<Request>) result.getMessage());
                    }


                    break;
                case Request.CODE_SEND:
                    if (activity.getClass().getName().equals(DarkhastActivity.class.getName())){
                        ((DarkhastActivity) activity).refresh_view();
                    }


                    break;
                case Transfer.CODE_SEND:
                    if (activity.getClass().getName().equals(TransferActivity.class.getName())){
                        ((TransferActivity)activity).refresh_view();
                    }


                    break;
                case Transfer.CODE:
                    if (activity.getClass().getName().equals(TransferActivity.class.getName())){
                        ((TransferActivity)activity).load_transfers((List<Transfer>) result.getMessage());
                    }


                    break;

                case Feedback.CODE_ALL:
                    if (activity instanceof  StatesticActivity){
                        ((StatesticActivity) activity).load_feeds((List<Feedback>) result.getMessage());
                    }
                    break;
                case Request.CODE_ALL:
                    if (activity instanceof StatesticActivity){
                        ((StatesticActivity) activity).load_reqs((List<Request>) result.getMessage());
                    }
                    break;
                case Transfer.CODE_ALL:
                    if (activity instanceof StatesticActivity){
                        ((StatesticActivity) activity).load_trans((List<Transfer>) result.getMessage());
                    }
                    break;
                case Feedback.CODE_SEND:
                    if (activity.getClass().getName().equals(EnteghadActivity.class.getName())){
                        ((EnteghadActivity)activity).refresh_view();
                    }


                    break;
                case Feedback.CODE:
                    if (activity.getClass().getName().equals(EnteghadActivity.class.getName())){
                        ((EnteghadActivity)activity).load_feedbsacks((List<Feedback>) result.getMessage());
                    }


                    break;
                case Cancellation.CODE_CHAINGED:
                    if (activity instanceof  StatesticActivity)
                        ((StatesticActivity) activity).refresh_view();
                    if (activity.getClass().getName().equals(CancelationQeueActivity.class.getName())){
                        ((CancelationQeueActivity)activity).refresh();
                    }


                    break;

                case Cancellation.CODE_SEND:
                    if (activity.getClass().getName().equals(CancelationActivity.class.getName())){
                        ((CancelationActivity)activity).refresh_view();
                    }


                    break;
                    case  Cancellation.CODE_ALL:
                        if (activity instanceof CancelationQeueActivity){
                            ((CancelationQeueActivity) activity).set_cancelations((List<Cancellation>) result.getMessage());
                        }
                        break;
                case Cancellation.CODE:


                    if (activity.getClass().getName().equals(CancelationActivity.class.getName())){
                        ((CancelationActivity)activity).load_cancelations((List<Cancellation>) result.getMessage());
                    }


                    break;

                case role.CODE:
                    if (activity.getClass().getName().equals(AddUserActivity.class.getName())){
                        ((AddUserActivity)activity).set_roles((List<role>) result.getMessage());
                    }



                    default:
                        Log.i("result error : " , "Unknown CODE sent in ");

            }


        }


    }

}
