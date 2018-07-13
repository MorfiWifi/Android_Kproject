package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.ActiveStudentActivity;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.List;

public class broudcast_RecyclerAdapter  extends RecyclerView.Adapter<ViewHolder_broudcast_std>{
    private List<Broudcast> broudcastList;
    private static  RecyclerView recyclerView;
    private static AppCompatActivity activity;

    public broudcast_RecyclerAdapter(List<Broudcast> broudcastList) {
        this.broudcastList = broudcastList;
    }

    @NonNull
    @Override
    public ViewHolder_broudcast_std onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item , parent , false);
        return new ViewHolder_broudcast_std(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_broudcast_std holder, int position) {
        final Broudcast sample_user = broudcastList.get(position);
        holder.t1.setText(sample_user.header); // YET DIDNT INPUT ANY STUDENT ID !
        holder.t2.setText("NON");
        //sample_user.created_date.toString()
        holder.t3.setText(sample_user.id);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_std_broudcast);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


                TextView name = bottom_sheet.findViewById(R.id.tv_std_queue_name);
                TextView lastname = bottom_sheet.findViewById(R.id.tv_std_queue_lastname);
                TextView kartmelli = bottom_sheet.findViewById(R.id.tv_std_queue_kodmelli);
                TextView username = bottom_sheet.findViewById(R.id.tv_std_queue_username);
                TextView date = bottom_sheet.findViewById(R.id.tv_std_queue_date);

                name.setText("NON YET");
                //sample_user.role_name
                lastname.setText(sample_user.id);
                kartmelli.setText(sample_user.role_name);
                //sample_user.created_date.toString()
                username.setText(sample_user.header);
                date.setText(sample_user.matn);
/*

                bottom_sheet.findViewById(R.id.btn_std_queue_accept).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // accepted
                        sample_user.setActive(true);
                        sample_user.setPreActive(true);
                        sample_user.setShould_fill_init_forms(false);
                        Repository.GetInstant(activity).getUserDao().update(sample_user);

                        if (activity instanceof ActiveStudentActivity){
                            ((ActiveStudentActivity) activity).refresh_view();
                        }

                        Init.Toas(activity , " فعال شد");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                });

                bottom_sheet.findViewById(R.id.btn_std_queue_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // deleted
                        Init.Toas(activity , "مثلا حذف شد");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                });
*/



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
        return broudcastList.size();
    }

    public static void view_fixer(List<Broudcast> users , AppCompatActivity activity){
        if (users == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_broudcast_std).setVisibility(View.GONE);
        }else{
            if (users.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_broudcast_std).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_broudcast_std).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<Broudcast> broudcastList , AppCompatActivity activity){
        view_fixer(broudcastList , activity);
        recyclerView = activity.findViewById(R.id.rec_broudcast_std);
        broudcast_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new broudcast_RecyclerAdapter(broudcastList));

    }
}
