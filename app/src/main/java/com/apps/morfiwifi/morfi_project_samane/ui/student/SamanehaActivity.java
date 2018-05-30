package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.view.samane_RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SamanehaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samaneha);

        List<Samane> samanes = new ArrayList<>();
        DaoSession session = Repository.GetInstant(this);
        samanes = session.getSamaneDao().loadAll();

        samane_RecyclerAdapter.Init(samanes , this);
        // .Init(Init.get_messages_dao(this), this)
    }
}
