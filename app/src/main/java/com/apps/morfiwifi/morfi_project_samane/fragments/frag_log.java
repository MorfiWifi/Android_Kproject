package com.apps.morfiwifi.morfi_project_samane.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class frag_log extends Fragment {
    AppCompatActivity activity ;
    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_lay, container, false);
//        v.findViewById()
        TextView text = (TextView)v.findViewById(R.id.tv_general_text);
//        text.setText("rotated text here");
//        activity.setTitle("LOGS");
        RotateAnimation rotate= (RotateAnimation)AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        text.setAnimation(rotate);

        return v;


    }

}
