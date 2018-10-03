package com.apps.morfiwifi.morfi_project_samane.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;

//import com.booleanbites.tabsample.R;

/**
 * @author Wifimorfi
 *
 *
 */
public class Transfers extends Fragment {
    AppCompatActivity activity ;
    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Transfer.load_transfers(activity , true );
        return inflater.inflate(R.layout.fragment_transfer_lay, container, false);
    }

    public void load_transfers (List<Transfer> transfers){
        //todo feeds init the RECYCLER
        general_RecyclerAdapter.Init(transfers
                , activity , Init.Mod.transfer , false ,true);
    }
}
