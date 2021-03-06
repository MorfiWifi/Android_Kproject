package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.User;
//import com.apps.morfiwifi.morfi_project_samane.models.UserDao;
import com.apps.morfiwifi.morfi_project_samane.utility.WriteExcel;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_active_stu;

import java.util.List;

public class ActiveStudentActivity extends SiteMasterActivity {
    private List<User> sh_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("فعال سازی حساب");

       /* sh_users =
                Repository.GetInstant(this).getUserDao().queryBuilder()
                        .where(UserDao.Properties.Should_fill_init_forms.eq(true))
                        .where(UserDao.Properties.Active.eq(false))
                        .where(UserDao.Properties.PreActive.eq(true))
                        .list();*/

       // GET NEEED ACTIVE USERS >>>>!
//        User.getall_active_users();
        User.getall_need_active_students(this , true , false);


        RecyclerAdapter_active_stu.Init(sh_users , this); // TODO: 6/26/2018 Active Thign !
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.site_master, menu);
        MenuItem item = menu.findItem(R.id.action_export_list);
        if (item != null){
            item.setVisible(true);// visiling the Second One
        }

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
        if (id ==R.id.action_export_list ){
            //item.setVisible(true);
            WriteExcel.Export_Active_Queue(sh_users , this);
            // TODO: 6/25/2018 Export THE EXCEL THING
            //bug fix THIS EXCELL USER CHAINGED SINCE THEN!
        }

        return super.onOptionsItemSelected(item);
    }

    public void put_users (List<User> users){
        this.sh_users = users;
        RecyclerAdapter_active_stu.Init(sh_users , this); // TODO: 6/26/2018 Active Thign !
    }

    @Override
    public void refresh_view(){
        Properties.clear_other();
        User.getall_need_active_students(this , true , true);
    }

    public void load_other_properties (Properties properties){
        RecyclerAdapter_active_stu.expand_buttom_sheet(properties);
    }

    public void DELETED() {
        Toast.makeText(this, "کاربر حذف شد", Toast.LENGTH_SHORT).show();
    }

    public void ACTIVATED() {
        Toast.makeText(this, "کار بر فعال شد", Toast.LENGTH_SHORT).show();
    }
}
