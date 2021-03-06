package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentMainActivity;
import com.apps.morfiwifi.morfi_project_samane.util.MYService;

public class SiteMasterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog loading;



    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(this.getClass().getName() , "Broaud cast RECIVED HANDELING >>>0");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String string = bundle.getString(MYService.FILEPATH);
                int resultCode = bundle.getInt(MYService.RESULT);
                if (resultCode == RESULT_OK) {
                    Toast.makeText(context,
                            "Download complete. Download URI: " + string,
                            Toast.LENGTH_LONG).show();
//                    textView.setText("Download done");
                } else {
                    Toast.makeText(context, "Download failed",
                            Toast.LENGTH_LONG).show();
//                    textView.setText("Download failed");
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        Log.d("SERVICE :" , "SERVICE unREGISTERED !") ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                MYService.NOTIFICATION));
        Log.d("SERVICE :" , "SERVICE REGISTERED !") ;

        Intent intent = new Intent(this, MYService.class);
        intent.putExtra(MYService.FILENAME, "index.html");
        intent.putExtra(MYService.URL,
                "http://www.vogella.com/index.html");
        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_master);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        setTitle("پنل مسئول سایت");
        setTitle(User.current_user.Role);

        int m = User.current_user.cod;
        if (m > 3){
            findViewById(R.id.lin_6th_icon).setVisibility(View.VISIBLE);
            findViewById(R.id.lin_5th_icon).setVisibility(View.VISIBLE);
            findViewById(R.id.lin_4th_icon).setVisibility(View.VISIBLE);
            findViewById(R.id.lin_3rd_icon).setVisibility(View.VISIBLE);
            findViewById(R.id.lin_2nd_icon).setVisibility(View.VISIBLE);
        }

//        android:id="@+id/"


        final AppCompatActivity activity = this;
        findViewById(R.id.rel_prof).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteTicketActivity.StartActivity_UserBase(activity);
                /*Intent intent = new Intent(getApplicationContext() , Site_ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        });

        findViewById(R.id.rel_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 8/26/2018 add log out function in site master
                Dialogue dialogue = new Dialogue();
                dialogue.Log_out_account(activity).show();

            }
        });

        findViewById(R.id.rel_signup_queue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , SignupQeueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        findViewById(R.id.rel_activate_std).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ActiveStudentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.rel_go_out_queue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , CancelationQeueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.rel_add_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , AddUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.rel_statistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , StatesticActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        findViewById(R.id.rel_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


//        --------------------- LOADUD KHABGAH SETTING FOR PROFILE -----------------------------

        Khabgah.load_Khabgahs(null , false , true);
        Room.load_rooms(null , false ,true);
        Block.load_blocks(null , false , true);

//        --------------------- LOADUD KHABGAH SETTING FOR PROFILE -----------------------------

    }

    @Override
    public void onBackPressed() {
        // you have destroyed this !
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (! this.getClass().getName().equals(SiteMasterActivity.class.getName())){
                Intent intent = new Intent(this , SiteMasterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else {
                    Dialogue dialog = new Dialogue();
                    dialog.Exit_app(this).show();
//                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.site_master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh){
            refresh_view();
        }

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
        if (id == R.id.nav_profile) {
            if (!(this.getClass().getName().equals(Site_ProfileActivity.class.getName()))){
                intent = new Intent(this , Site_ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_sighup) {
            if (!(this.getClass().getName().equals(SignupQeueActivity.class.getName()))){
                intent = new Intent(this , SignupQeueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        } else if (id == R.id.nav_enseraf) {
            if (!(this.getClass().getName().equals(CancelationQeueActivity.class.getName()))){
                intent = new Intent(this , CancelationQeueActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        } else if (id == R.id.nav_adduser) {
            if (!(this.getClass().getName().equals(AddUserActivity.class.getName()))){
                intent = new Intent(this , AddUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        } else if (id == R.id.nav_statesic) {
            if (!(this.getClass().getName().equals(StatesticActivity.class.getName()))){
                intent = new Intent(this , StatesticActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        } else if (id == R.id.nav_ticket) {
            if (!(this.getClass().getName().equals(SiteTicketActivity.class.getName()))){
                SiteTicketActivity.StartActivity_UserBase (this);

            }

        } else if (id == R.id.nav_search) {
            if (!(this.getClass().getName().equals(SearchActivity.class.getName()))){
                intent = new Intent(this , SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        }else if (id == R.id.nav_about_us) {
            Dialogue.about_us(this , "SOME !");
        } else if (id == R.id.nav_active_user) {
            if (!(this.getClass().getName().equals(ActiveStudentActivity.class.getName()))){
                intent = new Intent(this , ActiveStudentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } else if (id == R.id.nav_exit) {
            // Wait YET ....
            Dialogue dialogue = new Dialogue();
            dialogue.Log_out_account(this).show();

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

    public void refresh_view(){

    }
}
