package com.apps.morfiwifi.morfi_project_samane.view;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh;
import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh_type;
import com.apps.morfiwifi.morfi_project_samane.models.Gozaresh_typeDao;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.UserDao;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.Calendar;
import java.util.List;

public class gozaresh_RecyclerAdapter extends RecyclerView.Adapter<ViewHolder_gozaresh> {

    private List<Gozaresh> gozareshList; // our items !
    private static  RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    User sener_user ;
    Gozaresh_type gozaresh_type;
    List<Gozaresh_type> gozaresh_typeList;

    public gozaresh_RecyclerAdapter (List<Gozaresh> gozareshList) {
        this.gozareshList = gozareshList;
    }

    @Override
    public ViewHolder_gozaresh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gozaresh_recive_item , parent , false); // Gozaresh Item
        return new ViewHolder_gozaresh(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_gozaresh holder, int position) {
        final Gozaresh sample_gozaresh = gozareshList.get(position);



        List<User> users = Repository.GetInstant(activity).getUserDao().queryBuilder()
                .where(UserDao.Properties.Id.eq(sample_gozaresh.user_id))
                .list();

        if (users.size() > 0){
            sener_user = users.get(0);
        }else {
            sener_user = new User();
            sener_user.setFName( "Unknown");
            sener_user.setLName( "Unknown");
        }

        final List<Gozaresh_type> gozaresh_types = Repository.GetInstant(activity).getGozaresh_typeDao().queryBuilder()
                .where(Gozaresh_typeDao.Properties.Id.eq(sample_gozaresh.type_id))
                .list();

        if (gozaresh_types.size() > 0){
            gozaresh_type = gozaresh_types.get(0);
        }else {
            gozaresh_type = new Gozaresh_type();
            gozaresh_type.setName( "Unknown");
            gozaresh_type.setPr_name( "Unknown");
        }

        sample_gozaresh.gozaresh_type = gozaresh_type; // setting Directly!

        holder.sender_name.setText(sener_user.getFName());
        holder.sender_lname.setText(sener_user.getLName());
        holder.gozaresh_type.setText(gozaresh_type.getPr_name());

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                LinearLayout bottom_sheet = (LinearLayout)
                        activity.findViewById(R.id.bottom_sheet_std_gozaresh_rec);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                if (sample_gozaresh.seen_date == null){
                    // Juset First Update!
                    sample_gozaresh.seen_date = Calendar.getInstance().getTime();
                    Repository.GetInstant(activity).getGozareshDao().update(sample_gozaresh);
                }

                gozaresh_typeList = Repository.GetInstant(activity).getGozaresh_typeDao().loadAll();

                Gozaresh.State[] vals = Gozaresh.State.values();
                
                
                TextView matn = bottom_sheet.findViewById(R.id.tv_gozaresh_matn_rec);
                TextView type = bottom_sheet.findViewById(R.id.tv_gozaresh_type);
                final Spinner states = bottom_sheet.findViewById(R.id.sp_new_state);
                TextView date = bottom_sheet.findViewById(R.id.tv_gozaresh_date);

                matn.setText(sample_gozaresh.getSharh());
                type.setText( "وضعیت : " + sample_gozaresh.get_State()  +" موضوع : "+ sample_gozaresh.gozaresh_type  );
                date.setText(sample_gozaresh.getDate().toString());

                ArrayAdapter<Gozaresh.State> spinnerArrayAdapter = new ArrayAdapter<>(activity,   android.R.layout.simple_spinner_item, vals);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                states.setAdapter(spinnerArrayAdapter);

                bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // accepted
                        Gozaresh.State new_state = (Gozaresh.State) states.getSelectedItem();
                        sample_gozaresh.set_State(new_state);
                        Repository.GetInstant(activity).getGozareshDao().update(sample_gozaresh);
                        Init.Toas(activity , "تفییرات اعمال شد");
                        // Juset checking ....
                        Init(gozareshList , activity);
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
        });
    }

    @Override
    public int getItemCount() {
        return gozareshList.size();
    }

    public static void view_fixer(List<Gozaresh> gozareshList , AppCompatActivity activity){
        if (gozareshList == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.GONE);
        }else{
            if (gozareshList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_gozaresh_recive).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(List<Gozaresh> gozareshList , AppCompatActivity activity){
        view_fixer(gozareshList , activity);
        recyclerView = activity.findViewById(R.id.rec_gozaresh_recive);
        gozaresh_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new gozaresh_RecyclerAdapter(gozareshList));

    }

}
