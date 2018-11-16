package com.apps.morfiwifi.morfi_project_samane.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentTicketActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.ticket.TicketMessageActivity;
import com.wang.avi.AVLoadingIndicatorView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder_ticket extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tick_header;
    TextView tick_review;
    TextView tick_date;
    CircleImageView image;
    AVLoadingIndicatorView loading;
    RelativeLayout item_back;
    Ticket ticket;
    AppCompatActivity activity;

    public ViewHolder_ticket(View itemView) {
        super(itemView);
        tick_header = itemView.findViewById(R.id.tv_tick_header);
        tick_review = itemView.findViewById(R.id.tv_tick_review);
        tick_date = itemView.findViewById(R.id.tv_tick_date);
        image = itemView.findViewById(R.id.im_ticket);
        loading = itemView.findViewById(R.id.avi);
        item_back = itemView.findViewById(R.id.rel_tickt_item);
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        // do that
//        Toast.makeText(activity, "TIC:"+ticket.isLoading, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity , TicketMessageActivity.class);
        if (User.current_user.cod == 0){
            intent.putExtra("SENDER" , StudentTicketActivity.STUDENT);
        }else {
            intent.putExtra("SENDER" , "NOT STUDENT ANY !");
        }

        intent.putExtra("TIVKET_ARRIVED" , true);
        TicketMessageActivity.setTicket(ticket);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);


    }
}
