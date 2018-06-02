package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.ui.ReciverActivity;

public class DarkhastActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darkhast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("درخواست");
        toolbar.setTitle("درخواست");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void send_darkhast(View view) {
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Dialogue dialog = new Dialogue();
            dialog.Exit_app(this).show();
            //super.onBackPressed(); // YET ON REAL EXITING
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master, menu);
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
        Intent intent;

        switch (id){


            case R.id.nav_fehrest :
                if (this instanceof FehrestActivity){

                }else {
                    intent = new Intent(this , FehrestActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_profile :
                break;
            case R.id.nav_exit :
                break;
            case R.id.nav_enteghad :
                if (!(this instanceof EnteghadActivity)){
                    intent = new Intent(this , EnteghadActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_darkhast :
                if (!(this instanceof DarkhastActivity)){
                    intent = new Intent(this , DarkhastActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_gozaresh :
                if (!(this instanceof GozareshActivity)){
                    intent = new Intent(this , GozareshActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
            case R.id.nav_manage_box :
               // if (!(this instanceof ReciverActivity)){ // HSould Chainge ...todo chainge this activity ASAP!
                    intent = new Intent(this , ReciverActivity.class);
                    startActivity(intent );
                //}

                break;
            case R.id.nav_enseraf :
                if (!(this instanceof EnserafActivity)){
                    intent = new Intent(this , EnserafActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
            case R.id.nav_jabeja :
                if (!(this instanceof JabejaiActivity)){
                    intent = new Intent(this , JabejaiActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
                default:
                    break;



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


