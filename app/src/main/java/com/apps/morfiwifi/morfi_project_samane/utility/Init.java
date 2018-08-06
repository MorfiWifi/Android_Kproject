package com.apps.morfiwifi.morfi_project_samane.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.SignupStudentsActivity;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Result;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.JabejaiActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.ReportActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentProfileActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.technical.StdReportActivity;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
///////////////////////////////////////////////////////////////////////////
// BLOOCK ZERO >> !
///////////////////////////////////////////////////////////////////////////
/**
 * Created by WifiMorfi on 12/20/2017.
 */
public class Init {
    public static String Parse_serevr = "pars";
    public static String ASP_server = "ASP";
    public static String Offline = "offline";
    public static String MOD = "pars" ;
    public final static String Empty = "خالی" ;
    public final static String EXEPTION  = "SAMANE EXCEPTION : " ;


    public static User current_login;
    private static ProgressDialog loading;


    public static  void Pront_mark(){
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
        messages = session.getMessageDao().loadAll();
        return messages;
    }



    @Nullable
    public static User log_in (String fname , String pass , AppCompatActivity activity){
        DaoSession session = Repository.GetInstant(activity);
        List<User> users = session.getUserDao().loadAll();

        for (User user:users) {
            if (user.getFName().equals(fname) && user.getPass().equals(pass)){
                return user;
            }
        }
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

        Properties.Clear();





    }

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
            if (activity instanceof JabejaiActivity){
                if (!((JabejaiActivity) activity).isIsloaded()){
                    if (loading != null){
                        loading.dismiss();
                    }
                    return;
                }
            }


            if (loading != null){
                loading.dismiss();
            }
        }
    }

    public static void result_of_query (AppCompatActivity activity , Result result){
        if (result.exception != null){
            Log.e(EXEPTION , result.getCode() + " EX CODE"); // Exception Code
            Log.e(EXEPTION , result.exception.getMessage()); // SOME THING WRONG IN THERE !
            Toast.makeText(activity, "خطا", Toast.LENGTH_SHORT).show();
        }else {
            switch (result.getCode()){
                case Properties.CODE :
                    if (activity instanceof StudentProfileActivity){
                        ((StudentProfileActivity) activity).loadproperties((Properties) result.getMessage());
                    }if (activity instanceof JabejaiActivity){
                        ((JabejaiActivity) activity).put_proprtties((Properties) result.getMessage());
                        // Wee Need 3 THING !
                    if (!((JabejaiActivity) activity).isIsloaded()){

//                        ((JabejaiActivity) activity).load_steppers();


                        // OK DO THE THING manage data in activity !
                    }




                    }


                    break;
                    case Properties.CODE_ALL:

                        break;
                case Khabgah.CODE:
                    if (activity instanceof JabejaiActivity){
                        ((JabejaiActivity) activity).put_khabgahs((ArrayList<Khabgah>) result.getMessage());

                        if (!((JabejaiActivity) activity).isIsloaded()){

//                            ((JabejaiActivity) activity).load_steppers();

                            // OK DO THE THING manage data in activity !
                        }
                    }

                    break;
                case Room.CODE:
                    if (activity instanceof JabejaiActivity){
                        ((JabejaiActivity) activity).put_rooms((ArrayList<Room>) result.getMessage());

                        if (!((JabejaiActivity) activity).isIsloaded()){


//                            ((JabejaiActivity) activity).load_steppers();
                            // OK DO THE THING manage data in activity !
                        }

                    }
                    break;
                case Block.CODE:
                    if (activity instanceof JabejaiActivity){
                        ((JabejaiActivity) activity).put_blooks((ArrayList<Block>) result.getMessage());

                        if (!((JabejaiActivity) activity).isIsloaded()){


//                            ((JabejaiActivity) activity).load_steppers();
                            // OK DO THE THING manage data in activity !
                        }
                    }
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
                    //just things loaded
                    if (activity instanceof DarkhastActivity){
                        ((DarkhastActivity) activity).setThings((List<Thing>) result.getMessage());
                    }


                    break;
                case Request.CODE:
                    //just things loaded
                    if (activity instanceof DarkhastActivity){
                        ((DarkhastActivity) activity).load_requests((List<Request>) result.getMessage());
                    }


                    break;
                case Request.CODE_SEND:
                    //just things loaded
                    if (activity instanceof DarkhastActivity){
                        ((DarkhastActivity) activity).refresh_view();
                    }


                    break;
                case Request.CODE_ALL:
                    //just things loaded
                    if (activity instanceof DarkhastActivity){
                        // this dosent have !
                    }


                    break;
                    default:
                        Log.i("result error : " , "Unknown CODE sent in ");

            }


        }


    }
}
