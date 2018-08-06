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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Report;
import com.apps.morfiwifi.morfi_project_samane.models.Report_type;
import com.apps.morfiwifi.morfi_project_samane.models.Request;
import com.apps.morfiwifi.morfi_project_samane.models.Thing;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.ui.student.DarkhastActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.student.ReportActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.List;

public class Dialogue {
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

                        /*Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//***Change Here***
                        activity.startActivity(intent);*/
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
    public static void Send_Broudcast (final AppCompatActivity activity){

        // custom dialog
        final Dialog dialog = new Dialog(activity);
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

}
