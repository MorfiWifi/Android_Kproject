package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.my_time_lisner;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

public class EnserafActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,
        View.OnClickListener{

    private static String[] monthrs = {"فروردین" , "اردیبهشت" , "خرداد" , "تیر" ,"مرداد" ,"شهریور" ,"مهر" ,"آبان" , "آذر" , "دی" , "بهمن" , "اسفند"};
    private boolean is_date_selected = false;
    private TextView tv_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseraf);
        tv_hint = findViewById(R.id.tv_enseraf_date);
    }

    public void pick_date(View view) {
        PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                EnserafActivity.this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        Init.Terminal(date);
        String s =  dayOfMonth+ " "+ monthrs[monthOfYear] +" "+ year ;
        tv_hint.setText(s);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/BNAZANIN.TTF");
        tv_hint.setTypeface(type);
        is_date_selected = true;
        //tv_hint.setTypeface();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }

    public void enseraf_taeed(View view) {
        if (!is_date_selected){
            Init.Toas(this , "یک روز انتخواب کنید");
        }
        else{
            // TODO: 5/30/2018 Add TO DB & Go BACK

            onBackPressed();
        }



    }
}
