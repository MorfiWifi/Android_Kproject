package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.ui.ReciverActivity;

import java.util.List;

/**
 * Created by WifiMorfi on 3/25/2018.
 */


public class message_RecyclerAdapter extends RecyclerView.Adapter<ViewHolder_message> {
    private List<Message> messages;
    private static  RecyclerView recyclerView;
    private static AppCompatActivity activity;

    public message_RecyclerAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder_message onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item , parent , false);
        return new ViewHolder_message(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder_message holder, int position ) {
        Message sample_message = messages.get(position);
        // TODO: 3/25/2018  Choose Aproprate Image for Message (Be aware !)
        // TODO: 3/25/2018  ocnverting Date To persian One!!
        // {holder.image = case {type of User} }
        holder.t3.setText(sample_message.Send_Date);
        holder.t1.setText(sample_message.Sender_ID); // Use Message Header Insetead!
        holder.t3.setText(sample_message.Tags); // Minimall Tags! (Not All OF Them !)

        holder.im3dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ReciverActivity){
                    LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet);
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return messages.size();
    }



    public static void Init(List<Message> messages , AppCompatActivity activity){
        recyclerView = activity.findViewById(R.id.rec_messages_recycle);
        message_RecyclerAdapter.activity = activity;
        //recyclerView.refreshDrawableState();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new message_RecyclerAdapter(messages));

    }
}