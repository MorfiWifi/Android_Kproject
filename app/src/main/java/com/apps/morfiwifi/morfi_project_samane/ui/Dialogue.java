package com.apps.morfiwifi.morfi_project_samane.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;
import com.apps.morfiwifi.morfi_project_samane.models.Feedback;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.Transfer;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.ReportActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.utility.JalaliCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Dialogue {
    private static Dialog cancel_dialogue;
    private static Dialog transfer_dialogue;
    private static String content1 = "";
    private static String content2 = "";
    private static Khabgah khabgah;
    private static Block block;
    private static Room room;
    static boolean first_time = true;


    public AlertDialog Exit_app (final AppCompatActivity activity ){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // Get the layout inflater
        final LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView( null)
                .setTitle("خروج از برنامه ؟")
                // Add action buttons
                .setPositiveButton("خروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //activity.finish();

                        Intent intent = new Intent(activity , LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT" , true);
                        activity.startActivity(intent);
                        dialog.cancel();


                        // sign in the user ...
                    }
                })
                .setNegativeButton("نه", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //cancel(); // Thout this whould be in init
                    }
                });
        return builder.create();
    }

    public AlertDialog Log_out_account (final AppCompatActivity activity ){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // Get the layout inflater
        final LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView( null)
                .setTitle("خروج از حساب کاربری ؟")
                // Add action buttons
                .setPositiveButton("خروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //activity.finish();

                        Intent intent = new Intent(activity , LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                        Init.start_fresh();


                        dialog.cancel();


                        // sign in the user ...
                    }
                })
                .setNegativeButton("بیخیال", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //cancel(); // Thout this whould be in init
                    }
                });
        return builder.create();
    }
    public static void Send_Broudcast (final AppCompatActivity activity){

        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_broudcast);

        // set the custom dialog components - text, image and button


        final TextInputEditText header = (TextInputEditText) dialog.findViewById(R.id.ti1);
        final TextInputEditText matn = (TextInputEditText) dialog.findViewById(R.id.ti2);
        final Spinner roles = (Spinner) dialog.findViewById(R.id.spinner_roles);

        // FIXME: 7/14/2018 SHOULD BE PRe LOADED BUT BE AWARE!!
        ArrayAdapter<role> spinnerArrayAdapter = new ArrayAdapter<role>(activity,   android.R.layout.simple_spinner_item, role.load_roles(activity));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        roles.setAdapter(spinnerArrayAdapter);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String matn_str = matn.getText().toString();
                String heade_str = header.getText().toString();

                if (matn_str.trim().length() < 1 || heade_str.trim().length() < 1){
                    Toast.makeText(activity, "متن یا موضوع ناقص است", Toast.LENGTH_SHORT).show();
                    Init.Terminal("WHAT KIND OF MESSAGE IS THIS ????");
                    return;
                }



                Broudcast broudcast = new Broudcast();
                role selected_role =(role) roles.getSelectedItem();
                broudcast.reciverrole_id = selected_role.id;
                broudcast.role_name = selected_role.name;
                broudcast.matn = matn_str;
                broudcast.header= heade_str;

                Broudcast.send_broundcast(null , false , broudcast , User.current_user);

                dialog.dismiss();
            }
        });

        dialog.show();


    }
    public static void Send_Report (final AppCompatActivity activity){

        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_report);

        // set the custom dialog components - text, image and button


//        final TextInputEditText header = (TextInputEditText) dialog.findViewById(R.id.ti1);
        final TextInputEditText matn = (TextInputEditText) dialog.findViewById(R.id.te_gozaresh_matn);
        final Spinner types = (Spinner) dialog.findViewById(R.id.sp_gozaresh_header);

        // FIXME: 7/14/2018 SHOULD BE PRe LOADED BUT BE AWARE!!
        if (activity instanceof ReportActivity){
            ArrayAdapter<Report_type> spinnerArrayAdapter = new ArrayAdapter<Report_type>(activity,   android.R.layout.simple_spinner_item, ((ReportActivity) activity).getTypes());
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            types.setAdapter(spinnerArrayAdapter);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_send_report);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String matn_str = matn.getText().toString();
//                    String heade_str = header.getText().toString();

                    if (matn_str.trim().length() < 1 ){
                        Toast.makeText(activity, "متن کوتاه است", Toast.LENGTH_SHORT).show();
                        Init.Terminal("WHAT KIND OF MESSAGE IS THIS ????");
                        return;
                    }


                    Report_type report_type = (Report_type) types.getSelectedItem();
                    if (report_type != null){
                        Report.send_self_report(activity ,true , matn_str , report_type.id);

                    }else {
                        Toast.makeText(activity, "نوع گزارش خالی است", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    dialog.dismiss();
                }
            });
        }


        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });



    }
    public static void Send_Request (final AppCompatActivity activity ,final List<Thing> things ){

        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_request);

        final TextInputEditText count =  dialog.findViewById(R.id.te_request_count);
        final TextInputLayout count_pa =  dialog.findViewById(R.id.ti_request_count);
        final Spinner thing_in =  dialog.findViewById(R.id.sp_thign);
        thing_in.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (things != null){
                    if (things.get(position) != null){
                        if (things.get(position).iscountable){
                            count_pa.setVisibility(View.VISIBLE);
                        }else {
                            count_pa.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                count_pa.setVisibility(View.GONE);
            }
        });

        if (activity instanceof DarkhastActivity){
            ArrayAdapter<Thing> spinnerArrayAdapter = new ArrayAdapter<Thing>(activity,   android.R.layout.simple_spinner_item, things);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            thing_in.setAdapter(spinnerArrayAdapter);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_send_request);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String count_str = count.getText().toString();
                    if (thing_in.getSelectedItem() != null){
                        if (((Thing)thing_in.getSelectedItem()).iscountable){
                            if (count_str.length() < 1){
                                Toast.makeText(activity, "یک مقدار انتخواب کنید", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int parsed_dount = 0;
                            try {
                                parsed_dount =  Integer.parseInt(count_str.trim());
                            }catch (Exception e){
                                Log.e("EXception REQUEST : " ,e.getMessage());
                            }


                            Request.send_self_report(activity ,true , parsed_dount ,((Thing)thing_in.getSelectedItem()).Id  );
                            dialog.dismiss();
                        }else {
                            Request.send_self_report(activity ,true , 0 ,((Thing)thing_in.getSelectedItem()).Id  );
                            dialog.dismiss();
                        }
                    }else {
                        Toast.makeText(activity, "خطا در اسال", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            });

        }


        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public static void Send_Feedback (final AppCompatActivity activity ){

        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_feedback);

        final TextInputLayout header =  dialog.findViewById(R.id.ti_enteghad_header);
        final TextInputLayout content =  dialog.findViewById(R.id.ti_enteghad_sharh);


        dialog.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_header   = Objects.requireNonNull(header.getEditText()).getText().toString() , str_content = Objects.requireNonNull(content.getEditText()).getText().toString();


                if ((str_header.length() < 3 )||( str_content.length() < 10)){
                    Toast.makeText(activity, "عنوان یا شرح نا مناسب", Toast.LENGTH_SHORT).show();
                    return;
                }

                Feedback.send_self_feedback(activity , true ,str_header , str_content );
                dialog.dismiss();
            }
        });


        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public static void Send_Give_up (final AppCompatActivity activity ){
        // HAS DATE -COMPLICATED
        // custom dialog
        final Dialog dialog;
        if (cancel_dialogue == null){
            dialog = new Dialog(activity);
            cancel_dialogue = dialog;
        }else {
            cancel_dialogue.dismiss();
            cancel_dialogue = null;
            dialog = new Dialog(activity);
            cancel_dialogue = dialog;
        }


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_giveup);

        final TextView choosen_date =  dialog.findViewById(R.id.tv_enseraf_date);
        final TextInputLayout reason = dialog.findViewById(R.id.txin_enseraf_reason);
        dialog.findViewById(R.id.btn_send).setVisibility(View.GONE); // CaNT SEND WITH NO DATE

        Button button = dialog.findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        dialog.show();




    }

    public static void Send_Give_up (final AppCompatActivity activity,final int y ,final int m ,final int d , String date_str ){
        // HAS DATE -COMPLICATED
        // custom dialog+

        final Dialog dialog;
        if (cancel_dialogue == null){
            dialog = new Dialog(activity);
            cancel_dialogue = dialog;
        }else {
            TextInputLayout reason1 = cancel_dialogue.findViewById(R.id.txin_enseraf_reason);
            content1 = Objects.requireNonNull(reason1.getEditText()).getText().toString();
            Log.d("content :" , content1);
            cancel_dialogue.dismiss();
            cancel_dialogue = null;
            dialog = new Dialog(activity);
            cancel_dialogue = dialog;
        }


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_giveup);

        final TextInputLayout reason = dialog.findViewById(R.id.txin_enseraf_reason);
        final TextView choosen_date =  dialog.findViewById(R.id.tv_enseraf_date);


        choosen_date.setText(date_str);
        Log.d("content : on set " , content1);
        Objects.requireNonNull(reason.getEditText()).setText(content1);


        dialog.show();

        dialog.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // WANT SEND THNIG !

                if (reason.getEditText().getText().toString().length() < 3){
                    Toast.makeText(activity, "دلبل انصراف ؟", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (JalaliCalendar.jalali_to_miladi(y ,m,d).before( Calendar.getInstance().getTime()) ){
                    Toast.makeText(activity, "تاریخ نا معتبر است", Toast.LENGTH_SHORT).show();
                    return;
                }

                Date date_of_go = JalaliCalendar.jalali_to_miladi(y , m , d);
                Cancellation.send_self_cancelation(activity ,true ,reason.getEditText().getText().toString() , date_of_go);
                dialog.dismiss();
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public static void Send_Transfer (final AppCompatActivity activity , final List<Khabgah> khabgahs ,
                                      final List<Block> blocks, final List<Room> rooms , Properties properties){


        final Dialog dialog;
        if (transfer_dialogue == null){
            dialog = new Dialog(activity);
            transfer_dialogue = dialog;
        }else {
//            TextInputLayout reason1 = cancel_dialogue.findViewById(R.id.txin_enseraf_reason);
//            content2 = Objects.requireNonNull(reason1.getEditText()).getText().toString();
            Log.d("content :" , content1);
            transfer_dialogue.dismiss();
            transfer_dialogue = null;
            dialog = new Dialog(activity);
            transfer_dialogue = dialog;
        }

        // custom dialog
//        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_transfer);

        final TextInputEditText count =  dialog.findViewById(R.id.te_request_count);
        final TextInputLayout count_pa =  dialog.findViewById(R.id.ti_request_count);

        Spinner khs1 =  transfer_dialogue.findViewById(R.id.sp_end_khab);
        final Spinner bls1 =  transfer_dialogue.findViewById(R.id.sp_end_block);
        final Spinner rs1 =  transfer_dialogue.findViewById(R.id.sp_end_room);


        dialog.findViewById(R.id.btn_send).setVisibility(View.GONE); // CaNT SEND WITH NO DATE


        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
//                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayAdapter<Khabgah> spinnerArrayAdapter = new ArrayAdapter<Khabgah>(activity,   android.R.layout.simple_spinner_item, khabgahs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        khs1.setAdapter(spinnerArrayAdapter);

        khs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    // TODO: 6/1/2018 Check some out of bound Things
                    bls1.setEnabled(true);
                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(activity ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    bls1.setAdapter(spinnerArrayAdapter);

                }else {
                    bls1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bls1.setEnabled(false);
                rs1.setEnabled(false);

            }

        });


        bls1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    rs1.setEnabled(true);

                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(activity ,   android.R.layout.simple_spinner_item, blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    rs1.setAdapter(spinnerArrayAdapter);

                }else {
                    rs1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rs1.setEnabled(false);
            }

        });


    }
    public static void Send_Transfer (final AppCompatActivity activity , final List<Khabgah> khabgahs ,
                                      final List<Block> blocks, final List<Room> rooms , final Properties properties
                                        , final int y , final int m , final int d , String date_str){


        first_time = true;
        int index1 = 0,index2 = 0,index3 = 0;
        final Dialog dialog;
        if (transfer_dialogue == null){
            dialog = new Dialog(activity);
            transfer_dialogue = dialog;
        }else {

            Spinner khs1 =  transfer_dialogue.findViewById(R.id.sp_end_khab);
            Spinner bls1 =  transfer_dialogue.findViewById(R.id.sp_end_block);
            Spinner rs1 =  transfer_dialogue.findViewById(R.id.sp_end_room);

            index1 = khs1.getSelectedItemPosition();
            index2 = bls1.getSelectedItemPosition();
            index3 = rs1.getSelectedItemPosition();

            Log.d("content2 :" , content2);
            transfer_dialogue.dismiss();
            transfer_dialogue = null;
            dialog = new Dialog(activity);
            transfer_dialogue = dialog;
        }

        // custom dialog

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_new_transfer);

        final TextInputEditText count =  dialog.findViewById(R.id.te_request_count);
        final TextInputLayout count_pa =  dialog.findViewById(R.id.ti_request_count);
        TextView chosen_dare =  dialog.findViewById(R.id.tv_choosen_date);
        Objects.requireNonNull(chosen_dare).setText(date_str);

        final Spinner khs =  dialog.findViewById(R.id.sp_end_khab);
        final Spinner bls =  dialog.findViewById(R.id.sp_end_block);
        final Spinner rs =  dialog.findViewById(R.id.sp_end_room);


        ArrayAdapter<Khabgah> spinnerArrayAdapter = new ArrayAdapter<Khabgah>(activity,   android.R.layout.simple_spinner_item, khabgahs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        khs.setAdapter(spinnerArrayAdapter);

        if (khabgahs.size() > 0){
            khs.setSelection(index1%khabgahs.size());
        }



        final int finalIndex = index2;
        khs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    // TODO: 6/1/2018 Check some out of bound Things
                    bls.setEnabled(true);
                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(activity ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    bls.setAdapter(spinnerArrayAdapter);

                    if (first_time){
                        if (blocks.size() > 0){
                            bls.setSelection(finalIndex %blocks.size());
                        }
                    }

                }else {
                    bls.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bls.setEnabled(false);
                rs.setEnabled(false);
            }

        });


        final int finalIndex1 = index3;
        bls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    rs.setEnabled(true);

                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(activity ,   android.R.layout.simple_spinner_item, blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    rs.setAdapter(spinnerArrayAdapter);

                    if (first_time){
                        if (rooms.size() > 0){
                            rs.setSelection(finalIndex1 %rooms.size());
                        }
                        first_time = false;
                    }

                }else {
                    rs.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rs.setEnabled(false);
            }

        });

        dialog.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if (properties.kh_id.equals(((Khabgah)khs.getSelectedItem()).Id) &&
                            properties.blook_id.equals(((Block)bls.getSelectedItem()).Id) &&
                            properties.room_id.equals(((Room)rs.getSelectedItem()).Id)){
                        Toast.makeText(activity, "محل جدید غیر قابل قبول", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (JalaliCalendar.jalali_to_miladi(y ,m,d).before( Calendar.getInstance().getTime()) ){
                        Toast.makeText(activity, "تاریخ نا معتبر است", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Transfer.send_self_transfer(activity ,true , properties.kh_id , properties.blook_id, properties.room_id,
                            ((Khabgah)khs.getSelectedItem()).Id ,((Block)bls.getSelectedItem()).Id,
                            ((Room)rs.getSelectedItem()).Id , JalaliCalendar.jalali_to_miladi(y ,m,d) );
                }catch (Exception e){
                    Toast.makeText(activity, "خطا", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Log.d("Exception : " ,e.getMessage());
                }


                dialog.dismiss();

            }
        });


        dialog.show();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(activity, "لغو شد", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public static void Load_user (final AppCompatActivity activity , User user , Properties properties){
        // TODO: 8/20/2018 fix appearance of dialogue and set things
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_load_user); // WHAT WORTH CAN IT BEE !

        final TextInputEditText matn = (TextInputEditText) dialog.findViewById(R.id.te_gozaresh_matn);
        final Spinner types = (Spinner) dialog.findViewById(R.id.sp_gozaresh_header);

        ArrayAdapter<Report_type> spinnerArrayAdapter = new ArrayAdapter<Report_type>(activity,   android.R.layout.simple_spinner_item, ((ReportActivity) activity).getTypes());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        types.setAdapter(spinnerArrayAdapter);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_send_report);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String matn_str = matn.getText().toString();
//                    String heade_str = header.getText().toString();

                if (matn_str.trim().length() < 1 ){
                    Toast.makeText(activity, "متن کوتاه است", Toast.LENGTH_SHORT).show();
                    Init.Terminal("WHAT KIND OF MESSAGE IS THIS ????");
                    return;
                }

                Report_type report_type = (Report_type) types.getSelectedItem();
                if (report_type != null){
                    Report.send_self_report(activity ,true , matn_str , report_type.id);

                }else {
                    Toast.makeText(activity, "نوع گزارش خالی است", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
