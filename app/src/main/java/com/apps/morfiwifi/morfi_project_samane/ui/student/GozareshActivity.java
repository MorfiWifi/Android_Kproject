package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.graphics.Paint;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh;
import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh_type;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GozareshActivity extends DarkhastActivity {
    boolean isok = false;
    List<Gozaresh_type> types = new ArrayList<>();
    Gozaresh_type selected_type;
    TextInputLayout text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gozaresh);
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
        text = findViewById(R.id.ti_gozaresh_matn);
//        TextInputEditText matn = findViewById(R.id.te_gozaresh_matn);
//        matn.getBackground().clearColorFilter();
//        text.getBackground().clearColorFilter();
//        editText.getBackground().clearColorFilter();


        Spinner gozaresh_header = findViewById(R.id.sp_gozaresh_header);

        types = Repository.GetInstant(this).getGozaresh_typeDao().loadAll();



        ArrayAdapter<Gozaresh_type> spinnerArrayAdapter = new ArrayAdapter<Gozaresh_type>(this,   android.R.layout.simple_spinner_item, types);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        gozaresh_header.setAdapter(spinnerArrayAdapter);




        gozaresh_header.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    selected_type = types.get((i % types.size()));
                    if (selected_type != null){
                        isok = true;
                    }else {
                        isok = false;
                    }


                }else {
                    isok = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO: 6/1/2018 SET ON NOTHING ADAPTER AS AN ITEM!
                isok = false;

            }
        });
    }

    public void send_gozaresh(View view) {
        String mes = "";
        if (text != null){
            mes = text.getEditText().getText().toString().trim();
        }

        if (mes.length() < 5){
            isok = false;
            Init.Toas(this , "چی نوشتی ؟");
        }else {
            if (selected_type != null){
                isok = true;
            }
        }

        if (isok){
            Gozaresh gozaresh = new Gozaresh();
            gozaresh.setType_id(selected_type.id);
            gozaresh.sharh = mes;
            gozaresh.date = Calendar.getInstance().getTime();
            gozaresh.setUser_id(Init.current_login.Id);
            Repository.GetInstant(this).getGozareshDao().insert(gozaresh);
            Init.Toas(this , "گزارش ارسال شد");
        }
    }
}
