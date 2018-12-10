package com.apps.morfiwifi.morfi_project_samane.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//rec_ticket
public class RecyclerAdapter_ticket extends RecyclerView.Adapter<ViewHolder_ticket> {

    private List<Ticket> ticketList; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
//    User sener_user ;
//    List<Thing> things;

    public RecyclerAdapter_ticket(List<Ticket> ticketList) {
        if (ticketList == null){
            this.ticketList = new ArrayList<Ticket>();
        }else {
            this.ticketList = ticketList;
        }
    }

    public int AddItem (Ticket ticket){
        ticketList.add(ticketList.size() ,ticket);
            notifyDataSetChanged();
            return ticketList.indexOf(ticket);

//        return -1;
    }

    public void FinishItem (int index){
        if (index > -1){
            ticketList.get(index).isLoading = false;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder_ticket onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket , parent , false); // Report Item
        return new ViewHolder_ticket(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_ticket holder, int position) {

       /* holder.image.setImageResource(
                ticketList.get(position).parseObject.getBoolean("isinternal")?
                        R.drawable.id_ticket_green
                        :R.drawable.ic_ticket
        );*/


//        String name = Init.notNull(role_models.get(position).parseObject.get("name"));
        int cod = ticketList.get(position).parseObject.getInt("creatot_role_cod");
//        holder.role_naem.setText(name);

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


        /*if (ticketList.get(position).parseObject.get("creatot_role_name") == null){
            holder.image.setImageResource(R.drawable.ic_casher);
        } else if (ticketList.get(position).parseObject.get("creatot_role_name").equals("ادمین سیستم")){
            holder.image.setImageResource(R.drawable.ic_admin_2);

        }else if (ticketList.get(position).parseObject.get("creatot_role_name").equals("مسئول سایت")){
            holder.image.setImageResource(R.drawable.ic_worker_2);

        }else if (ticketList.get(position).parseObject.get("creatot_role_name").equals("دانشجو")){
            holder.image.setImageResource(R.drawable.ic_student_2);
        }else {
            holder.image.setImageResource(R.drawable.ic_casher);
        }*/



        if (ticketList.get(position).isLoading){
            holder.image.setVisibility(View.GONE);
            holder.loading.setVisibility(View.VISIBLE);
            holder.loading.smoothToShow();

            holder.tick_header.setText(Init.notNull(ticketList.get(position).parseObject.get("header")));

            holder.tick_date.setText("...");
            holder.tick_review.setText("ارجاء نشده!");
        }else {
            if (!(Boolean) ticketList.get(position).parseObject.get("isopen")){
                holder.image.setImageResource(R.drawable.id_ticket_gray);
                // as it has closed;
            }


            holder.loading.smoothToHide();
            holder.image.setVisibility(View.VISIBLE);
            holder.loading.setVisibility(View.GONE);

            holder.tick_header.setText(Init.notNull(ticketList.get(position).parseObject.get("header")));
            holder.tick_date.setText(shamsiDate.persian_date(ticketList.get(position).parseObject.getCreatedAt()));

            Object b = ticketList.get(position).parseObject.get("ERJA_ROLE_NAME");
            Object c = ticketList.get(position).parseObject.get("CreateBy_username");

            if (c!= null ){
                if (c.toString().equals(ParseUser.getCurrentUser().getUsername())){
                    c = "خودم";
                }
            }

            if (b != null && c != null){
                String text = c.toString() + " => "
                        + "ارجاء شده به : " +
                        b.toString();
                holder.tick_review.setText(text);
            }else if (b != null){
                String text =  "ارجاء شده به : " +
                        b.toString();
                holder.tick_review.setText(text);
            }else { // c != null
                String text = "سازنده : "+ c.toString() ;
                holder.tick_review.setText(text);
            }
        }

        holder.activity = activity;
        holder.ticket = ticketList.get(position);
        holder.item_back.setOnClickListener(holder);


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static void view_fixer(List<Ticket> reportList, AppCompatActivity activity){

        /*if (reportList == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_ticket).setVisibility(View.GONE);
        }else{
            if (reportList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_ticket).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_ticket).setVisibility(View.VISIBLE);
            }
        }*/

    }

    public static RecyclerAdapter_ticket Init(List<Ticket> reportList, AppCompatActivity activity){
        view_fixer(reportList, activity);
        recyclerView = activity.findViewById(R.id.rec_ticket);
//        recyclerView
        RecyclerAdapter_ticket.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        RecyclerAdapter_ticket x = new RecyclerAdapter_ticket(reportList);
        recyclerView.setAdapter(x);
        return x;

    }

    public static void Init(List<Ticket> reportList , List<Thing> things, AppCompatActivity activity){
        view_fixer(reportList, activity);
        recyclerView = activity.findViewById(R.id.rec_ticket);
        RecyclerAdapter_ticket.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new RecyclerAdapter_ticket(reportList));

    }

    public void Set_List(List<Ticket> tickets) {
        if (tickets != null){
            ticketList = tickets;
            notifyDataSetChanged();
        }
    }

    /*public void Notify_Chainge() {
        notifyDataSetChanged();
    }*/
}
