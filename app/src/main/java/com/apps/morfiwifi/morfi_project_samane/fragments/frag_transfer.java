package com.apps.morfiwifi.morfi_project_samane.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.apps.morfiwifi.morfi_project_samane.view.general_RecyclerAdapter;

import java.util.List;
import java.util.Objects;

//import com.booleanbites.tabsample.R;

/**
 * @author Wifimorfi
 *
 *
 */
public class frag_transfer extends Fragment {
    AppCompatActivity activity ;
    static List<Transfer> _static ;
    private SwipeRefreshLayout swipeContainer;

    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Transfer.load_transfers(activity , true );

//        activity.setTitle("TRANSFETRS");

        Log.d(Init.FRAGMENT , "_static ONCREATE INFLATED");
        View view = inflater.inflate(R.layout.fragment_transfer_lay, container, false);
        general_RecyclerAdapter.Init_fragment(_static , getContext()
                , view , Init.Mod.transfer , false ,true ,activity); // getview || view ?
        return view;
    }

    @Override
    public void onStart() {
        Log.d(Init.FRAGMENT , "_static ONSTART INFLATED");
//        setContentView(R.layout.activity_main);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) getView().findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(Init.FRAGMENT , "_static ONRESUME INFLATED");
        super.onResume();
    }

    public void load_transfers (List<Transfer> transfers){
        Log.d(Init.FRAGMENT , "_static DATA_LOADED");
        _static = transfers;
        if (getView() == null)
            return;
        //todo feeds init the RECYCLER
        general_RecyclerAdapter.Init_fragment(transfers , getContext()
                , getView() , Init.Mod.transfer , false ,true , activity);
    }

    public void fetchTimelineAsync(int page) {
        swipeContainer.setRefreshing(false);
    }

    public void do_refrersh (){
        if (_static != null){
            general_RecyclerAdapter.Init_fragment(_static , getContext()
                    , getView() , Init.Mod.transfer , false ,true , activity);
        }
    }

    public void show_bottom_sheet (final Transfer transfer){
//        getView().findViewById(R.id.bottom_general_fr_transfer)
        LinearLayout bottom_sheet = (LinearLayout)
                getView().findViewById(R.id.bottom_general_fr_transfer); // IDONTFULLY UNDERSTAND THE FLOW!
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
        temp_text = "جابجایی از خوابگاه : " + transfer.current_kh.name +"\n"+
                "بلوک : " + transfer.current_bl.name + "\n" +
                "اطاق : " + transfer.current_room.name + "\n"+
                "به خوابگاه : " + transfer.new_kh + "\n"+
                "بلوک : " + transfer.new_bl + "\n" +
                "اطاق : " + transfer.new_room +"\n"+
                "در تاریخ : " + shamsiDate.persian_date(transfer.transfer_date)+"\n"+
                "انجام خواهد شد";
        str_4 = transfer.sender_id;
        str_2 = shamsiDate.persian_date(transfer.createAt);
        str_3 = " موضوع : "+ "جابجابی"+ " وضعیت : " + transfer.state;
        loud_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.user_info_dialogue(activity, transfer.sender_id);
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
                Transfer.set_new_state ( activity, transfer.Id , (( Report.State)states.getSelectedItem()).ordinal());
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

    public void hide_bottom_sheet (){
        LinearLayout bottom_sheet = (LinearLayout)
                Objects.requireNonNull(getView()).findViewById(R.id.bottom_general_fr_transfer); // IDONTFULLY UNDERSTAND THE FLOW!
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

}
