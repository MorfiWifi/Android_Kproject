package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_broudcast;

public class BroadcastActivity extends DarkhastActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("پیام های دریافتی");
        //toolbar.setTitle("فهرست");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Broudcast.getBroudcastList(this , true , false  );

    }

    @Override
    public void refresh_view() {
//        super.refresh_view();
        Broudcast.getBroudcastList(this , true , true  );
    }

    public void loadlist (){
        Broudcast broudcast = new Broudcast();
        RecyclerAdapter_broudcast.Init(broudcast.getBroudcastList(getApplicationContext()) , this);
    }


}
