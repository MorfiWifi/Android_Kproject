package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.UserDao;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.signup_stu_RecyclerAdapter;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import static com.apps.morfiwifi.morfi_project_samane.models.UserDao.*;

public class SignupQeueActivity extends SiteMasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_qeue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("صف ثبت نام");

        List<User> sh_users =
        Repository.GetInstant(this).getUserDao().queryBuilder()
                .where(Properties.Should_fill_init_forms.eq(true))
                .where(UserDao.Properties.Active.eq(false)).list();

//        Init.Toas( this, sh_users.size() +"");
        signup_stu_RecyclerAdapter.Init(sh_users , this);


    }


    public void accept_signup_std(View view) {
    }

    public void delet_signup_std(View view) {
    }
}
