package com.apps.morfiwifi.morfi_project_samane.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;

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

}
