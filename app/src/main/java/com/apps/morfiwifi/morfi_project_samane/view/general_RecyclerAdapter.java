package com.apps.morfiwifi.morfi_project_samane.view;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.StatesticActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.apps.morfiwifi.morfi_project_samane.utility.Init.Mod.cancelation;

//// STOPSHIP: 8/7/2018 has a general bottom sheet -show or not !
public class general_RecyclerAdapter extends  RecyclerView.Adapter<ViewHolder_general>{
    private Init.Mod mod;
    private String current_mod  = Init.Empty;
    private boolean stdmod = true; // FOR EXTRA DATA FOR NON STUDENTS
    private boolean show_bottom_sheet = true; // FOR EXTRA DATA FOR NON STUDENTS
    private List<Object> objects; // our items !
    private static RecyclerView recyclerView; //this
    private static AppCompatActivity activity; // super activit
    private static View view; // super activit
    private static boolean isFragment = false;
    User sener_user ;
    List<Thing> things;



    public general_RecyclerAdapter (List<Object> objects , boolean stdmod , boolean show_bottom_sheet , Init.Mod mod) {
        this.objects = objects;
        this.stdmod = stdmod;
        this.show_bottom_sheet = show_bottom_sheet;
        this.mod = mod;
    }

    @Override
    public ViewHolder_general onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item , parent , false); // Report Item
        return new ViewHolder_general(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_general holder, final int position) {

        final Object sample = objects.get(position);
        Date date = Calendar.getInstance().getTime();


        String str_1 = Init.Empty;
        switch (mod){
            case cancelation :{
                // CUURNT 1111111111111111111111111111111111111111111111 V
                Cancellation cancellation = (Cancellation) sample;
                holder.first.setText(" موضوع : "+ "انصراف"+ " وضعیت : " + cancellation.state  );
                str_1 = cancellation.sender_id;
                date = cancellation.createAt;

                // CUURNT 1111111111111111111111111111111111111111111111 ^
            }break;
            case feedback :{
                // Cuurent 22222222222222222222222222222222222222222222 V
                Feedback feedback = (Feedback) sample;
                holder.first.setText(" موضوع : "+ "پیشنهاد"+ " وضعیت : " + feedback.state  );
                str_1 = feedback.sender_id;
                date = feedback.createAt;

                // Cuurent 22222222222222222222222222222222222222222222 ^
            }break;
            case transfer: {
                // Cuurent 3333333333333333333333333333333333333333333 V
                Transfer transfer = (Transfer) sample;
                holder.first.setText(" موضوع : "+ "جابجابی"+ " وضعیت : " + transfer.state  );
                str_1 = transfer.sender_id;
                date = transfer.createAt;

                // Cuurent 3333333333333333333333333333333333333333333 ^
            }break;
            case request:{
                Request request = (Request) sample;
                holder.first.setText(" موضوع : "+ "درخواست "+ " وضعیت : " + request.state  );
                str_1 = request.sender_id;
                date = request.createAt;
            }break;



        }


        {// Golobal PART
            holder.third.setText(shamsiDate.persian_date(date));
            if (stdmod)
                holder.second.setText(" فرستنده : " + User.current_user.UserName);
            else
                holder.second.setText(" آیدی فرستنده : " + str_1);

            holder.lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 6/6/2018 Loadup - show some Butotm sheet - just Do it
                    if (activity == null){
                        View activity = view;
                    }

                    if (isFragment){
                        ((StatesticActivity)activity).show_bottom_sheet(objects.get(position));
                        return; //bug   BAD WORK FLOW MAY APPIER
                    }

                    LinearLayout bottom_sheet = (LinearLayout)
                            activity.findViewById(R.id.bottom_general);
                    final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    Report.State[] vals = Report.State.values();


                    TextView tv_1st = bottom_sheet.findViewById(R.id.tv_1st);
                    TextView tv_2nd = bottom_sheet.findViewById(R.id.tv_2nd);
                    TextView tv_3rd = bottom_sheet.findViewById(R.id.tv_3rd);
                    ImageButton loud_user_data = bottom_sheet.findViewById(R.id.im_load_user_data);


                    final Spinner states = bottom_sheet.findViewById(R.id.sp_new_state);
                    TextView date = bottom_sheet.findViewById(R.id.tv_gozaresh_date);

//                    TextView sende_id = bottom_sheet.findViewById(R.id.tv_gozaresh_sender_id);


                    String temp_text = Init.Empty;
                    String str_2     = Init.Empty;
                    String str_3     = Init.Empty;
                    String str_4     = Init.Empty;
                    switch (mod){
                        case cancelation:
                            final Cancellation cancellation = (Cancellation) objects.get(position);
                            temp_text = "انصراف از خوابگاه در تاریخ : " +shamsiDate.persian_date(cancellation.canvle_date)+ " \n"+
                                    "انجام خواهد شد";
                            str_4 = cancellation.sender_id;
                            str_2 = shamsiDate.persian_date(cancellation.createAt);
                            str_3 = " موضوع : "+ "انصراف"+ " وضعیت : " + cancellation.state ;
                            loud_user_data.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    User.user_info_dialogue(activity ,cancellation.sender_id );
                                }
                            });
                            break;
                        case feedback:
                            final Feedback feedback = (Feedback) objects.get(position);
                            temp_text =  "عنوان : " + feedback.header +"\n"+
                                    "متن : " +feedback.content;
                            str_4 = feedback.sender_id;
                            str_2 = shamsiDate.persian_date(feedback.createAt);
                            str_3 = " موضوع : "+ "پیشنهاد"+ " وضعیت : " + feedback.state;
                            loud_user_data.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    User.user_info_dialogue(activity , feedback.sender_id);
                                }
                            });
                            break;

                        case transfer:
                            final Transfer transfer = (Transfer) objects.get(position);
                            temp_text = "جابجایی از خوابگاه : " + transfer.current_kh.name +"\n"+
                                    "بلوک : " + transfer.current_bl.name + "\n" +
                                    "اطاق : " + transfer.current_room.name + "\n"+
                                    "به خوابگاه : " + transfer.new_kh + "\n"+
                                    "بلوک : " + transfer.new_bl + "\n" +
                                    "اطاق : " + transfer.new_room +"\n"+
                                    "در تاریخ : " + shamsiDate.persian_date(transfer.transfer_date)+"\n"+
                            "انجام خواهد شد";
                            str_4 = transfer.sender_id;
                            str_2 = shamsiDate.persian_date(transfer.createAt);
                            str_3 = " موضوع : "+ "جابجابی"+ " وضعیت : " + transfer.state;
                            loud_user_data.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    User.user_info_dialogue(activity ,transfer.sender_id );
                                }
                            });
                            break;

                        case request:
                            final Request request = (Request) objects.get(position);
                            temp_text = " درخواست : " + request.thing.name +"\n"+
                                    "تعداد : " + request.count + "\n" +
                                    "کد : " + request.thing.code + "\n"+
//                                    "به خوابگاه : " + request.new_kh + "\n"+
//                                    "بلوک : " + request.new_bl + "\n" +
//                                    "اطاق : " + request.new_room +"\n"+
//                                    "در تاریخ : " + shamsiDate.persian_date(request.transfer_date)+"\n"+
                                    "در خواست شده است";
                            str_4 = request.sender_id;
                            str_2 = shamsiDate.persian_date(request.createAt);
                            str_3 = " موضوع : "+ " درخواست "+ " وضعیت : " + request.state;
                            loud_user_data.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    User.user_info_dialogue(activity ,request.sender_id );
                                }
                            });
                            break;
                            default:

                    }

                    if (stdmod){
                        tv_2nd.setText(" فرستنده : " + User.current_user.UserName);
                        bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setVisibility(View.GONE);
                        states.setVisibility(View.GONE);
                        bottom_sheet.findViewById(R.id.rel_bottom).setVisibility(View.INVISIBLE);
                        loud_user_data.setVisibility(View.GONE);
                    }else {
                        tv_2nd.setText("آیدی فرستنده : " + str_4);
                        bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setVisibility(View.VISIBLE);
                        states.setVisibility(View.VISIBLE);
                        bottom_sheet.findViewById(R.id.rel_bottom).setVisibility(View.VISIBLE);
                        loud_user_data.setVisibility(View.VISIBLE);

                    }


                    tv_1st.setText(str_3);
                    date.setText(str_2);
                    tv_3rd.setText(temp_text);

                    ArrayAdapter<Report.State> spinnerArrayAdapter = new ArrayAdapter<>(activity,   android.R.layout.simple_spinner_item, vals);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    states.setAdapter(spinnerArrayAdapter);

                    bottom_sheet.findViewById(R.id.btn_gozaresh_new_state).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // accepted

                            if (mod.equals(Init.Mod.cancelation)){
                                Cancellation.set_new_state ( activity, ((Cancellation)objects.get(position)).Id , (( Report.State)states.getSelectedItem()).ordinal());
                            }else if (mod.equals(Init.Mod.feedback)){
                                Feedback.set_new_state ( activity, ((Feedback)objects.get(position)).Id , (( Report.State)states.getSelectedItem()).ordinal());
                            }else if (mod.equals(Init.Mod.request)){
                                Request.set_new_state ( activity, ((Request)objects.get(position)).Id , (( Report.State)states.getSelectedItem()).ordinal());
                            }else if (mod.equals(Init.Mod.transfer)){
                                Transfer.set_new_state ( activity, ((Transfer)objects.get(position)).Id , (( Report.State)states.getSelectedItem()).ordinal());
                            }

                            Report.State new_state = (Report.State) states.getSelectedItem();
//
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
        }// Golobal PART




//        Holder.sender_name.setText(sample_report.thing.name);
//        Holder.sender_lname.setText(sample_report.state.toString());


        shamsiDate shamsiDate = new shamsiDate();
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(sample_report.createAt);
        String dati = shamsiDate.shamsiDate(calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH)+1 , calendar.get(Calendar.DATE));

//        Holder.gozaresh_type.setText(dati);




    }

    @Override
    public int getItemCount() {
        if (objects == null)
            return 0;


        return objects.size();
    }

    public static void view_fixer(List<Object> reportList, AppCompatActivity activity){
        if (reportList == null){
            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.rec_general).setVisibility(View.GONE);
        }else{
            if (reportList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.rec_general).setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                activity.findViewById(R.id.rec_general).setVisibility(View.VISIBLE);
            }
        }
    }

    public static void view_fixer_fr(RecyclerView recyclerView , List<Object> reportList, View activity){
        if (recyclerView == null){
            Log.d(Init.FRAGMENT , "RECYCLER WASS NULL SKIPING!!");
            return;
        }
        if (reportList == null){

            activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            if (reportList.size() == 0){
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            else {
                activity.findViewById(R.id.tv_signup_empty).setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void Init(Object object, AppCompatActivity activity , final Init.Mod mod, boolean stdmod , boolean show_bottom_sheet ){
        List<Object> objects = ((List<Object>) object);
        view_fixer(objects, activity);
        general_RecyclerAdapter.isFragment = false;
        recyclerView = activity.findViewById(R.id.rec_general);
        general_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new general_RecyclerAdapter(objects ,stdmod, show_bottom_sheet ,mod ));


    }

   /* public void asdad(){ // this is USED for single item chainge - loading pics and info along time...
        notifyItemChanged(5);
    }*/

    public static void Init_fragment (Object object, Context context, View view , final Init.Mod mod, boolean stdmod , boolean show_bottom_sheet ,AppCompatActivity activity ){
        List<Object> objects = ((List<Object>) object);


        switch (mod){
            case cancelation:
//                recyclerView = activity.findViewById(R.id.rec_general_fr_cancelation);
                recyclerView = view.findViewById(R.id.rec_general);
                break;
            case transfer:
                recyclerView = view.findViewById(R.id.rec_general_fr_transfer);
                break;
            case request:
                recyclerView = view.findViewById(R.id.rec_general_fr_request);
                break;
            case feedback:
                recyclerView = view.findViewById(R.id.rec_general_fr_feedback);
                break;
                default:
                    recyclerView = view.findViewById(R.id.rec_general);
        }
        view_fixer_fr(recyclerView,objects, view);
        general_RecyclerAdapter.isFragment = true;
        general_RecyclerAdapter.activity = activity;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new general_RecyclerAdapter(objects ,stdmod, show_bottom_sheet ,mod ));
    }


}