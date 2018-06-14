package com.apps.morfiwifi.morfi_project_samane.view;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.ReciverActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentMainActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.List;

public class samane_RecyclerAdapter extends RecyclerView.Adapter<ViewHolder_samane> {

    private List<Samane> samanes;
    private static  RecyclerView recyclerView;
    private static AppCompatActivity activity;

    public samane_RecyclerAdapter (List<Samane> samanes) {
        this.samanes = samanes;
    }

    @Override
    public ViewHolder_samane onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samane_item , parent , false);
        return new ViewHolder_samane(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder_samane holder, final int position ) {
        final Samane sample_message = samanes.get(position);

//        holder.t3.setText(sample_message.Recive_Date);
        holder.t1.setText(sample_message.Name); // Use Message Header Insetead!
//        holder.sender_lname.setText(sample_message.Tags); // Minimall Tags! (Not All OF Them !)

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sample_message.Code.equals(Samane.khab)){
                    // Activity To samane!!
                    Intent intent = new Intent(activity , StudentMainActivity.class); // Yet this is khab!
                    activity.startActivity(intent);
                }else{
                    Init.Toas(activity,"این سامانه در حال ساخت .." );
                }
            }
        });






    }


    @Override
    public int getItemCount() {
        return samanes.size();
    }



    public static void Init(List<Samane> samanes , AppCompatActivity activity){
        recyclerView = activity.findViewById(R.id.rec_active_samanes);
        samane_RecyclerAdapter.activity = activity;
        //recyclerView.refreshDrawableState();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new samane_RecyclerAdapter(samanes));

    }


}
