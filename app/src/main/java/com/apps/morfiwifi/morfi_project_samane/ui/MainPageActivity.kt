package com.apps.morfiwifi.morfi_project_samane.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apps.morfiwifi.morfi_project_samane.R
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        //Todo set apro priate Vhild for Displaying (add multplr First);

        vf_viewfliper.displayedChild = 1;
        //inc_layout_main.set
    }
}
