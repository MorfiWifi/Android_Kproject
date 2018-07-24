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
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
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
    public static void Insert_init_Users(AppCompatActivity activity){
        DaoSession session = Repository.GetInstant(activity);
        List<User> users = session.getUserDao().loadAll();
        if (users.size() < 2){
            User u1 = new User();
            u1.setType(0);
            u1.setFName("morteza");
            u1.setPass("1990");
            u1.setLName("ad");
            u1.setActive(true);
            u1.setShould_fill_init_forms(false);

            User u2 = new User();
            u2.setFName("max");
            u2.setPass("1996");
            u2.setLName("ad_block");
            u2.setType(5);
            u2.setActive(true);
            u2.setShould_fill_init_forms(false);

            User u3 = new User();
            u3.setFName("site");
            u3.setPass("1234");
            u3.setLName("ad_block");
            u3.set_Type(User.Kind.Site_Master);
            u3.setActive(true);
            u3.setShould_fill_init_forms(false);

            User u4 = new User();
            u4.setFName("masool");
            u4.setPass("1234");
            u4.setLName("ad_block");
            u4.set_Type(User.Kind.Technical);
            u4.setActive(true);
            u4.setShould_fill_init_forms(false);


            session.getUserDao().insert(u1);
            session.getUserDao().insert(u2);
            session.getUserDao().insert(u3);
            session.getUserDao().insert(u4);

            List<Message> messages = session.getMessageDao().loadAll();
            users = session.getUserDao().loadAll();


            if (messages.size() < 2 ){
                Message m1 = new Message();
                m1.Matn = "بازگشت اضطراری" ;
                m1.Readed = "YES"; //=> Make it boolean oR TIME !
                m1.setSender(users.get(users.size()-1)); // admin
                m1.Tags = "NON"; // yet
                m1.Reciver_Type = 0;
                m1.Recive_Date = Calendar.getInstance().getTime().toString();


                Message m2 = new Message();
                m2.Matn = "پام اضطراری" ;
                m2.Readed = "NO"; //=> Make it boolean oR TIME !
                m2.setSender(users.get(users.size()-1)); // admin
                m2.Tags = "NON"; // yet
                m2.Reciver_Type = 0;
                m2.Recive_Date = Calendar.getInstance().getTime().toString();

                session.getMessageDao().insert(m1);

               // session.getMessageDao().insert(m2);// todo Unknown Exception HERE!!
                List<Samane> samanes = session.getSamaneDao().loadAll();
                //users = session.getUserDao().loadAll();

                if (samanes.size() < 2){
                    Samane s1 = new Samane();
                    s1.Name = "خوابگاه ها";
                    s1.Code = "06";
                    s1.prop = "NON_YET_INSERTED";


                    Samane s2 = new Samane();
                    s2.Name = "تغذیه";
                    s2.Code = "07";
                    s2.prop = "NON_YET_INSERTED";

                    session.getSamaneDao().insert(s1);
                    session.getSamaneDao().insert(s2);


                    users = session.getUserDao().loadAll();
                    samanes = session.getSamaneDao().loadAll();

                    User un =  users.get(0);
                    un.getSamanes().add(samanes.get(0));
                    session.getUserDao().save(un);

                    un =  users.get(0);
                    un.getSamanes().add(samanes.get(1));
                    session.getUserDao().save(un);

                }


                Khabgah kh1 = new Khabgah();
                Khabgah kh2 = new Khabgah();

                Block bl1 = new Block();
                Block bl2 = new Block();

                Room ot1 = new Room();
                Room ot2 = new Room();

                /*ot1.setNaem("شماره 5");
                ot1.setCode("007");

                ot2.setNaem("شماره 6");
                ot2.setCode("008");*/

                bl1.name = "بلوک 1";
                bl2.name = "بلوک 2";
                bl2.code = "2";
                bl1.code = "3";

                kh1.name = "شهید مطهری";
                kh2.name = "مرکزی";
                kh2.code = "0";
                kh1.code = "13";

                /*kh1.id = session.getKhabgahDao().insert(kh1); // retriving ID
                kh2.id = session.getKhabgahDao().insert(kh2); // retriving ID

                bl1.id = session.getBlockDao().insert(bl1);
                bl2.id = session.getBlockDao().insert(bl2);

                ot1.id = session.getOthaghDao().insert(ot1);
                ot2.id = session.getOthaghDao().insert(ot2);*/


                List<Room> othaghs_1 = new ArrayList<>();
                List<Room> othaghs_2 = new ArrayList<>();
                othaghs_1.add(ot1);
                othaghs_1.add(ot2);
                othaghs_2.add(ot1);
                othaghs_2.add(ot2);

                bl1.rooms = othaghs_1;
                bl2.rooms = othaghs_2;

//                session.getBlockDao().update(bl1);
//                session.getBlockDao().update(bl2);

                List<Block> blocks_1 = new ArrayList<>();
                blocks_1.add(bl1);
                blocks_1.add(bl2);

                List<Block> blocks_2 = new ArrayList<>();
                blocks_2.add(bl2);

                kh1.blocks = blocks_1;
                kh2.blocks = blocks_2;

//                session.getKhabgahDao().update(kh1);
//                session.getKhabgahDao().update(kh2);


                Report_type type1 = new Report_type();
                type1.Name = "bilding";
                type1.pr_name = "خرابی ساختمان";

                Report_type type2 = new Report_type();
                type2.Name = "robery";
                type2.pr_name = "سرقت";

                Report_type type3 = new Report_type();
                type3.Name = "network";
                type3.pr_name = "شبکه";

                Report_type type4 = new Report_type();
                type4.Name = "usual";
                type4.pr_name = "عمومی";

                Report_type type5 = new Report_type();
                type5.Name = "food";
                type5.pr_name = "آشپزخانه";

                session.getReport_typeDao().insert(type1);
                session.getReport_typeDao().insert(type2);
                session.getReport_typeDao().insert(type3);
                session.getReport_typeDao().insert(type4);
                session.getReport_typeDao().insert(type5);
            }
        }




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

    public static void start_loading( AppCompatActivity activity){
        if (loading != null){
            stop_loading(activity);
        }

        loading = new ProgressDialog(activity);
        loading.setMessage("در حال پردازش");
        loading.setCancelable(false);
        loading.show();
    }

    public static void stop_loading( AppCompatActivity activity){
        if (loading != null){
            loading.dismiss();
        }
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




    }
}
