package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WifiMorfi on 4/13/2018.
 */

public class DataPref {
    private static SharedPreferences prefs ;
    private static SharedPreferences.Editor editor;
    private static ArrayList<User> users = new ArrayList<>();


    private static void Init (AppCompatActivity activity){
        activity.getSharedPreferences(
                "com.morfi_kProject.app", Context.MODE_PRIVATE);

        prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = prefs.edit();
        first_init();
    }


    public  static Value check_user (String name , String pass , AppCompatActivity activity){
        Init(activity);
        Value res = new Value();
        ger_users();
        for (User user : users) {
            if (user.FName.equals(name)   && user.Pass.equals(pass) ){
                res.user = user;
                res.res = true;
                return res;
            }
        }


        return res;
    }

    public  static boolean insert_users (List<User> users , AppCompatActivity activity){
        boolean res = false;


        return res;
    }

    private static void first_init (){
        String username1 =
        prefs.getString("usr1", "NON");
        if (true){
            username1 =  "student";
            String pass1 = "1234";

            String username2 =  "admin";
            String pass2 = "1234";

            editor.putString("user1.name" , username1);
            editor.putString("user1.pass" , pass1);

            editor.putString("user2.name" , username2);
            editor.putString("user2.pass" , pass2);

            editor.apply();

        }
    }
    private static void ger_users (){
        String N1 = prefs.getString("user1.name" , "NON");
        String N2 = prefs.getString("user2.name" , "NON");
        String P1 = prefs.getString("user1.pass" , "NON");
        String P2 = prefs.getString("user2.pass" , "NON");
        if (!N1.equals("NON")  && !P1.equals("NON") ){
            User user1 = new User();
            user1.Pass = P1;
            user1.FName = N1;
            user1.Type = User.kind.Student;
            users.add(user1);

        }
        if (!N2.equals("NON")  && !P2.equals("NON")){
            User user2 = new User();
            user2.Pass = P2;
            user2.FName = N2;
            user2.Type = User.kind.Admin;
            users.add(user2);
        }
    }
}

