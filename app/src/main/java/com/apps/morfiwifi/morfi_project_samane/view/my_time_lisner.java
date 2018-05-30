package com.apps.morfiwifi.morfi_project_samane.view;

import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

public class my_time_lisner implements DatePickerDialog.OnDateSetListener {
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        Init.Terminal(date);
    }
}
