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
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;

//import com.booleanbites.tabsample.R;

/**
 * @author Wifimorfi
 *
 */
public class Requests extends Fragment {
    AppCompatActivity activity ;
    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request_lay, container, false);
        Request.load_requests(activity , true );
        return v;
    }

    public void load_request (List<Request> requests){
        //todo feeds init the RECYCLER
        general_RecyclerAdapter.Init(requests
                , activity , Init.Mod.request , false ,true);

    }
}
