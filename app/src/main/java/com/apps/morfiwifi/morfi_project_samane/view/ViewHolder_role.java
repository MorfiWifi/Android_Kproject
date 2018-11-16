package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Role_model;
import com.apps.morfiwifi.morfi_project_samane.ui.ticket.ChooseUserActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder_role extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView role_naem;
    public CircleImageView image;
    public AppCompatActivity activity;
    public Role_model role;

    public ViewHolder_role(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.im_role_image);
        role_naem = itemView.findViewById(R.id.tv_role_name);
    }

    @Override
    public void onClick(View v) {
        if (activity== null) return;
        if (activity instanceof ChooseUserActivity){
            ((ChooseUserActivity) activity).setRole(role);
        }
    }
}
