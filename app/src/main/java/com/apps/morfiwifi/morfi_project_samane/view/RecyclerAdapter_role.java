package com.apps.morfiwifi.morfi_project_samane.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Role_model;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter_role extends RecyclerView.Adapter<ViewHolder_role> {
    private List<Role_model> role_models; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    List<Thing> things;

    public RecyclerAdapter_role(List<Role_model> role_models) {
        if (role_models == null){
            this.role_models = new ArrayList<>();
            return;
        }
        this.role_models = role_models;
    }

    @Override
    public ViewHolder_role onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_role_view , parent , false); // Report Item
        return new ViewHolder_role(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder_role holder, int position) {

//        if (role_models.get(position))
        holder.activity = activity;
        holder.role = role_models.get(position);
        holder.image.setOnClickListener(holder);

        String name = Init.notNull(role_models.get(position).parseObject.get("name"));
        int cod = (int) role_models.get(position).parseObject.getNumber("cod");
        holder.role_naem.setText(name);

        if (cod >= 4){
            holder.image.setImageResource(R.drawable.ic_admin_2);

        }else if (cod == 1){
            holder.image.setImageResource(R.drawable.ic_worker_2);
        }else if (cod == 2){
        holder.image.setImageResource(R.drawable.ic_cook);
        } else if (cod == 3){
        holder.image.setImageResource(R.drawable.ic_travel);
        }
        else if (cod == 0){
            holder.image.setImageResource(R.drawable.ic_student_2);
        }else {
            holder.image.setImageResource(R.drawable.ic_casher);
        }


    }

    @Override
    public int getItemCount() {
        return role_models.size();
    }

    public static RecyclerAdapter_role Init(List<Role_model> ticket_messages, AppCompatActivity activity){
        // TODO: 11/9/2018 FIX CHOOSEN RECYCLER
        recyclerView = activity.findViewById(R.id.rec_roles);
        RecyclerAdapter_role.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        RecyclerAdapter_role x = new RecyclerAdapter_role(ticket_messages);
        recyclerView.setAdapter(x);

        return x;
    }

    public void Set_List(List<Role_model> ticket_messages) {
        if (ticket_messages != null){
            this.role_models = ticket_messages;
            notifyDataSetChanged();
        }
    }



}
