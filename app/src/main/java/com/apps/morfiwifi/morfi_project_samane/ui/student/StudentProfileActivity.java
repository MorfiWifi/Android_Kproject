package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.util.Calendar;

public class StudentProfileActivity extends DarkhastActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

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


        ExpansionLayout expansionLayout = findViewById(R.id.expansionLayout);
        expansionLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {

            }
        });

        ExpansionLayout expansionLayout2 = findViewById(R.id.expansionLayout2);
        expansionLayout2.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {

            }
        });

        ExpansionLayout expansionLayout3 = findViewById(R.id.expansionLayout3);
        expansionLayout2.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {

            }
        });


        Properties.load_self_properties(this , true , false); // JUST FAST REQUEST ! ASYNC RES

    }

    public void loadproperties (Properties properties){ // RUNS FROM ASYNC METHOD
        // THE NULL PROBLEM HAS ALREDY FIXED - NO NULL INCLUDED

        TextView tv_isstudying = findViewById(R.id.tv_isstudying);

        TextView tv_prof_name = findViewById(R.id.tv_prof_name);
        TextView tv_real_name = findViewById(R.id.tv_real_name);
        TextView tv_real_lastname = findViewById(R.id.tv_real_lastname);
        TextView tv_natinal_code = findViewById(R.id.tv_natinal_code);
        TextView tv_signup_date = findViewById(R.id.tv_signup_date);

        TextView tv_user_khabgah = findViewById(R.id.tv_user_khabgah);
        TextView tv_khabgah_nmae = findViewById(R.id.tv_khabgah_nmae);
        TextView tv_blook_name = findViewById(R.id.tv_blook_name);
        TextView tv_room_name = findViewById(R.id.tv_room_name);


        TextView tv_username = findViewById(R.id.tv_username);
        TextView tv_ranc = findViewById(R.id.tv_ranc);

        tv_username.setText(User.current_user.getUserName());
        tv_ranc.setText(User.current_user.Role);

        if (properties.use_khabgah){
            findViewById(R.id.lin_expan_khabgah).setVisibility(View.VISIBLE);
            // finding KHABGAH PROPERTY....// .
                Khabgah khabgah = new Khabgah();
                Room room = new Room();
                Block block = new Block();

                if (Khabgah.things != null){
                    for (Khabgah ka: Khabgah.things) {
                        if (ka.Id.equals(properties.kh_id)){
                            khabgah = ka;
                            break;
                        }
                    }
                }
                if (Block.blocks != null){
                    for (Block ka: Block.blocks) {
                        if (ka.Id.equals(properties.blook_id)){
                            block = ka;
                            break;
                        }
                    }
                }

                if (Room.rooms != null){
                    for (Room ka: Room.rooms) {
                        if (ka.Id.equals(properties.room_id)){
                            room = ka;
                            break;
                        }
                    }
                }



            tv_user_khabgah.setText("استفاده از خوابگاه  : بله");
            tv_khabgah_nmae.setText("نام خوابگاه : " + khabgah.name);

            tv_blook_name.setText("نام بلوک : " + block.name);
            tv_room_name.setText("نام اتاق : " + room.name);


            shamsiDate shamsiDate = new shamsiDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(properties.createAt);

            String date_STR = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR)  , (calendar.get(Calendar.MONTH)+1) ,  calendar.get(Calendar.DAY_OF_MONTH));

            tv_signup_date.setText("تاریخ ثبت نام : " + date_STR);

        }else {
            findViewById(R.id.lin_expan_khabgah).setVisibility(View.GONE);
        }

        if (properties.is_studying){
            tv_isstudying.setText("درحال نحصیل : بله");
        }else {
            tv_isstudying.setText("درحال نحصیل : خبر");
        }

        tv_prof_name.setText("نام کاربری : " + User.current_user.getUserName());
        tv_real_name.setText("نام : " + properties.real_name);
        tv_real_lastname.setText("نام خانوادگی : " + properties.real_lastname);
        tv_natinal_code.setText("کد ملی : " + properties.national_cod);


    }

    public void chainge_profile_image_std(View view) {
        Toast.makeText(this, "در آینده", Toast.LENGTH_SHORT).show();
    }
}
