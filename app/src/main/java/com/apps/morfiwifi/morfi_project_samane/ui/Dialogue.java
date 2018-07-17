package com.apps.morfiwifi.morfi_project_samane.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

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

}
