package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class ViewHolder_active_stu extends RecyclerView.ViewHolder  {
    public TextView id;
    public TextView name;
    public TextView lastname;
    public LinearLayout linearLayout; // for on click

    public ViewHolder_active_stu(View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.tv_signup_id);
        name = itemView.findViewById(R.id.tv_signup_name);
        lastname = itemView.findViewById(R.id.tv_signup_lastname);
        linearLayout = itemView.findViewById(R.id.lin_signup_item);
    }
}
