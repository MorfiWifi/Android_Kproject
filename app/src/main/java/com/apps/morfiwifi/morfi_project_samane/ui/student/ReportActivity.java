package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.broudcast_RecyclerAdapter;
import com.apps.morfiwifi.morfi_project_samane.view.gozaresh_RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends DarkhastActivity {
    boolean isok = false;
    List<Report_type> types = new ArrayList<>();
    Report_type selected_type;
    TextInputLayout text;
    boolean isloaded = false;

    public List<Report_type> getTypes (){
        return types;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("گزارش");
        toolbar.setTitle("گزارش");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Report.load_self_reports(this , true , false);

    }


    public void load_types(ArrayList<Report_type> report_types) {
        isloaded = true;
        types = report_types;
    }

    public void new_report(View view) {
        Dialogue.Send_Report(this);
    }

    public void load_reports (List<Report> reports , List<Report_type> types){
        isloaded = true;
        this.types = types;
        Broudcast broudcast = new Broudcast();
        gozaresh_RecyclerAdapter.Init(reports , types,this);
    }

    @Override
    public void refresh_view() {
        report_sent();
//        super.refresh_view();
    }

    public void report_sent (){
        Report.load_self_reports(this , true , true);
    }
}
