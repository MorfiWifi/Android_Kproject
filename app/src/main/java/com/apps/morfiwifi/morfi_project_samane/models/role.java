package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.ui.site_master.AddUserActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.BroadcastActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.broudcast_RecyclerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class role {

    private static boolean isloaded = false;
    static  String class_name = "role";
    final static  String obj_cod = "cod";
    public static  String HARD_CODED_STUDENT_ROLE = "Gd28OPmRhm";
    static  String obj_name = "name";
    static List<ParseObject> temp;
    static List<role> roles;
    public final static int CODE = 28;

    public String name ;

    @Override
    public String toString() {
        if (name != null){
            return name;
        }else {
            return "یه چیزی !";
        }

    }

    public int cod = 100;
    public String id ;
    public Date craeateAt;

    public static List<role> load_roles (Context context){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                temp =  query.find();
                convert_parse_role();
                isloaded = true;
            }catch (Exception e){
                roles = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(context)){
                return roles;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    temp =  query.find();
                    convert_parse_role();
                }catch (Exception e){
                    roles = new ArrayList<>();
                }
            }
        }
        return roles;
    }
    public static void load_roles  (final AppCompatActivity activity , final boolean draw_loading){
        if ( !isloaded){
            try {
                ParseQuery query = new ParseQuery(class_name);
                if (activity instanceof BroadcastActivity && draw_loading){
                    Init.start_loading(activity);
                }
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            temp = objects;
                            convert_parse_role();
                            isloaded = true;
                            if (activity instanceof BroadcastActivity && draw_loading){
//                                broudcast_RecyclerAdapter.Init(roles , activity);
                                Init.stop_loading(activity);
                            }
                        }else {
                            Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            if (activity instanceof BroadcastActivity && draw_loading ){
//                                broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                Init.stop_loading(activity);
                            }
                        }
                    }
                });
            }catch (Exception e){
                roles = new ArrayList<>();
            }
        }else {
            if (Setting.isPreload(activity)){
                return ;
            }else {
                try {
                    ParseQuery query = new ParseQuery(class_name);
                    if (activity instanceof  BroadcastActivity && draw_loading){
                        Init.start_loading(activity);
                    }
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List objects, ParseException e) {
                            if (e == null){
                                temp = objects;
                                convert_parse_role();
                                isloaded = true;
                                if (activity instanceof BroadcastActivity && draw_loading){
//                                    broudcast_RecyclerAdapter.Init(roles , activity);
                                    Init.stop_loading(activity);
                                }
                            }else {
                                Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            }
                        }
                    });
                }catch (Exception e){
                    roles = new ArrayList<>();
                    if (activity instanceof BroadcastActivity && draw_loading){
//                        broudcast_RecyclerAdapter.Init(roles , activity);
                        Init.stop_loading(activity);
                    }
                }
            }
        }
        return ;

    }
    public static void load_roles_accesasables  (final AppCompatActivity activity , final boolean draw_loading , boolean force){
        if ( !isloaded || force){
            try {
                if (User.current_user == null)
                    return;
                if (User.current_user.cod == 0 )
                    return;
                ParseQuery query = new ParseQuery(class_name);
                query.whereLessThan("cod" , User.current_user.cod+1);// equals are added!


                if ( draw_loading){
                    Init.start_loading(activity);
                }

                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            temp = objects;
                            convert_parse_role();
                            isloaded = false; //  this is not all so no catch
                            Result result = new Result(roles ,CODE , true );
                            Init.result_of_query(activity , result);


                            if ( draw_loading){
//                                broudcast_RecyclerAdapter.Init(roles , activity);
                                Init.stop_loading(activity);
                            }
                        }else {
                            Init.Terminal("Some ERROR IN RETRIVING BROUDCASTS");
                            Result result = new Result(e ,CODE );
                            Init.result_of_query(activity , result);
                            if ( draw_loading ){
//                                broudcast_RecyclerAdapter.Init(broudcastList , activity);
                                Init.stop_loading(activity);
                            }
                        }
                    }
                });
            }catch (Exception e){
                roles = new ArrayList<>();
            }
        }else {

            List<role> roless = new ArrayList<>();
            for (role roli:roles) {
                if (roli.cod <= User.current_user.cod)
                    roless.add(roli);
            }


            Result result = new Result(roless ,CODE , true );
            Init.result_of_query(activity , result);
        }
    }
    private static void convert_parse_role(){
        if (temp != null){
            roles = new ArrayList<>();
            for (ParseObject parseObject : temp) {
                role role = new role();
                role.id = parseObject.getObjectId();
                role.craeateAt = parseObject.getCreatedAt();
                role.name = parseObject.get(obj_name).toString();
                role.cod = parseObject.getInt(obj_cod);
                roles.add(role);
            }
        }else {
            roles = new ArrayList<>();
        }

    }

    public static void Clear(){
        roles = null;
        temp   = null;
        isloaded = false;
    }
}
