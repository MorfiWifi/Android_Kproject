package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
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

public class StudentTicketActivity extends DarkhastActivity {
    public static final  String STUDENT = "student";
    RecyclerAdapter_ticket recycler;
    HashMap< Ticket , Integer> map = new HashMap<>();
    List<Ticket> tickets = new ArrayList<>();
    int chainge = 0;
    String ticket_heade = "";
    ParseUser parseUser;
    boolean isLoadig = true, isSaving = false , isFirstTime = true;
    final Handler handler = new Handler(); // update View Handler in MAIN TH;
    Runnable runnable_view = new Runnable() {
        @Override
        public void run() {
            if (isLoadig){
                star_loading();
            }else {
                stop_loading();
            }
            if (Init.ADVANCE_MOD){
                load_tickets();
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
            ParseObject tic = new ParseObject("ticket");
            ParseQuery query = new ParseQuery("ticket");
            ParseUser temp = ParseUser.getCurrentUser();

            query.whereEqualTo("CreateBy" ,parseUser );
            query.orderByDescending("createdAt");
            try {
                if (tickets.size() <= 0 && isFirstTime){
                    isLoadig = true;
                }
                List<ParseObject> list =
                        query.find();

                if (Init.ADVANCE_MOD && !isFirstTime){
                    if (list.size() == tickets.size())return; // NO refresh
                }
                isFirstTime = false;
                tickets = new ArrayList<>();// first load then send !
                for (ParseObject pa :list) {
                    Ticket t = new Ticket(false , pa);
                    tickets.add(t);
                }
                chainge++;
                isLoadig = false;
                Thread.sleep(1000); // FOR no Ultimate Fast
            } catch (Exception e) {
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
//            tic.put("creatot_role_name" , User.current_user.Role);
            tic.put("creatot_role_cod" , User.current_user.cod);
//                tic_message.put("ROLE_COD" , User.current_user.cod);
            tic.put("header" , ticket_heade);
            tic.put("isinternal" , false);
            tic.put("isopen" , true);
            tic.put("CreateBy_username" , ParseUser.getCurrentUser().getUsername());
            tic.put("BLOCK_ID" , User.current_user.property.blook_id);
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
                    if (tickets.contains(ticket))
                        tickets.get(tickets.indexOf(ticket)).isLoading = false;
//                    tickets.get(p).isLoading = false;
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
    Thread thread_send = new Thread(runnable_sendTickets);

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable_view);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start_view_thread();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable_view);
        super.onDestroy();
    }

    @Override
    public void refresh_view() {
        load_tickets();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srudent_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("تیکت");
        toolbar.setTitle("تیکت");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recycler = RecyclerAdapter_ticket.Init(null , this);
        parseUser = ParseUser.getCurrentUser();
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
            if (thread_send == null){
                thread_send = new Thread(runnable_sendTickets);
                thread_send.start();
                return;
            }

            if (thread_send.isAlive()){
                Toast.makeText(this, "داره میفرسته !", Toast.LENGTH_SHORT).show();
                return;
            }

            thread_send = new Thread(runnable_sendTickets);
            thread_send.start();

        }catch (Exception e){
            Log.e("SEND" , "EXCEPTION >>>");
        }
    }


    public void new_ticket(View view) {
        if (isSaving)
            return;
        Dialogue.new_ticket_student(this);
    }
}
