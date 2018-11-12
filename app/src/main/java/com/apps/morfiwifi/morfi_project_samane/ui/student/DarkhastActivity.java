package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.app.ProgressDialog;
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

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_request;

import java.util.ArrayList;
import java.util.List;

public class DarkhastActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ProgressDialog loading;
    private List<Thing> things = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();

    public List<Thing> getThings() {
        return things;
    }

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

        Request.load_self_requests(this , true , false);


//        ((TextView) navigationView.findViewById(R.id.tv_navbar_name)).setText(User.current_user.getUserName());
//        ((TextView) navigationView.findViewById(R.id.tv_navbar_rolename)).setText(User.current_user.Role);
    }

    public void send_darkhast(View view) {
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (! this.getClass().getName().equals(StudentMainActivity.class.getName())){
                Intent intent = new Intent(this , StudentMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else {
                Dialogue dialog = new Dialogue();
                dialog.Exit_app(this).show();
//                super.onBackPressed();
            }


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
        if (id == R.id.action_refresh){
            if (this instanceof DarkhastActivity){
                this.refresh_view();
            }
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



            case R.id.nav_profile :
                if (!(this instanceof StudentProfileActivity)){
                    intent = new Intent(this , StudentProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_exit :
                Dialogue dialogue = new Dialogue();
                dialogue.Log_out_account(this).show();
                break;
            case R.id.nav_enteghad :
                if (!(this instanceof EnteghadActivity)){
                    intent = new Intent(this , EnteghadActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_about_us :
                Dialogue.about_us(this , "SOME");
                break;
            case R.id.nav_darkhast :
                if (! (this.getClass().getName().equals(DarkhastActivity.class.getName())) ){
                    intent = new Intent(this , DarkhastActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                break;
            case R.id.nav_gozaresh :
                if (!(this instanceof ReportActivity)){
                    intent = new Intent(this , ReportActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
                case R.id.nav_ticket :
                if (!(this instanceof StudentTicketActivity)){ // HSould Chainge ...todo chainge this activity ASAP!
                    intent = new Intent(this , StudentTicketActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }
                    break;
            case R.id.nav_manage_box :
                if (!(this instanceof BroadcastActivity)){ // HSould Chainge ...todo chainge this activity ASAP!
                    intent = new Intent(this , BroadcastActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
            case R.id.nav_enseraf :
                if (!(this instanceof CancelationActivity)){
                    intent = new Intent(this , CancelationActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent );
                }

                break;
            case R.id.nav_jabeja :
                if (!(this instanceof TransferActivity)){
                    intent = new Intent(this , TransferActivity.class);
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

    public void start_loading(){
        loading = new ProgressDialog(this);
        loading.setMessage("در حال پردازش");
        loading.setCancelable(true);
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

    public void new_request(View view) {
        Dialogue.Send_Request(this , getThings());
    }

    public void setThings(List<Thing> things){
        if (things != null)
            this.things = things;
    }

    public void refresh_view() {
        // Some new SENT....
//       load_requests(requests);
        Request.load_self_requests(this , true , true);

    }

    public void load_requests(List<Request> requests) {
        things = Thing.load_things_fog(false); // should already be loaded
        requests = requests;
        RecyclerAdapter_request.Init(requests , things , this);
        // load recycler with data...

    }

}


