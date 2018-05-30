package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;

public class ViewHolder_samane extends RecyclerView.ViewHolder {

//    public ImageView image ;
    public TextView t1;
    public LinearLayout layout;
//    public  TextView t2;
//    public  TextView t3;
//    public  ImageView im3dot;

    public ViewHolder_samane(View itemView) {
        super(itemView);

//        image = (ImageView) itemView.findViewById(R.id.im_message_image);
//        im3dot = (ImageView) itemView.findViewById(R.id.im_message_more);
        t1 = (TextView) itemView.findViewById(R.id.tv_samane_name);
        layout = (LinearLayout) itemView.findViewById(R.id.lin_samane_view);
//        t2 = (TextView) itemView.findViewById(R.id.tv_message_t2);
//        t3 = (TextView) itemView.findViewById(R.id.tv_message_t3);
    }

}
