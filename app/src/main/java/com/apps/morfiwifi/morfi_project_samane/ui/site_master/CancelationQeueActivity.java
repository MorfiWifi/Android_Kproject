package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;

public class CancelationQeueActivity extends SiteMasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelation_qeue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        setTitle("انصراف ها");

        Cancellation.load_cancelations(this , true);
    }

    public void set_cancelations (List<Cancellation> cancelations){

        general_RecyclerAdapter.Init(cancelations
                , this , Init.Mod.cancelation , false ,true);
    }

    public void refresh (){
        refresh_view();
    }


    @Override
    protected void refresh_view() {
//        super.refresh_view();
        Cancellation.load_cancelations(this , true);
    }
}
