package com.apps.morfiwifi.morfi_project_samane.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket_Message;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecyclerAdapter_tickmessage extends RecyclerView.Adapter<ViewHolder_tickmessage>  {

    private List<Ticket_Message> ticket_messages; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    List<Thing> things;

    public RecyclerAdapter_tickmessage(List<Ticket_Message> ticket_messages) {
        if (ticket_messages == null){
            this.ticket_messages = new ArrayList<>();
            return;
        }
        this.ticket_messages = ticket_messages;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public ViewHolder_tickmessage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tickmessage_recived , parent , false); // Report Item
        return new ViewHolder_tickmessage(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_tickmessage holder, int position) {

        if (ticket_messages.get(position).isLoading){
            holder.loading.setVisibility(View.VISIBLE);
            holder.loading.smoothToShow();
            holder.message.setText(Init.notNull(ticket_messages.get(position).parseObject.get("mess")));
            holder.date.setText("...");
        }else {
            holder.loading.smoothToHide();
            holder.loading.setVisibility(View.GONE);
            holder.message.setText(Init.notNull(ticket_messages.get(position).parseObject.get("mess")));
            if (ticket_messages.get(position).parseObject.getCreatedAt() == null)
                holder.date.setText("خطا");
            if (ticket_messages.get(position).parseObject.getCreatedAt() != null)
                holder.date.setText(shamsiDate.persian_date(ticket_messages.get(position).parseObject.getCreatedAt()));
        }

        if (ParseUser.getCurrentUser().getObjectId().equals(ticket_messages.get(position).parseObject.getParseUser("CreatedBy").getObjectId())){
            holder.detail.setText("خودم");
//            holder.rspace.setVisibility(View.GONE);
            holder.item_back.setBackgroundResource(R.drawable.background_message_sent);
            holder.item_parent.setGravity(Gravity.END);
        }else {
            holder.detail.setText(Init.notNull(ticket_messages.get(position).parseObject.get("ROLE_NAME")) +" , "
                    + Init.notNull(ticket_messages.get(position).parseObject.get("SENDER_USERNAME")) );
//            holder.lspace.setVisibility(View.GONE);  SENDER_USERNAME
            holder.item_back.setBackgroundResource(R.drawable.background_message_recive);
            holder.item_parent.setGravity(Gravity.START);
        }

        if (ticket_messages.get(position).parseObject.getBoolean("ERJA")){
//            holder.rspace.setVisibility(View.VISIBLE);
//            holder.rspace.setVisibility(View.VISIBLE);
            String base = "ارجاء شده به : ";
            base = base + (Init.notNull(ticket_messages.get(position).parseObject.get("ERJA_ROLE_NAME")));
            holder.message.setText(base);
            holder.item_back.setBackgroundResource(R.drawable.background_erja);
            holder.item_parent.setGravity(Gravity.CENTER);
        }


        holder.activity = activity;
        holder.ticket_message = ticket_messages.get(position);
        holder.item_back.setOnClickListener(holder);


    }

    @Override
    public int getItemCount() {
        return ticket_messages.size();
    }

    public static RecyclerAdapter_tickmessage Init(List<Ticket_Message> ticket_messages, AppCompatActivity activity){
        // TODO: 11/9/2018 FIX CHOOSEN RECYCLER
        recyclerView = activity.findViewById(R.id.rec_tick_message);
        RecyclerAdapter_tickmessage.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        RecyclerAdapter_tickmessage x = new RecyclerAdapter_tickmessage(ticket_messages);
        recyclerView.setAdapter(x);
        return x;
    }

    public void Set_List(List<Ticket_Message> ticket_messages) {
        if (ticket_messages != null){
            this.ticket_messages = ticket_messages;
            notifyDataSetChanged();
        }
    }
}
