package com.apps.morfiwifi.morfi_project_samane.ui.technical;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.view.gozaresh_RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StdReportActivity extends TechnicalActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("گزارشات");

        Report_type.load_report_types(this , false , false);
        Report.load_reports(this , true);


//        List<Report> reportList = Repository.GetInstant(this).getReportDao().loadAll();
//        gozaresh_RecyclerAdapter.Init(reportList, this);


        Log.d("STDREPORT ACCTIVITY : " , "THIS ONE HAS LOADED");
    }

    public void loadstd_reports(ArrayList<Report> reports){
        gozaresh_RecyclerAdapter.Init(reports, this);

    }


    @Override
    public void refresh_view() {
        Report.load_reports(this , true );
    }

    public void report_chainged() {
        refresh_view();

    }
}
