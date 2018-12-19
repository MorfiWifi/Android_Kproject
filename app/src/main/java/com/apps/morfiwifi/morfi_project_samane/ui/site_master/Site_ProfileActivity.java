package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;

import java.util.Calendar;

public class Site_ProfileActivity extends SiteMasterActivity {

    Properties properties = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//        setTitle("پروفایل");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("پروفایل");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Properties.load_self_properties(this , true , true); // JUST FAST REQUEST ! ASYNC RES
    }

    public  void set_properties (Properties properties){
        if (properties != null ){
            this.properties = properties;
        }else {
            this.properties = null;
        }

        load_profile();
    }

    private void load_profile() {
        if (properties == null){
            findViewById(R.id.lin_colapse1).setVisibility(View.GONE);
            findViewById(R.id.lin_expan_khabgah).setVisibility(View.GONE);
        }else {
            findViewById(R.id.lin_colapse1).setVisibility(View.VISIBLE);
            findViewById(R.id.lin_expan_khabgah).setVisibility(View.VISIBLE);

            TextView tv_prof_name = findViewById(R.id.tv_prof_name);
            TextView tv_real_name = findViewById(R.id.tv_real_name);
            TextView tv_real_lastname = findViewById(R.id.tv_real_lastname);
            TextView tv_natinal_code = findViewById(R.id.tv_natinal_code);
            TextView tv_signup_date = findViewById(R.id.tv_signup_date);

            TextView tv_user_khabgah = findViewById(R.id.tv_user_khabgah);
            TextView tv_khabgah_nmae = findViewById(R.id.tv_khabgah_nmae);
            TextView tv_blook_name = findViewById(R.id.tv_blook_name);
            TextView tv_room_name = findViewById(R.id.tv_room_name);


            TextView tv_username = findViewById(R.id.tv_role_name);
            TextView tv_ranc = findViewById(R.id.tv_ranc);

            tv_username.setText(User.current_user.UserName);
            tv_ranc.setText(User.current_user.Role);

            if (properties.use_khabgah){
                findViewById(R.id.lin_expan_khabgah).setVisibility(View.VISIBLE);
                // finding KHABGAH PROPERTY....// .
                Khabgah khabgah = new Khabgah();
                Room room = new Room();
                Block block = new Block();

                if (Khabgah.khabgahs != null){
                    for (Khabgah ka: Khabgah.khabgahs) {
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
                tv_khabgah_nmae.setText("نام خوابگاه : " + (khabgah.name.equals(Init.Empty)?properties.kh_id:khabgah.name));

                tv_blook_name.setText("نام بلوک : " + (block.name.equals(Init.Empty)?properties.blook_id:block.name));
                tv_room_name.setText("نام اتاق : " + (room.name.equals(Init.Empty)?properties.room_id:room.name));




            }else {
                findViewById(R.id.lin_expan_khabgah).setVisibility(View.GONE);
            }


            tv_prof_name.setText("نام کاربری : " + User.current_user.UserName);
            tv_real_name.setText("نام : " + properties.real_name);
            tv_real_lastname.setText("نام خانوادگی : " + properties.real_lastname);
            tv_natinal_code.setText("کد ملی : " + properties.national_cod);
            shamsiDate shamsiDate = new shamsiDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(properties.createAt);

            String date_STR = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR)  , (calendar.get(Calendar.MONTH)+1) ,  calendar.get(Calendar.DAY_OF_MONTH));

            tv_signup_date.setText("تاریخ ثبت نام : " + date_STR);


        }
    }

    public void chainge_profile_image_std(View view) {
        Toast.makeText(this, "در آینده", Toast.LENGTH_SHORT).show();
    }
}
