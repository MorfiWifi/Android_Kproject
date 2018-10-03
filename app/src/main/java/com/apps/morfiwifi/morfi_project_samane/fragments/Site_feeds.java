package com.apps.morfiwifi.morfi_project_samane.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;

public class Site_feeds extends Fragment {
    AppCompatActivity activity ;
    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         Feedback.load_feedbacks(activity , true );
         View v = inflater.inflate(R.layout.fragment_feed_lay, container, false);
         return v;
    }

    public void load_feeds (List<Feedback> feedbacks){
        //todo feeds init the RECYCLER
        general_RecyclerAdapter.Init(feedbacks
                , activity , Init.Mod.feedback , false ,true);
    }
}
