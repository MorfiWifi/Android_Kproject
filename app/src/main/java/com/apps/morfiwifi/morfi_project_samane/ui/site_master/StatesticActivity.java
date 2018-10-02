package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.fragments.Extra_frag;
import com.apps.morfiwifi.morfi_project_samane.fragments.Site_feeds;
import com.apps.morfiwifi.morfi_project_samane.fragments.Site_logs;
import com.apps.morfiwifi.morfi_project_samane.fragments.Transfers;
import com.apps.morfiwifi.morfi_project_samane.fragments.Requests;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.List;

public class StatesticActivity extends SiteMasterActivity{

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statestic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        getApplicationContext()
//
        /*FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.addTab(getTabSpec1(fragmentTabHost), FirstActivity.class, null);
        fragmentTabHost.addTab(getTabSpec2(fragmentTabHost), SecondActivity.class, null);
*/

        int[] icons = {
                R.drawable.ic_request,
                R.drawable.ic_transfer,

                //fake center fragment, so that it creates place for raised center tab.
                R.drawable.ic_logout,

                R.drawable.ic_feedback,
                R.drawable.ic_info_about_us
        };
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_tab_content);

        setupViewPager(viewPager);


        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < icons.length; i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }
        tabLayout.getTabAt(0).select();

        setTitle("آمار");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.insertNewFragment(new Requests ());
        adapter.insertNewFragment(new Transfers());

        //fake center fragment, so that it creates place for raised center tab.
        adapter.insertNewFragment(new Site_logs());

        adapter.insertNewFragment(new Site_feeds());
        adapter.insertNewFragment(new Extra_frag());
        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void insertNewFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

    /*public void openCameraActivity(View view) {
        Toast.makeText(this, "OPEN CAMERA ", Toast.LENGTH_SHORT).show();
    }*/


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, AwesomeActivity.class);
                startActivity(settingsIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public void refresh_view() {
//        super.refresh_view();
        // BASED ON CHOOSEN TAB !
        // TODO: 10/2/2018 REFRESH THE VIEW BASED ON CHOOSEN TAB ....

        Init.start_loading(this);
        final StatesticActivity activity = this;
        handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
//                tv.append("Hello World");
//                handler.postDelayed(this, 1000);
                Init.stop_loading(activity);
                Log.d("SERVICE :" , "NOTIFICATION NOTYFIED");
            }
        };

        handler.postDelayed(r, 500);

    }
}
