package com.apps.morfiwifi.morfi_project_samane

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.botttom_sheet.*


class Bottom_sheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)




    }


    fun showsheet (view:View){
        //bottomsheet.showWithSheetView(LayoutInflater.from(applicationContext).inflate(R.layout.activity_login, bottomsheet, false));
        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }


}
