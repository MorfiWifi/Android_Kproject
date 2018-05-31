package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;

import java.util.List;

public class JabejaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jabejai);
        //Load_View( samanes);




    }

    private void Load_View(List<Samane> samanes) {
        // Array of choices
        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.sp_start_block);

        // Application of the Array to the Spinner
        ArrayAdapter<Samane> spinnerArrayAdapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, samanes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public void jabeja_pick_date(View view) {
    }

    public void taeed_jabeja(View view) {

    }
}
