package com.apps.morfiwifi.morfi_project_samane.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;

public class frag_feed extends Fragment {
    AppCompatActivity activity ;
    static List<Feedback> _static ;
    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         Feedback.load_feedbacks(activity , true );
//        activity.setTitle("FEEDS");
        Log.d(Init.FRAGMENT , "SITE FRAGMENT ONCREATE INFLATED");
         View v = inflater.inflate(R.layout.fragment_feed_lay, container, false);
        general_RecyclerAdapter.Init_fragment(_static , getContext()
                , v , Init.Mod.feedback , false ,true ,activity); // getview || view ?
         return v;
    }

    public void load_feeds (List<Feedback> feedbacks){
        //todo feeds init the RECYCLER
        _static = feedbacks;
        if (getView() == null)
            return;
        general_RecyclerAdapter.Init_fragment(_static , getContext()
                , getView() , Init.Mod.feedback , false ,true , activity);
    }

    public void show_bottom_sheet(final Feedback feedback) {
        LinearLayout bottom_sheet = (LinearLayout)
                getView().findViewById(R.id.bottom_general_fr_feed); // IDONTFULLY UNDERSTAND THE FLOW!
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        TextView tv_1st = bottom_sheet.findViewById(R.id.tv_1st);
        TextView tv_2nd = bottom_sheet.findViewById(R.id.tv_2nd);
        TextView tv_3rd = bottom_sheet.findViewById(R.id.tv_3rd);
        ImageButton loud_user_data = bottom_sheet.findViewById(R.id.im_load_user_data);

        final Spinner states = bottom_sheet.findViewById(R.id.sp_new_state);
        TextView date = bottom_sheet.findViewById(R.id.tv_gozaresh_date);

        String temp_text = Init.Empty;
        String str_2     = Init.Empty;
        String str_3     = Init.Empty;
        String str_4     = Init.Empty;

//        final Transfer transfer = (Transfer) objects.get(position);
//        final Feedback feedback = (Feedback) objects.get(position);
        temp_text =  "عنوان : " + feedback.header +"\n"+
                "متن : " +feedback.content;
        str_4 = feedback.sender_id;
        str_2 = shamsiDate.persian_date(feedback.createAt);
        str_3 = " موضوع : "+ "پیشنهاد"+ " وضعیت : " + feedback.state;

        loud_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.user_info_dialogue(activity, feedback.sender_id);
            }});

        tv_2nd.setText("آیدی فرستنده : " + str_4);
        bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setVisibility(View.VISIBLE);
        states.setVisibility(View.VISIBLE);
        bottom_sheet.findViewById(R.id.rel_bottom).setVisibility(View.VISIBLE);
        loud_user_data.setVisibility(View.VISIBLE);



        tv_1st.setText(str_3);
        date.setText(str_2);
        tv_3rd.setText(temp_text);

        Report.State[] vals = Report.State.values();
        ArrayAdapter<Report.State> spinnerArrayAdapter = new ArrayAdapter<>(activity,   android.R.layout.simple_spinner_item, vals);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        states.setAdapter(spinnerArrayAdapter);

        bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // accepted
                Feedback.set_new_state ( activity, feedback.Id , (( Report.State)states.getSelectedItem()).ordinal());
                Report.State new_state = (Report.State) states.getSelectedItem();
            }
        });


        bottom_sheet.findViewById(R.id.im_close_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });


    }

    public void hide_bootom_sheet(){
        LinearLayout bottom_sheet = (LinearLayout)
                getView().findViewById(R.id.bottom_general_fr_feed); // IDONTFULLY UNDERSTAND THE FLOW!
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
