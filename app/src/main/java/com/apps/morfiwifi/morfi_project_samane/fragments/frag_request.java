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
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_general;

import java.util.List;

//import com.booleanbites.tabsample.R;

/**
 * @author Wifimorfi
 *
 */
public class frag_request extends Fragment {
    AppCompatActivity activity ;
    private List<Request> _static;

    public void setActivity (AppCompatActivity activity){
        this.activity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        activity.setTitle("REQUESTS");
        View v = inflater.inflate(R.layout.fragment_request_lay, container, false);
        RecyclerAdapter_general.Init_fragment(_static , getContext()
                , v , Init.Mod.request , false ,true ,activity); // getview || view ?
        Log.d(Init.FRAGMENT , "request ONCREATE INFLATED");
        return v;
    }

    public void load_request (List<Request> requests){
        //todo feeds init the RECYCLER
        _static = requests;
        if (getView() == null)
            return;
        RecyclerAdapter_general.Init_fragment(_static , getContext()
                , getView() , Init.Mod.request , false ,true , activity);

    }

    public void show_bottom_sheet(final Request request) {
        LinearLayout bottom_sheet = (LinearLayout)
                getView().findViewById(R.id.bottom_general_fr_request); // IDONTFULLY UNDERSTAND THE FLOW!
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
//        final Request request = (Request) objects.get(position);
        temp_text = " درخواست : " + request.thing.name +"\n"+
                "تعداد : " + request.count + "\n" +
                "کد : " + request.thing.code + "\n"+
//                                    "به خوابگاه : " + request.new_kh + "\n"+
//                                    "بلوک : " + request.new_bl + "\n" +
//                                    "اطاق : " + request.new_room +"\n"+
//                                    "در تاریخ : " + shamsiDate.persian_date(request.transfer_date)+"\n"+
                "در خواست شده است";
        str_4 = request.sender_id;
        str_2 = shamsiDate.persian_date(request.createAt);
        str_3 = " موضوع : "+ " درخواست "+ " وضعیت : " + request.state;
        loud_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.user_info_dialogue(activity, request.sender_id);
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
                Request.set_new_state ( activity, request.Id , (( Report.State)states.getSelectedItem()).ordinal());

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
                getView().findViewById(R.id.bottom_general_fr_request); // IDONTFULLY UNDERSTAND THE FLOW!
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
