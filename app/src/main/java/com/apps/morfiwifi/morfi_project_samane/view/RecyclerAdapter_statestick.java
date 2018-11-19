package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Stats;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket_Message;
import com.apps.morfiwifi.morfi_project_samane.models.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter_statestick extends RecyclerView.Adapter<ViewHolder_statestic> {
    private List<Stats> statsList; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    List<Thing> things;

    public RecyclerAdapter_statestick(List<Stats> statsList) {
        if (statsList == null){
            this.statsList = new ArrayList<>();
            return;
        }
        this.statsList = statsList;
    }

    @Override
    public ViewHolder_statestic onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statestic_view , parent , false); // Report Item
        return new ViewHolder_statestic(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_statestic holder, int position) {

        holder.activity = activity;
        holder.count.setText(statsList.get(position).value);
        holder.title.setText(statsList.get(position).title);

        if (statsList.get(position).isLoading){
            holder.count.setText("");
            holder.loading.show();
        }else {
            holder.loading.hide();
        }

        holder.queryType = statsList.get(position).queryType;
        holder.back.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static RecyclerAdapter_statestick Init(List<Stats> statsList, AppCompatActivity activity){
        recyclerView = activity.findViewById(R.id.rec_statestic);
        RecyclerAdapter_statestick.activity = activity;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        RecyclerAdapter_statestick x = new RecyclerAdapter_statestick(statsList);
        recyclerView.setAdapter(x);
        return x;
    }

    public void Set_List(List<Stats> statsList) {
        if (statsList != null){
            this.statsList = statsList;
            notifyDataSetChanged();
        }
    }
}
