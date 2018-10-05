package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.fragments.frag_extra;
import com.apps.morfiwifi.morfi_project_samane.fragments.frag_feed;
import com.apps.morfiwifi.morfi_project_samane.fragments.frag_log;
import com.apps.morfiwifi.morfi_project_samane.fragments.frag_transfer;
import com.apps.morfiwifi.morfi_project_samane.fragments.frag_request;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;

import java.util.ArrayList;
import java.util.List;

public class StatesticActivity extends SiteMasterActivity{

    private Handler handler;
    frag_request rec ;
    frag_transfer tranc;
//    that it create
    frag_log log ;
    frag_feed feed;
    frag_extra extra;
    ViewPager viewPager;
    boolean isDetroying = false;

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



        Transfer.load_transfers(this , false);
        Feedback.load_feedbacks(this , false);
        Request.load_requests(this , true);

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



        rec = new frag_request();
        tranc = new frag_transfer();
//      create;
        log = new frag_log();
        feed = new frag_feed();
        extra = new frag_extra();

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

        title_fixer();
    }

    private void title_fixer() {
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                System.out.print("sad");

                int index = viewPager.getCurrentItem();
                switch (index){
                    case 0:
                        setTitle("درخواست ها");
                        break;
                    case 1:
                        setTitle("جابجایی ها");
                        break;
                    case 2:
                        setTitle("لاگ");
                        break;
                    case 3:
                        setTitle("پیشنهادات");
                        break;
                    case 4 :
                        setTitle("آمار تجمعی");
                        break;
                        default:
                            setTitle("آمار");

                }
                if (!isDetroying){
                    handler.postDelayed(this , 200);
                }

            }
        };
        handler.postDelayed(runnable ,200);
    }

    public void show_bottom_sheet(Object object) {
        int current_page = viewPager.getCurrentItem();
        Log.d("STATISTICS :" , "page current : " +current_page );
        switch (current_page){
            case 0 :
                Log.d("STATISTICS :" , "page num : " +0 );
//                Request.load_requests(this , true);
                rec.show_bottom_sheet((Request) object);
                break;
            case 1:
//                Log.d("STATISTICS :" , "page num : " +1 );
//                tranc.do_refrersh();
//                Transfer.load_transfers(this , true);
                tranc.show_bottom_sheet((Transfer) object);
                break;
            case 2:
                Log.d("STATISTICS :" , "page num : " +2 );
                break;

            case 3:
//                Feedback.load_feedbacks(this , true);
                Log.d("STATISTICS :" , "page num : " +3 );
                feed.show_bottom_sheet((Feedback) object);
                break;
            case 4:
                Log.d("STATISTICS :" , "page num : " +4 );
                break;
            default:

                break;
        }
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
    protected void onDestroy() {
        isDetroying = true;
        super.onDestroy();
    }

    @Override
    public void refresh_view() {
        int current_page = viewPager.getCurrentItem();
        Log.d("STATISTICS :" , "page current : " +current_page );
        switch (current_page){
            case 0 :
                Log.d("STATISTICS :" , "page num : " +0 );
                rec.hide_bootom_sheet();
                Request.load_requests(this , true);
                break;
            case 1:
                Log.d("STATISTICS :" , "page num : " +1 );
//                tranc.do_refrersh();.
                tranc.hide_bottom_sheet();
                Transfer.load_transfers(this , true);
                break;
            case 2:
                Log.d("STATISTICS :" , "page num : " +2 );
                break;

            case 3:
                feed.hide_bootom_sheet();
                Feedback.load_feedbacks(this , true);
                Log.d("STATISTICS :" , "page num : " +3 );
                break;
            case 4:
                Log.d("STATISTICS :" , "page num : " +4 );
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
