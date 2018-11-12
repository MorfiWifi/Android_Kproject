package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;

import java.util.Calendar;
import java.util.List;

public class RecyclerAdapter_active_stu extends RecyclerView.Adapter<ViewHolder_active_stu> {
    private List<User> users; // our items !
    private static  RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activity
    private Properties properties;
    private static User current_user;
    private static BottomSheetBehavior bottomSheetBehavior;
    private static  LinearLayout bottom_sheet;
    private static void set_buttom_sheet(LinearLayout bottom_sheet1,BottomSheetBehavior bottomSheetBehavior1){
        bottom_sheet = bottom_sheet1;
        bottomSheetBehavior = bottomSheetBehavior1;}

    public RecyclerAdapter_active_stu(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder_active_stu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_signup, parent , false);
        return new ViewHolder_active_stu(view);
    }

    public static void expand_buttom_sheet (Properties properties){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        TextView name = bottom_sheet.findViewById(R.id.tv_std_queue_name);
        TextView lastname = bottom_sheet.findViewById(R.id.tv_std_queue_lastname);
        TextView kartmelli = bottom_sheet.findViewById(R.id.tv_std_queue_kodmelli);
        TextView username = bottom_sheet.findViewById(R.id.tv_std_queue_username);
        TextView date = bottom_sheet.findViewById(R.id.tv_std_queue_date);

        name.setText(properties.real_name);
        lastname.setText(properties.real_lastname);
        kartmelli.setText(properties.national_cod);
        username.setText(current_user.UserName);

        shamsiDate shamsiDate = new shamsiDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current_user.createAt);

        String date_STR = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR)  , (calendar.get(Calendar.MONTH)+1) ,  calendar.get(Calendar.DAY_OF_MONTH));


        date.setText(date_STR);

        bottom_sheet.findViewById(R.id.btn_std_queue_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // accepted
//                sample_user.Active = (true);
//                sample_user.PreActive = (true);
//                sample_user.should_fill_init_forms = (false);
//                        Repository.GetInstant(activity).getUserDao().update(sample_user);


                User.active_std( activity , true,current_user);
//                Init.Toas(activity , " فعال شد"); // TODO: 9/23/2018 wait for second to complete
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        bottom_sheet.findViewById(R.id.btn_std_queue_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // deleted
//                if (current_user.id != null*/)
                User.delete_std(activity , true,current_user);

//                Init.Toas(activity , "مثلا حذف شد");
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });



        bottom_sheet.findViewById(R.id.im_close_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder_active_stu holder, int position) {
        final User sample_user = users.get(position);
        holder.id.setText("ID : "+ sample_user.id); // YET DIDNT INPUT ANY STUDENT ID !
        holder.name.setText(sample_user.UserName);

        shamsiDate shamsiDate = new shamsiDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sample_user.createAt);

        String date_STR = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR)  , (calendar.get(Calendar.MONTH)+1) ,  calendar.get(Calendar.DAY_OF_MONTH));
        holder.lastname.setText(date_STR);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_active_std_queue);


                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                current_user = sample_user;

                Properties.load_properties(activity , true , false , sample_user.id);






            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static void view_fixer(List<User> users , AppCompatActivity activity){
        if (users == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.GONE);
        }else{
            if (users.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_signup_su_recyclere).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<User> users , AppCompatActivity activity){
        view_fixer(users , activity);
        recyclerView = activity.findViewById(R.id.rec_signup_su_recyclere);
        RecyclerAdapter_active_stu.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new RecyclerAdapter_active_stu(users));
        LinearLayout bottom_sheet = (LinearLayout)
                activity.findViewById(R.id.bottom_sheet_active_std_queue);
         bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        set_buttom_sheet(bottom_sheet ,bottomSheetBehavior);

    }
}
