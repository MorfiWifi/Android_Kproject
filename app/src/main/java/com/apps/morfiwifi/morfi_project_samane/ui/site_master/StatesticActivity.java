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
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.List;

public class StatesticActivity extends SiteMasterActivity{

    private Handler handler;
    Requests rec ;
    Transfers tranc;
//    that it create
    Site_logs log ;
    Site_feeds feed;
    Extra_frag extra;
    ViewPager viewPager;

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



        rec = new Requests();
        tranc = new Transfers();
//      create;
        log = new Site_logs();
        feed = new Site_feeds();
        extra = new Extra_frag();

        rec.setActivity(this);
        tranc.setActivity(this);
        log.setActivity(this);
        feed.setActivity(this);
        extra.setActivity(this);

        adapter.insertNewFragment(rec);
        adapter.insertNewFragment(tranc);
        adapter.insertNewFragment(log);
        adapter.insertNewFragment(feed);
        adapter.insertNewFragment(extra);
        viewPager.setAdapter(adapter);
        this.viewPager = viewPager;

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

        /*Init.start_loading(this);
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

        handler.postDelayed(r, 500);*/

        int current_page = viewPager.getCurrentItem();
        Log.d("STATISTICS :" , "page current : " +current_page );
        switch (current_page){
            case 0 :
                Log.d("STATISTICS :" , "page num : " +1 );
                Request.load_requests(this , true);
                break;
            case 1:
                Log.d("STATISTICS :" , "page num : " +2 );
                Transfer.load_transfers(this , true);
                break;
            case 2:
                Log.d("STATISTICS :" , "page num : " +3 );
                break;

            case 3:
                Feedback.load_feedbacks(this , true);
                Log.d("STATISTICS :" , "page num : " +4 );
                break;
            case 4:
                Log.d("STATISTICS :" , "page num : " +5 );
                break;
                default:

                    break;
        }

    }

    public void load_reqs (List<Request> requests){
        rec.load_request(requests);
        Log.d("STATISICS : " , "REQUESTS LOADED");
    }

    public void load_trans (List<Transfer> transfers){
        tranc.load_transfers(transfers);
        Log.d("STATISICS : " , "TRANSFER LOADED");
    }

    public void load_feeds (List<Feedback> feedbacks){
        feed.load_feeds(feedbacks);
        Log.d("STATISICS : " , "FEEDS LOADED");
    }


}
