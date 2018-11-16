package com.apps.morfiwifi.morfi_project_samane.view;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket_Message;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.User_model;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter_user extends RecyclerView.Adapter<ViewHolder_user> {
    private List<User_model> user_models; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    private String role_name = Init.Empty;
    User sener_user ;
    List<Thing> things;

    public RecyclerAdapter_user(List<User_model> user_models) {
        if (user_models == null){
            this.user_models = new ArrayList<>();
            return;
        }
        this.user_models = user_models;
    }

    @Override
    public ViewHolder_user onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_view , parent , false); // Report Item
        return new ViewHolder_user(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder_user holder, int position) {

        if (role_name.equals("ادمین سیستم")){
            holder.image.setImageResource(R.drawable.ic_admin);

        }else if (role_name.equals("مسئول سایت")){
            holder.image.setImageResource(R.drawable.ic_worker);

        }else if (role_name.equals("دانشجو")){
            holder.image.setImageResource(R.drawable.ic_student);
        }else {
            holder.image.setImageResource(R.drawable.ic_ticket);
        }
        holder.role_name.setText(role_name);
        holder.user_name.setText(Init.notNull(user_models.get(position).parseUser.getUsername()));
        holder.user_model =user_models.get(position);
        holder.activity = activity;
    }

    @Override
    public int getItemCount() {
        return user_models.size();
    }

    public static RecyclerAdapter_user Init(List<User_model> user_models, AppCompatActivity activity , String role_name){
        // TODO: 11/9/2018 FIX CHOOSEN RECYCLER
        recyclerView = activity.findViewById(R.id.rec_users);
        RecyclerAdapter_user.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        RecyclerAdapter_user x = new RecyclerAdapter_user(user_models);
        x.role_name = role_name;
        recyclerView.setAdapter(x);
        return x;
    }

    public void Set_List(List<User_model> user_models , String role_name) {
        if (user_models != null){
            this.role_name = role_name;
            this.user_models = user_models;
            notifyDataSetChanged();
        }
    }
}
