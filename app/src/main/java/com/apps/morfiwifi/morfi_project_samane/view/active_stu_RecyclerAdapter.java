package com.apps.morfiwifi.morfi_project_samane.view;

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
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.ActiveStudentActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SignupQeueActivity;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.List;

public class active_stu_RecyclerAdapter extends RecyclerView.Adapter<ViewHolder_active_stu> {
    private List<User> users; // our items !
    private static  RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activity

    public active_stu_RecyclerAdapter (List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder_active_stu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signup_item , parent , false);
        return new ViewHolder_active_stu(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_active_stu holder, int position) {
        final User sample_user = users.get(position);
        holder.id.setText(sample_user.getKaet_meli()); // YET DIDNT INPUT ANY STUDENT ID !
        holder.name.setText(sample_user.FName);
        holder.lastname.setText(sample_user.getLName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_active_std_queue);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


                TextView name = bottom_sheet.findViewById(R.id.tv_std_queue_name);
                TextView lastname = bottom_sheet.findViewById(R.id.tv_std_queue_lastname);
                TextView kartmelli = bottom_sheet.findViewById(R.id.tv_std_queue_kodmelli);
                TextView username = bottom_sheet.findViewById(R.id.tv_std_queue_username);
                TextView date = bottom_sheet.findViewById(R.id.tv_std_queue_date);

                name.setText(sample_user.getFName());
                lastname.setText(sample_user.getLName());
                kartmelli.setText(sample_user.getKaet_meli());
                username.setText(sample_user.getUserName());
                date.setText(sample_user.getInset_date().toString());

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
        return users.size();
    }

    public static void view_fixer(List<User> users , AppCompatActivity activity){
        if (users == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.GONE);
        }else{
            if (users.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<User> users , AppCompatActivity activity){
        view_fixer(users , activity);
        recyclerView = activity.findViewById(R.id.rec_signup_su_recyclere);
        active_stu_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new active_stu_RecyclerAdapter(users));

    }
}
