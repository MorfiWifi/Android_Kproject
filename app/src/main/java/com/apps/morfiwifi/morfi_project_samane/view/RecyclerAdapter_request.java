package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;

import java.util.Calendar;
import java.util.List;

public class RecyclerAdapter_request extends RecyclerView.Adapter<ViewHolder_request>  {



    private List<Request> reportList; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    List<Thing> things;

    public RecyclerAdapter_request(List<Request> reportList) {
        this.reportList = reportList;
    }

    @Override
    public ViewHolder_request onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gozaresh_recive_item , parent , false); // Report Item
        return new ViewHolder_request(view);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder_request holder, int position) {



        final Request sample_report = reportList.get(position);

        holder.sender_name.setText(sample_report.thing.name);
        holder.sender_lname.setText(sample_report.state.toString());


        shamsiDate shamsiDate = new shamsiDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sample_report.createAt);
        String dati = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH)+1 , calendar.get(Calendar.DATE));

        holder.gozaresh_type.setText(dati);



        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_std_gozaresh_rec);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                Report.State[] vals = Report.State.values();


                TextView matn = bottom_sheet.findViewById(R.id.tv_gozaresh_matn_rec);
                TextView type = bottom_sheet.findViewById(R.id.tv_gozaresh_type);
                final Spinner states = bottom_sheet.findViewById(R.id.sp_new_state);
                TextView date = bottom_sheet.findViewById(R.id.tv_gozaresh_date);
                TextView sende_id = bottom_sheet.findViewById(R.id.tv_gozaresh_sender_id);

                shamsiDate shamsiDate = new shamsiDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sample_report.createAt);
                String dati = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH)+1 , calendar.get(Calendar.DATE));

                if (sample_report.iscountable){
                    matn.setVisibility(View.VISIBLE);
                    matn.setText("تعداد : " +sample_report.count);
                }else {
                    matn.setVisibility(View.GONE);
                }

                type.setText(" نوع درخواست : "+ sample_report.thing.name+ " وضعیت : " + sample_report.state  );
                date.setText(dati);
                if (activity instanceof DarkhastActivity){
                    sende_id.setText(" فرستنده : " + User.current_user.UserName);
                    bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setVisibility(View.GONE);
                    states.setVisibility(View.GONE);
                }else {
                    sende_id.setText("آیدی فرستنده : " + sample_report.sender_id);
                    bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setVisibility(View.VISIBLE);
                    states.setVisibility(View.VISIBLE);
                }


                ArrayAdapter<Report.State> spinnerArrayAdapter = new ArrayAdapter<>(activity,   android.R.layout.simple_spinner_item, vals);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                states.setAdapter(spinnerArrayAdapter);

                bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // accepted
                        Report.State new_state = (Report.State) states.getSelectedItem();
//                        sample_report.set_State(new_state);
//                        Repository.GetInstant(activity).getReportDao().update(sample_report);
                        Init.Toas(activity , "تفییرات اعمال شد");
                        // Juset checking ....
                        Init(reportList, activity);
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
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public static void view_fixer(List<Request> reportList, AppCompatActivity activity){
        if (reportList == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_request).setVisibility(View.GONE);
        }else{
            if (reportList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_request).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_request).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<Request> reportList, AppCompatActivity activity){
        view_fixer(reportList, activity);
        recyclerView = activity.findViewById(R.id.rec_request);
        RecyclerAdapter_request.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new RecyclerAdapter_request(reportList));

    }

    public static void Init(List<Request> reportList , List<Thing> things, AppCompatActivity activity){
        view_fixer(reportList, activity);
        recyclerView = activity.findViewById(R.id.rec_request);
        RecyclerAdapter_request.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new RecyclerAdapter_request(reportList));

    }

}
