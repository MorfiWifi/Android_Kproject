package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

public class JabejaiActivity extends DarkhastActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,
        View.OnClickListener{
    private JabejaiActivity activity;
    private List<Block> st_blocks;
    private List<Block> end_blocks;
    private List<Room> st_rooms;
    private List<Room> end_rooms;
    private static String[] monthrs = {"فروردین" , "اردیبهشت" , "خرداد" , "تیر" ,"مرداد" ,"شهریور" ,"مهر" ,"آبان" , "آذر" , "دی" , "بهمن" , "اسفند"};
    private boolean is_date_selected = false;
    private TextView tv_hint;

    private ArrayList<Room> rooms;
    private ArrayList<Khabgah> khabgahs;
    private ArrayList<Block> blocks;
    private Properties properties;

    private  boolean isloaded = false;

    public boolean isIsloaded(){
        return isloaded;
    }

    private void force_refresh_view(){
        // TODO: 7/28/2018 clear data - is loaded = NO!
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jabejai);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("جابحایی");
        toolbar.setTitle("جابحایی");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        Properties.load_self_properties(this , true , false);
        Khabgah.load_Khabgahs(this , true , false);
        Block.load_blocks(this , true, false);
        Room.load_rooms(this , true, false);

    }

    public void put_rooms (ArrayList<Room> rooms){
        this.rooms = rooms;
        if (blocks != null && khabgahs != null && properties != null){
            isloaded  =true;
            load_steppers();
        }
        Log.d(this.getClass().getName() , "rooms loaded");
//        Toast.makeText(activity, "rooms loaded", Toast.LENGTH_SHORT).show();

    }
    public void put_blooks (ArrayList<Block> blocks){
        this.blocks = blocks;
        if (rooms != null && khabgahs != null && properties != null){
            isloaded  =true;
            load_steppers();
        }
        Log.d(this.getClass().getName() , "blocks loaded");
//        Toast.makeText(activity, "block loaded", Toast.LENGTH_SHORT).show();
    }
    public void put_khabgahs (ArrayList<Khabgah> khabgahs){
        this.khabgahs = khabgahs;
        if (blocks != null && rooms != null && properties != null){
            isloaded  =true;
            load_steppers();
        }
        Log.d(this.getClass().getName() , "khabgah loaded");
//        Toast.makeText(activity, "kha loaded", Toast.LENGTH_SHORT).show();
    }

    public void put_proprtties (Properties properties){
        this.properties = properties;
        if (blocks != null && rooms != null && khabgahs != null){
            isloaded  =true;
            load_steppers();
        }
        Log.d(this.getClass().getName() , "properties loaded");
//        Toast.makeText(activity, "prop loaded", Toast.LENGTH_SHORT).show();
    }
    private void FIX_ARRAYS (){
        if (rooms != null && blocks != null && khabgahs != null){
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



        }
    }

    public void load_steppers (){
        FIX_ARRAYS();

//        Toast.makeText(activity, "ALL LOADED !", Toast.LENGTH_SHORT).show();


        activity = this;
        tv_hint = findViewById(R.id.tv_choosen_date);
        DaoSession session = Repository.GetInstant(this);

        Spinner sp_st_kh = (Spinner) findViewById(R.id.sp_start_khab);
        Spinner sp_end_kh = (Spinner) findViewById(R.id.sp_end_khab);

        final Spinner sp_st_block = (Spinner) findViewById(R.id.sp_start_block);
        final Spinner sp_end_block = (Spinner) findViewById(R.id.sp_end_block);

        final Spinner sp_st_othagh = (Spinner) findViewById(R.id.sp_start_otagh);
        final Spinner sp_end_othagh = (Spinner) findViewById(R.id.sp_end_otagh);

        ArrayAdapter<Khabgah> spinnerArrayAdapter = new ArrayAdapter<Khabgah>(this,   android.R.layout.simple_spinner_item, khabgahs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_st_kh.setAdapter(spinnerArrayAdapter);
        sp_end_kh.setAdapter(spinnerArrayAdapter);



        sp_st_kh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    sp_st_block.setEnabled(true);
                    //sp_end_block.setEnabled(false);
                    st_blocks = khabgahs.get(i).blocks;
//                            getBlocks();

                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(activity ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    sp_st_block.setAdapter(spinnerArrayAdapter);



                }else {
                    sp_st_block.setEnabled(false);
                    //sp_end_block.setEnabled(false);
                    sp_st_othagh.setEnabled(false);
                    //sp_end_othagh.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO: 6/1/2018 SET ON NOTHING ADAPTER AS AN ITEM!

            }
        });


        sp_end_kh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    // TODO: 6/1/2018 Check some out of bound Things
                    sp_end_block.setEnabled(true);
                    //sp_end_block.setEnabled(false);

                    end_blocks = khabgahs.get(i).blocks;
//                            getBlocks();
                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(activity ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    sp_end_block.setAdapter(spinnerArrayAdapter);

                }else {
                    //sp_st_block.setEnabled(false);
                    sp_end_block.setEnabled(false);
                    //sp_st_othagh.setEnabled(false);
                    sp_end_othagh.setEnabled(false);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sp_st_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    sp_st_othagh.setEnabled(true);
                    //sp_end_block.setEnabled(false);

                    st_rooms = st_blocks.get(i).rooms;
//                            getOthaghs();
                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(activity ,   android.R.layout.simple_spinner_item, st_blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    sp_st_othagh.setAdapter(spinnerArrayAdapter);

                }else {
                    sp_st_othagh.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sp_end_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    sp_end_othagh.setEnabled(true);
                    //sp_end_block.setEnabled(false);

                    end_rooms = st_blocks.get(i).rooms;
//                            getOthaghs();
                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(activity ,   android.R.layout.simple_spinner_item, st_blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    sp_end_othagh.setAdapter(spinnerArrayAdapter);

                }else {
                    sp_end_othagh.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_st_othagh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_end_othagh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    private void Load_View(List<Samane> samanes) {
        // Array of choices
        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.sp_start_block);



        // Application of the Array to the Spinner
        ArrayAdapter<Samane> spinnerArrayAdapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, samanes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public void jabeja_pick_date(View view) {
        PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void taeed_jabeja(View view) {
        if (!is_date_selected){
            Init.Toas(this , "یک روز انتخواب کنید");
        }
        else{
            // TODO: 5/30/2018 Add TO DB & Go BACK
            Init.Toas(this , "تایید شد");
            //onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        Init.Terminal(date);
        String s =  dayOfMonth+ " "+ monthrs[monthOfYear] +" "+ year ;
        tv_hint.setText(s);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/BNAZANIN.TTF");
        tv_hint.setTypeface(type);
        is_date_selected = true;
        //tv_hint.setTypeface();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }
}
