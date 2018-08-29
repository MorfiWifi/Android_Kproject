package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.role;

import java.util.List;

public class AddUserActivity extends SiteMasterActivity {
    List<role> roles;
    boolean isroleloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        role.load_roles_accesasables(this , true , false);
        final Switch sw1 = findViewById(R.id.sw_first);
        final Switch sw2 = findViewById(R.id.sw_second);


        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    findViewById(R.id.lin_expandable_info1).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.lin_expandable_info1).setVisibility(View.GONE);

            }
        });


        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    findViewById(R.id.lin_expandable_info2).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.lin_expandable_info2).setVisibility(View.GONE);
            }
        });

         final Spinner spinner = findViewById(R.id.sp_user_types);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (spinner.getSelectedItem().toString().equals("دانشجو")){
                     sw1.setChecked(true);
                     sw2.setChecked(true);
                     sw1.setEnabled(false);
                     sw2.setEnabled(false);

                 }else {
                     sw1.setEnabled(true);
                     sw2.setEnabled(true);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });



        setTitle("اضافه کردن کاربر");
    }


    public void set_roles (List<role> roles){
        if (roles != null){
            this.roles = roles;
            isroleloaded = true;
            update_view();
        }

    }

    private void update_view() {
        if (roles== null)
            return;
        ArrayAdapter<role> spinnerArrayAdapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, roles);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        Spinner spinner = findViewById(R.id.sp_user_types);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    private void clean_up(){
        // TODO: 8/29/2018 cleaning things up


    }


    @Override
    protected void refresh_view() {
        role.load_roles_accesasables(this , true , true);
    }
}
