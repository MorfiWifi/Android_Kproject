package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.models.User;

import java.util.List;

public class signup_stu_RecyclerAdapter  extends RecyclerView.Adapter<ViewHolder_signup_stu>{

    private List<User> users; // our items !
    private static  RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activity

    public signup_stu_RecyclerAdapter (List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder_signup_stu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signup_item , parent , false);
        return new ViewHolder_signup_stu(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_signup_stu holder, int position) {
        final User sample_user = users.get(position);
        holder.id.setText(sample_user.getKaet_meli()); // YET DIDNT INPUT ANY STUDENT ID !
        holder.name.setText(sample_user.FName);
        holder.lastname.setText(sample_user.getLName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it

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
        signup_stu_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new signup_stu_RecyclerAdapter(users));

    }
}
