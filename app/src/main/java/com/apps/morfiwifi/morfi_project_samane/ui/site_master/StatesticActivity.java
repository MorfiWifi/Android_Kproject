package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.app.TabActivity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class StatesticActivity extends SiteMasterActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statestic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        getApplicationContext()
//
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.addTab(getTabSpec1(fragmentTabHost), FirstActivity.class, null);
        fragmentTabHost.addTab(getTabSpec2(fragmentTabHost), SecondActivity.class, null);



        setTitle("آمار");
    }

    private TabHost.TabSpec getTabSpec1(FragmentTabHost tabHost) {
        return tabHost.newTabSpec("First Tab")
                .setIndicator("Tab1");
    }

    private TabHost.TabSpec getTabSpec2(FragmentTabHost tabHost) {
        return tabHost.newTabSpec("Second Tab")
                .setIndicator("Tab 2");
    }


}
