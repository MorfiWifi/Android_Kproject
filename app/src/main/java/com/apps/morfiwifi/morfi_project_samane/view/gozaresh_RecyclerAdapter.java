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
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
//import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh_typeDao;
import com.apps.morfiwifi.morfi_project_samane.models.Report_typeDao;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.UserDao;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.Calendar;
import java.util.List;

public class gozaresh_RecyclerAdapter extends RecyclerView.Adapter<ViewHolder_gozaresh> {

    private List<Report> reportList; // our items !
    private static  RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    Report_type report_type;
    List<Report_type> report_typeList;

    public gozaresh_RecyclerAdapter (List<Report> reportList) {
        this.reportList = reportList;
    }

    @Override
    public ViewHolder_gozaresh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gozaresh_recive_item , parent , false); // Report Item
        return new ViewHolder_gozaresh(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_gozaresh holder, int position) {
        final Report sample_report = reportList.get(position);



        List<User> users = Repository.GetInstant(activity).getUserDao().queryBuilder()
                .where(UserDao.Properties.Id.eq(sample_report.user_id))
                .list();

        if (users.size() > 0){
            sener_user = users.get(0);
        }else {
            sener_user = new User();
            sener_user.setFName( "Unknown");
            sener_user.setLName( "Unknown");
        }

        final List<Report_type> report_types = Repository.GetInstant(activity).getReport_typeDao().queryBuilder()
                .where(Report_typeDao.Properties.Id.eq(sample_report.type_id))
                .list();

        if (report_types.size() > 0){
            report_type = report_types.get(0);
        }else {
            report_type = new Report_type();
            report_type.setName( "Unknown");
            report_type.setPr_name( "Unknown");
        }

        sample_report.report_type = report_type; // setting Directly!

        holder.sender_name.setText(sener_user.getFName());
        holder.sender_lname.setText(sener_user.getLName());
        holder.gozaresh_type.setText(report_type.getPr_name());

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_std_gozaresh_rec);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                if (sample_report.seen_date == null){
                    // Juset First Update!
                    sample_report.seen_date = Calendar.getInstance().getTime();
                    Repository.GetInstant(activity).getReportDao().update(sample_report);
                }

                report_typeList = Repository.GetInstant(activity).getReport_typeDao().loadAll();

                Report.State[] vals = Report.State.values();
                
                
                TextView matn = bottom_sheet.findViewById(R.id.tv_gozaresh_matn_rec);
                TextView type = bottom_sheet.findViewById(R.id.tv_gozaresh_type);
                final Spinner states = bottom_sheet.findViewById(R.id.sp_new_state);
                TextView date = bottom_sheet.findViewById(R.id.tv_gozaresh_date);

                matn.setText(sample_report.getSharh());
                type.setText( "وضعیت : " + sample_report.get_State()  +" موضوع : "+ sample_report.report_type);
                date.setText(sample_report.getDate().toString());

                ArrayAdapter<Report.State> spinnerArrayAdapter = new ArrayAdapter<>(activity,   android.R.layout.simple_spinner_item, vals);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                states.setAdapter(spinnerArrayAdapter);

                bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // accepted
                        Report.State new_state = (Report.State) states.getSelectedItem();
                        sample_report.set_State(new_state);
                        Repository.GetInstant(activity).getReportDao().update(sample_report);
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

    public static void view_fixer(List<Report> reportList, AppCompatActivity activity){
        if (reportList == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.GONE);
        }else{
            if (reportList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<Report> reportList, AppCompatActivity activity){
        view_fixer(reportList, activity);
        recyclerView = activity.findViewById(R.id.rec_gozaresh_recive);
        gozaresh_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new gozaresh_RecyclerAdapter(reportList));

    }

}
