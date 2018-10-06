package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends SiteMasterActivity {
    List<role> roles;
    List<Khabgah> khabgahs;
    List<Block> blocks;
    List<Room> rooms;
    boolean dorm_loaded  =false;
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
        Khabgah.load_Khabgahs(this , true , false);
        Block.load_blocks(this  , true , false);
        Room.load_rooms(this , true , false);



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
    public void refresh_view() {
        role.load_roles_accesasables(this , true , true);
    }

    public  int check_propertyes (){

        return 0;
    }

    public void add_user(View view) {
        Toast.makeText(this, "NOT TRYING INSERT USER", Toast.LENGTH_SHORT).show();
        // if std full property is requre !
        // if mot std property is pre requred !
        User user = new User();
//        user.UserName = username;
//
//        User.insert_user();


    }

    private void FIX_ARRAYS (){
        Log.d("ADD USER :" , "FIX ARRAYS CALLED");
        if (khabgahs == null || rooms == null || blocks == null){
            return;
        }

        // EVEY MAN HAS A Weakness find the ....
        Log.d("ADD USER :" , "FIX ARRAYS RUNNING");

        for (int i = 0; i < khabgahs.size(); i++) {
            khabgahs.get(i).blocks = new ArrayList<Block>();
        }
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).rooms = new ArrayList<Room>();
        }

        for (Room r : rooms) {

            for (Block block :blocks ) {
                if (r.blook_id.equals(block.Id)){
                    block.rooms.add(r);
                }
            }

        }

        for (Block block : blocks) {
            for (Khabgah khabgah : khabgahs){
                if (block.khabgah_id.equals(khabgah.Id)){
                    khabgah.blocks.add(block);
                }
            }
        }

        Spinner khs1 =  findViewById(R.id.sp_kh);
        final Spinner bls1 =  findViewById(R.id.sp_block);
        final Spinner rs1 =  findViewById(R.id.sp_room);


        ArrayAdapter<Khabgah> spinnerArrayAdapter = new ArrayAdapter<Khabgah>(this,   android.R.layout.simple_spinner_item, khabgahs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        khs1.setAdapter(spinnerArrayAdapter);

        khs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    // TODO: 6/1/2018 Check some out of bound Things
                    bls1.setEnabled(true);
                    if (khabgahs.get(i).blocks.size() == 0){
                        bls1.setEnabled(false);
                        rs1.setEnabled(false);
                        return;
                    }
                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(getApplicationContext() ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    bls1.setAdapter(spinnerArrayAdapter);

                }else {
                    bls1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bls1.setEnabled(false);
                rs1.setEnabled(false);

            }

        });


        bls1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    rs1.setEnabled(true);

                    bls1.setEnabled(true);
                    if (blocks.get(i).rooms.size() == 0){
//                        bls1.setEnabled(false);
                        rs1.setEnabled(false);
                        return;
                    }

                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(getApplicationContext() ,   android.R.layout.simple_spinner_item, blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    rs1.setAdapter(spinnerArrayAdapter);

                }else {
                    rs1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rs1.setEnabled(false);
            }

        });



    }

    public void set_kh (List<Khabgah> ka    ){
        Log.d("ADD USER :" , "KHABGAH LOADED");
        khabgahs = ka;
        FIX_ARRAYS();
    }

    public void set_bls (List<Block> bls    ){
        Log.d("ADD USER :" , "BLOOCKS LOADED");
        blocks = bls;
        FIX_ARRAYS();
    }

    public void set_rooms (List<Room> roos    ){
        Log.d("ADD USER :" , "ROOMS LOADED");
        rooms = roos;
        FIX_ARRAYS();
    }

}
