package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.User_model;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.ui.ticket.ChooseUserActivity;
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
    private final  int requestCod = 2156;
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
    public static final String NOTINTERNAL = "NOTINTERNAL" ;
    public static final String STUDENT = "STUDENT";
    public static final String ALL = "ALL";
    private boolean isFirstTime = true;
    String type = "";
    static User_model ticketReciver = null;
    Thread thread_new_internal_ticket;

    public static void setTicketReciver(User_model user_model) {
        ticketReciver = user_model;
    }

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
            ParseQuery<ParseObject> mainQuery;
            List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
            ParseQuery query = new ParseQuery("ticket");
            ParseQuery query2 = new ParseQuery("ticket");
            ParseQuery query3 = new ParseQuery("ticket");
            if (Run.equals(runtime.Normal)){
                query.whereEqualTo("CreateBy" ,ParseUser.getCurrentUser() );
                query2.whereEqualTo("LASTERJA" , ParseUser.getCurrentUser());
                query3.whereDoesNotExist("LASTERJA");
                // TODO: 12/9/2018 chek std block with site ...
                query3.whereEqualTo("ROLE_COD" , 0); // Created By std
                query3.whereEqualTo("BLOCK_ID" , User.current_user.property.blook_id); // In block STD

                if (User.current_user.cod == 1){
                    queries.add(query);
                    queries.add(query2);
                    queries.add(query3);
                }else {
                    // IS NOT SITI !!
                    queries.add(query);
                    queries.add(query2);
                }


                mainQuery = ParseQuery.or(queries);
            }else {
                // based on what wanted !!!

                if (type.equals(NOTINTERNAL)){
                    query.whereEqualTo("isinternal" , false);
                    queries.add(query);
                }

                if (type.equals(INTERNAL)){
                    query.whereEqualTo("isinternal" , true);
                    queries.add(query);
                }

                if (type.equals(STUDENT)){
//                    query.whereEqualTo("creatot_role_name" ,"دانشجو");
                    query.whereEqualTo("creatot_role_cod" , User.current_user.cod); // 0
                    queries.add(query);
                }

                if (type.equals(OPEN)){
                    query.whereEqualTo("isopen" ,true);
                    queries.add(query);
                }

                if (type.equals(CLOSED)){
                    query.whereEqualTo("isopen" ,false);
                    queries.add(query);
                }

                if (type.equals(ALL)){
                    queries.add(query);
                }
                mainQuery = ParseQuery.or(queries);
            }



            try {
                if (tickets.size() <= 0 && isFirstTime){
                    isLoadig = true;
                }

                mainQuery.orderByDescending("createdAt");
                List<ParseObject> list =
                        mainQuery.find();

                if (Init.ADVANCE_MOD && !isFirstTime){
                    if (tickets.size() == list.size())return; // no Chainge!
                }
                isFirstTime = false;
                tickets = new ArrayList<>();// first load then send !
                for (ParseObject pa :list) {
                    Ticket t = new Ticket(false , pa);
                    tickets.add(t);
                }
                chainge++;
                isLoadig = false;
                Thread.sleep(1000);
            } catch (Exception e) {
//                e.printStackTrace();
//                Log.
                Log.e(getClass().getName() , e.getMessage());
                chainge++;
                isLoadig = false;
            }
        }
    };

    Runnable runnable_new_internal_ticket = new Runnable() {
        @Override
        public void run() {
            try {
                if (ticketReciver == null) return;
//                HashMap<String , Object> hashMap2 = new HashMap<>();
//                hashMap2.put("userId" , ticketReciver.parseUser.getObjectId());
                ParseQuery role = new ParseQuery("role");

                String role_name = Init.Empty;
                try {
                    String role_id = ticketReciver.parseUser.get("role_id").toString();
                    ParseObject x = (ParseObject) role.get(role_id);
                    role_name = (x ).get("name").toString();
                }catch (Exception e){
                    Log.e("EX IN ROLE GET " , e.getMessage());
                }

                ParseObject tic = new ParseObject("ticket");
                tic.put("CreateBy" , ParseUser.getCurrentUser());
                tic.put("creatot_role_name" , User.current_user.Role);
                tic.put("creatot_role_cod" , User.current_user.cod);
//                tic_message.put("ROLE_COD" , User.current_user.cod);

                tic.put("header" , ticket_heade);
                tic.put("isinternal" , true);
                tic.put("isopen" , true);
                tic.put("CreateBy_username" , ParseUser.getCurrentUser().getUsername());


                ParseObject tic_message = new ParseObject("ticket_message");
//                tic_message.put("mess" , message);
                tic_message.put("CreatedBy" , ParseUser.getCurrentUser());
                tic_message.put("SENDER_USERNAME" , ParseUser.getCurrentUser().getUsername()); // this won't chainge
                tic_message.put("ATTACHED" , "NON"); // file attach (server adress)
                tic_message.put("ERJA" , true);
                tic_message.put("ERJATO" , ticketReciver.parseUser );
                tic_message.put("HAS_ATTACHED" , false);
                tic_message.put("ROLE_NAME" , User.current_user.Role);
                tic_message.put("ROLE_COD" , User.current_user.cod);
                tic_message.put("ERJA_ROLE_NAME" , role_name);
                tic_message.put("ERJATO_USERNAME" , ticketReciver.parseUser.getUsername());


                tic.put("LASTERJA" , ticketReciver.parseUser);
                tic.put("ERJA_ROLE_NAME" , role_name);
                tic.put("LASTERJA_USERNAEM" , ticketReciver.parseUser.getUsername());
                tic_message.put("TICKET" , tic);//add ticket to message
                Ticket ticket = new Ticket(true , tic);
                tickets.add(0 ,ticket);
                map.put(  ticket , tickets.indexOf(ticket));
                isLoadig = true;
                tic_message.save();
                isLoadig = false;
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
                isLoadig = false;
            }
        }
    };

    Thread thread = new Thread(runnable_getTickets);
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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
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
        if (getIntent().getExtras() != null){
            Run = Init.notNull(getIntent().getExtras().getString(USER_MOD , NONADMIN)).equals(ADMINIMISTRATOR)?runtime.Admin:runtime.Normal;
            type =
                    getIntent().getExtras().getString(Query_Part , Init.Empty);
        }

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
        Intent intent = new Intent(this , ChooseUserActivity.class);
        int current_cod =
                User.current_user.cod;

        switch (current_cod){
            case 0:
                intent.putExtra(ChooseUserActivity.min_role_level , 1 ); // NO ERJA TO STD!!
                intent.putExtra(ChooseUserActivity.max_role_level , 0 ); // Base on Current Role COD ..
                break;
            case 1:
                intent.putExtra(ChooseUserActivity.min_role_level , 0 ); // NO ERJA TO STD!!
                intent.putExtra(ChooseUserActivity.max_role_level , 2 ); // Base on Current Role COD ..
                break;
            case 2:
                intent.putExtra(ChooseUserActivity.min_role_level , 1 ); // NO ERJA TO STD!!
                intent.putExtra(ChooseUserActivity.max_role_level , 4 ); // Base on Current Role COD ..
                break;
            case 3:
                intent.putExtra(ChooseUserActivity.min_role_level , 1 ); // NO ERJA TO STD!!
                intent.putExtra(ChooseUserActivity.max_role_level , 4 ); // Base on Current Role COD ..
                break;
            case 4 :
                intent.putExtra(ChooseUserActivity.min_role_level , 0 ); // NO ERJA TO STD!!
                intent.putExtra(ChooseUserActivity.max_role_level , 4 ); // Base on Current Role COD ..
                default:

        }


        startActivityForResult(intent , requestCod);
    }


    public void new_ticket(View view) {
        if (isSaving)
            return;
        Dialogue.new_ticket_student(this);
    }

    private void OPEN_NEW_INTERNAL_TICKET() {
        if (thread_new_internal_ticket == null){
            thread_new_internal_ticket = new Thread(runnable_new_internal_ticket);
            thread_new_internal_ticket.start();
            return;
        }
        if (!thread_new_internal_ticket.isAlive()){
            thread_new_internal_ticket = new Thread(runnable_new_internal_ticket);
            thread_new_internal_ticket.start();
            return;
        }else {
            Toast.makeText(this, "درحال ساختن ...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCod){
            if (resultCode == RESULT_CANCELED ){
                ticketReciver = null;
            }else if (resultCode == RESULT_OK){
                if (data.getExtras() == null)return; // go hell out .....
                boolean has_data =
                        data.getExtras().getBoolean("hasResult" , false);
                if (has_data){
                    OPEN_NEW_INTERNAL_TICKET();
                }
            }
        }
    }

}
