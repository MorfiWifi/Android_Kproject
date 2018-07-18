package com.apps.morfiwifi.morfi_project_samane.ui.technical;

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
import com.apps.morfiwifi.morfi_project_samane.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileTechActivity extends TechnicalActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_tech);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("پروفایل");
        toolbar.setElevation(0);

        load_profile();

    }

    public void load_profile (){
        CircleImageView circleImageView = findViewById(R.id.im_prof);
        TextView username = findViewById(R.id.ti_username);
        TextView rank = findViewById(R.id.tv_ranc);

        username.setText(User.current_user.getUserName());
        rank.setText(User.current_user.Role);
    }

    public void chainge_profile_image(View view) {
        Toast.makeText(this, "در آینده ای نزدیک", Toast.LENGTH_SHORT).show();
    }
}
