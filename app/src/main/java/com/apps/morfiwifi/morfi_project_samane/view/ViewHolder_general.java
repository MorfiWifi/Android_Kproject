package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class ViewHolder_general extends RecyclerView.ViewHolder {
    public LinearLayout lin ;
    public TextView first;
    public  TextView second;
    public  TextView third;

    public ViewHolder_general(View itemView) {
        super(itemView);

        lin = itemView.findViewById(R.id.lin_general_item);
        first = itemView.findViewById(R.id.tv_first_text);
        second = itemView.findViewById(R.id.tv_second_text);
        third = itemView.findViewById(R.id.tv_third_text);
    }
}
