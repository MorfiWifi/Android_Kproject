package com.apps.morfiwifi.morfi_project_samane.ui.technical;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.EnserafQeueActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SiteMasterActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

public class TechnicalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("مسئول فنی");

        // REDUNDENT PART __ FUTURE MAY REMOVE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // REDUNDENT PART __ FUTURE MAY REMOVE
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (! this.getClass().getName().equals(TechnicalActivity.class.getName())){
                Intent intent = new Intent(this , TechnicalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else {
                super.onBackPressed();
            }



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.technical, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent ;
        if (id == R.id.nav_std_report) {
            if (!(this.getClass().getName().equals(StdReportActivity.class.getName()))){
                intent = new Intent(this , StdReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_post_archive) {
            if (!(this.getClass().getName().equals(PostArchiveActivity.class.getName()))){
                intent = new Intent(this , PostArchiveActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_profile) {
            if (!(this.getClass().getName().equals(ProfileTechActivity.class.getName()))){
                intent = new Intent(this , ProfileTechActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_manage_items) {
            if (!(this.getClass().getName().equals(ManageItemsActivity.class.getName()))){
                intent = new Intent(this , ManageItemsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_exit) {
            // TODO: 6/8/2018 EXIT CODE FOR TECHNIKAL MAN !

        } else  {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void start_loading(){
        loading = new ProgressDialog(this);
        loading.setMessage("در حال پردازش");
        loading.setCancelable(false);
        loading.show();
    }

    public void stop_loading(){
        if (loading != null){
            loading.dismiss();
        }
    }

    @Override
    protected void onStop() {
        stop_loading();
        super.onStop();
    }

    public void refresh(MenuItem item) {
        //        // TODO: 7/17/2018  refresh This activity posts complet Other ACS
        Init.Terminal("Refresh Clicked .....");
        String class_name =  this.getClass().getName();


        if (class_name.equals(TechnicalActivity.class.getName())){


        }else if (class_name.equals(ProfileTechActivity.class.getName())){


        }else if (class_name.equals(StdReportActivity.class.getName())){


        }else if (class_name.equals(ManageItemsActivity.class.getName())){


        }else if (class_name.equals(PostArchiveActivity.class.getName())){
            Broudcast.getBroudcastList(this , true , true);

        }else {

            // NO MAIN ACTIVITY !

        }



    }
}
