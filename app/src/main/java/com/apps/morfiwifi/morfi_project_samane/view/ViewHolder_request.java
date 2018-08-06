package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class ViewHolder_request extends RecyclerView.ViewHolder {
    public LinearLayout lin ;
    public TextView sender_name;
    public  TextView sender_lname;
    public  TextView gozaresh_type;

    public ViewHolder_request(View itemView) {
        super(itemView);

        lin = itemView.findViewById(R.id.lin_gozaresh_item);
        sender_name = itemView.findViewById(R.id.tv_sender_name);
        sender_lname = itemView.findViewById(R.id.tv_sender_lname);
        gozaresh_type = itemView.findViewById(R.id.tv_gozaresh_type_item);
    }
}
