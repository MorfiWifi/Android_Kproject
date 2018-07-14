package com.apps.morfiwifi.morfi_project_samane.ui.technical;

import android.app.Dialog;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Broudcast;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

public class PostArchiveActivity extends TechnicalActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_archive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("صندوق پستی");

        final PostArchiveActivity activity = this;

        Button button =  findViewById(R.id.btn_send_broudcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialogue_new_broudcast);

                // set the custom dialog components - text, image and button


                final TextInputEditText header = (TextInputEditText) dialog.findViewById(R.id.ti1);
                final TextInputEditText matn = (TextInputEditText) dialog.findViewById(R.id.ti2);
                final Spinner roles = (Spinner) dialog.findViewById(R.id.spinner_roles);

                // FIXME: 7/14/2018 SHOULD BE PRe LOADED BUT BE AWARE!!
                ArrayAdapter<role> spinnerArrayAdapter = new ArrayAdapter<role>(getApplicationContext(),   android.R.layout.simple_spinner_item, role.load_roles(getApplicationContext()));
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
        });
    }
}
