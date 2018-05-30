package com.apps.morfiwifi.morfi_project_samane.utility;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ActionMenuView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by WifiMorfi on 12/20/2017.
 */
public class Init {
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

            User u2 = new User();
            u2.setFName("max");
            u2.setPass("1996");
            u2.setLName("ad_block");
            u2.setType(5);



            session.getUserDao().insert(u1);
            session.getUserDao().insert(u2);

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
}
