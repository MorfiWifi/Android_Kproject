package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Stats;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_statestick;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteTicketStatsActivity extends SiteMasterActivity {
    RecyclerAdapter_statestick recycler;
    List<Stats> statsList = new ArrayList<>();
    int chainge = 0;
    public enum query_id {opens , closes , students , internals , outternals , all , erja_to_site}
    final Handler handler = new Handler(); // update View Handler in MAIN TH;
    Runnable runnable_view = new Runnable() {
        @Override
        public void run() {
            load_tickets();
            if (chainge > 0){
                recycler.Set_List(statsList);
                chainge = 0;
            }
            handler.postDelayed(this , 200);
        }
    };

    Runnable runnable_getStats = new Runnable() {
        @Override
        public void run() {

            for (Stats s : statsList) {
                ParseQuery query = new ParseQuery("ticket");
                int m = 0;
                switch (s.queryType){
                    case all:
                        try {
                            m =
                            query.count();
                        } catch (ParseException e) {
                            Log.e("EX IN LOAD : ", s.queryType.toString());
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case opens:
                        query.whereEqualTo("isopen" , true);
                        try {
                            m =
                                    query.count();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case closes:
                        query.whereEqualTo("isopen" , false);
                        try {
                            m =
                                    query.count();
                        } catch (ParseException e) {
                            Log.e("EX IN LOAD : ", s.queryType.toString());
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case students:
                        query.whereEqualTo("creatot_role_name" , "دانشجو");
                        try {
                            m =
                                    query.count();
                        } catch (ParseException e) {
                            Log.e("EX IN LOAD : ", s.queryType.toString());
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case internals:
                        query.whereEqualTo("isinternal" , true);
                        try {
                            m =
                                    query.count();
                        } catch (ParseException e) {
                            Log.e("EX IN LOAD : ", s.queryType.toString());
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case outternals:
                        query.whereEqualTo("isinternal" , false);
                        try {
                            m =
                                    query.count();
                        } catch (ParseException e) {
                            Log.e("EX IN LOAD : ", s.queryType.toString());
                        }
                        if (!s.value.equals(m+"")){
                            chainge++;
                        }
                        s.isLoading = false;
                        s.value = m+"";
                        if (m == 0){
                            s.value = Init.Empty;
                        }
                        break;
                    case erja_to_site:
                        break;
                        default:
                }
            }
        }
    };
    Thread thread = new Thread(runnable_getStats);


    public void load_tickets (){
        if (!thread.isAlive()){
            thread = new Thread(runnable_getStats);
            thread.start();
        }

    }

    public void start_view_thread(){
        handler.removeCallbacks(runnable_view);
        handler.postDelayed(runnable_view , 200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_ticket_stats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("آمار تیکت ها");

        recycler = RecyclerAdapter_statestick.Init(null , this);
        start_view_thread();
        statsList.add(new Stats("تیکت های باز" , "" , true ,  query_id.opens));
        statsList.add(new Stats("تیکت های بسته" , "" , true,  query_id.closes));
        statsList.add(new Stats("تیکت های داخلی" , "" , true ,  query_id.internals));
        statsList.add(new Stats("تیکت های دانشجویان" , "" , true ,  query_id.students));
        statsList.add(new Stats("تیکت های غیر داخلی" , "" , true , query_id.outternals));
        statsList.add(new Stats("کل" , "" , true ,  query_id.all));
        load_tickets();

    }

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
}
