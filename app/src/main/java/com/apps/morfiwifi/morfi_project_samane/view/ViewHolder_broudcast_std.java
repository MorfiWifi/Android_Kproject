package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class ViewHolder_broudcast_std extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public ImageView image ;
    public TextView t1;
    public  TextView t2;
    public  TextView t3;
    public  ImageView im3dot;

    public ViewHolder_broudcast_std(View itemView) {
        super(itemView);

        linearLayout = (LinearLayout) itemView.findViewById(R.id.lin_broudcast);
        image = (ImageView) itemView.findViewById(R.id.im_message_image);
        im3dot = (ImageView) itemView.findViewById(R.id.im_message_more);
        t1 = (TextView) itemView.findViewById(R.id.tv_message_t1);
        t2 = (TextView) itemView.findViewById(R.id.tv_message_t2);
        t3 = (TextView) itemView.findViewById(R.id.tv_message_t3);
    }
}
