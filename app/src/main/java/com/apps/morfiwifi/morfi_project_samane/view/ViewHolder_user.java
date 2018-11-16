package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.User_model;
import com.apps.morfiwifi.morfi_project_samane.ui.ticket.ChooseUserActivity;
import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder_user extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView role_name;
    TextView user_name;
//    ParseUser user;
    User_model user_model;
    CircleImageView image;
    ImageButton btn_info;
    AppCompatActivity activity;
    RelativeLayout back;

    public ViewHolder_user(View itemView) {
        super(itemView);
        user_name = itemView.findViewById(R.id.tv_username);
        role_name = itemView.findViewById(R.id.tv_role_name);
        btn_info = itemView.findViewById(R.id.im_user_info);
        image = itemView.findViewById(R.id.im_role_image);
        back = itemView.findViewById(R.id.rel_back);
        btn_info.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO: 11/13/2018 add dialogu for selected user (Property)
//        Toast.makeText(activity, "NO DIALOGUE YET", Toast.LENGTH_SHORT).show();

        if (v.equals(back)){
            if (activity == null) return;
            if (activity instanceof ChooseUserActivity){
                ((ChooseUserActivity) activity).selectUser(user_model);
            }
        }else if (v.equals(btn_info)){
            User.user_info_dialogue(activity , user_model.parseUser.getObjectId());
//            Toast.makeText(activity, "INFO", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(activity, "ELSE", Toast.LENGTH_SHORT).show();
        }

    }
}
