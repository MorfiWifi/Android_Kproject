package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket_Message;
import com.wang.avi.AVLoadingIndicatorView;

public class ViewHolder_tickmessage extends RecyclerView.ViewHolder  implements View.OnClickListener {
    public TextView message ;
    public TextView date ;
    public TextView detail ;

    public AVLoadingIndicatorView loading;
    public RelativeLayout item_back;
    public RelativeLayout item_parent;
    public RelativeLayout lspace;
    public RelativeLayout rspace;
    public Ticket_Message ticket_message;
    public AppCompatActivity activity;

    public ViewHolder_tickmessage(View itemView) {
        super(itemView);

        detail = itemView.findViewById(R.id.tv_tick_meddsge_extra_data);
        message = itemView.findViewById(R.id.tv_tick_header);
//        tick_review = itemView.findViewById(R.id.tv_tick_review);
        date = itemView.findViewById(R.id.tv_tick_date);
//        image = itemView.findViewById(R.id.im_ticket);
        loading = itemView.findViewById(R.id.avi);
        item_back = itemView.findViewById(R.id.rel_tickt_message_item);
//        lspace = itemView.findViewById(R.id.rel_lspace);
//        rspace = itemView.findViewById(R.id.rel_rspace);
        item_parent = itemView.findViewById(R.id.rel_message_parent);
    }

    @Override
    public void onClick(View v) {

    }
}
