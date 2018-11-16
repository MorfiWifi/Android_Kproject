package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_ticket;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteTicketActivity extends SiteMasterActivity {
    public final static String ADMINIMISTRATOR = "ADMIN";
    public final static String NONADMIN = "NOADMIN";
    public final static String USER_MOD = "MOD";
    public final static String Query_Part = "QUERYIN";
    public final static String NOERJA = "NOTASSIGNED";
    public final static String FORADMIN = "FORADMIN";
    public final static String OPEN = "OPEN";
    public final static String CLOSED = "CLOSED";
    public final static String INTERNAL = "INTERNAL";
    public final static String INTERNAL_OPEN = "INTERNAL_OPEN";
    public final static String INTERNAL_CLOSE = "INTERNAL_CLOSE";
    public final static String ERJA_BASE = "ERJA_BASE";
    public final static String USER_BASE = "USER_BASE";
    public final static String TARGET_USER = "TARGER";



   /* var json = {
            "open": open,
            "close": close,
            "internal" : internal,
            "outernal" : out,
            "notassigned" : notassigne
}*/


    enum runtime { Admin  , Normal}
    runtime Run = runtime.Normal;

    RecyclerAdapter_ticket recycler;
    HashMap< Ticket , Integer> map = new HashMap<>();
    List<Ticket> tickets = new ArrayList<>();
    int chainge = 0;
    String ticket_heade = "";
//    ParseUser parseUser;
    boolean isLoadig = true, isSaving = false;
    final Handler handler = new Handler(); // update View Handler in MAIN TH;
    Runnable runnable_view = new Runnable() {
        @Override
        public void run() {
            if (isLoadig){
                star_loading();
            }else {
                stop_loading();
            }
            if (isSaving){
                findViewById(R.id.fab_new_ticket).setVisibility(View.GONE);
            }else {
                findViewById(R.id.fab_new_ticket).setVisibility(View.VISIBLE);
            }
            if (chainge > 0){
                recycler.Set_List(tickets);
                chainge = 0;
            }
            handler.postDelayed(this , 200);
        }
    };

    Runnable runnable_getTickets = new Runnable() {
        @Override
        public void run() {
//            boolean isloaded = false;

            ParseQuery<ParseObject> mainQuery;
            List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
            ParseQuery query = new ParseQuery("ticket");
            ParseQuery query2 = new ParseQuery("ticket");
            ParseQuery query3 = new ParseQuery("ticket");
            if (Run.equals(runtime.Normal)){
                query.whereEqualTo("CreateBy" ,ParseUser.getCurrentUser() );
                query2.whereEqualTo("LASTERJA" , ParseUser.getCurrentUser());
                query3.whereDoesNotExist("LASTERJA");

                queries.add(query);
                queries.add(query2);
                queries.add(query3);
                mainQuery = ParseQuery.or(queries);
            }else {
                // based on what wanted !!!
                mainQuery = new ParseQuery<ParseObject>("asda");// ??



            }



            try {
                isLoadig = true;
                mainQuery.orderByDescending("createdAt");
                List<ParseObject> list =
                        mainQuery.find();
                tickets = new ArrayList<>();// first load then send !
                for (ParseObject pa :list) {
                    Ticket t = new Ticket(false , pa);
                    tickets.add(t);
                }
                chainge++;
                isLoadig = false;
            } catch (ParseException e) {
//                e.printStackTrace();
//                Log.
                Log.e(getClass().getName() , e.getMessage());
                chainge++;
                isLoadig = false;
            }
        }
    };
    Runnable runnable_sendTickets = new Runnable() {
        @Override
        public void run() {
            ParseObject tic = new ParseObject("ticket");
            tic.put("CreateBy" , ParseUser.getCurrentUser());
            tic.put("creatot_role_name" , User.current_user.Role);
            tic.put("header" , ticket_heade);
            tic.put("isinternal" , false);
            tic.put("isopen" , true);
            tic.put("CreateBy_username" , ParseUser.getCurrentUser().getUsername());
            try {
                Ticket ticket = new Ticket(true , tic);
                tickets.add(0 ,ticket);
                map.put(  ticket , tickets.indexOf(ticket));
                chainge++;
                isSaving = true;
                tic.save();
                isSaving = false;
                chainge++;
                ticket_heade = "";
                if (map.containsKey(ticket)){
                    int p = map.get(ticket);
                    tickets.get(p).isLoading = false;
                }
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                isSaving = false;
                ticket_heade = "";
                chainge++;
            }
        }
    };
    Thread thread = new Thread(runnable_getTickets);
    final Thread thread_send = new Thread(runnable_sendTickets);

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable_view);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start_view_thread();
        load_tickets();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable_view);
        super.onDestroy();
    }

    public static void StartActivity_UserBase(AppCompatActivity activity) {
        // based on user .....

        if (User.current_user.cod > 3){
            Intent intent= new Intent(activity, SiteTicketStatsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(USER_MOD , ADMINIMISTRATOR);
            activity.startActivity(intent);
        }else {
            Intent intent= new Intent(activity, SiteTicketActivity.class);
            intent.putExtra(USER_MOD , NONADMIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("تیکت ها");

        Run = Init.notNull(getIntent().getExtras().getString(USER_MOD , NONADMIN)).equals(ADMINIMISTRATOR)?runtime.Admin:runtime.Normal;
        recycler = RecyclerAdapter_ticket.Init(null , this);
        load_tickets();
        start_view_thread();

    }

    public void start_view_thread(){
        handler.removeCallbacks(runnable_view);
        handler.postDelayed(runnable_view , 200);
    }
    public void load_tickets (){
        if (!thread.isAlive()){
            thread = new Thread(runnable_getTickets);
            thread.start();
        }

    }

    public void star_loading (){
        findViewById(R.id.tv_please_bepashent).setVisibility(View.VISIBLE);
        findViewById(R.id.rec_ticket).setVisibility(View.GONE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_loading);
        avi.setVisibility(View.VISIBLE);
        if (!avi.isActivated()){
            avi.show();
        }
    }

    @Override
    public void stop_loading (){
        findViewById(R.id.tv_please_bepashent).setVisibility(View.GONE);
        findViewById(R.id.rec_ticket).setVisibility(View.VISIBLE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_loading);
        avi.setVisibility(View.GONE);
        avi.hide();
    }

    public void insert_ticket_header ( String s){
        ticket_heade = s;
        try {
            thread_send.start();
        }catch (Exception e){
            Log.e(getClass().getName() , e.getMessage());
        }
    }


    public void new_ticket(View view) {
        if (isSaving)
            return;
        Dialogue.new_ticket_student(this);
    }

}
