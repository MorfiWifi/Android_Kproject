package com.apps.morfiwifi.morfi_project_samane.ui.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.Samane;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_samane;

import java.util.ArrayList;

public class SamanehaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samaneha);

//        List<Samane> samanes = new ArrayList<>();
//        DaoSession session = Repository.GetInstant(this);
//        samanes = session.getSamaneDao().loadAll();




        Properties.load_self_properties(this , true , false);
        Khabgah.load_Khabgahs(null , false , true);
        Room.load_rooms(null , false ,true);
        Block.load_blocks(null , false , true);

        // .Init(Init.get_messages_dao(this), this)
    }


    public void set_property(Properties properties){
        Samane samane = new Samane();
        ArrayList<Samane> list = new ArrayList<>();
        if (!properties.id.equals(Init.Empty)){
            if (properties.use_khabgah){
                samane.Code = Samane.khab;
                samane.Name = "خوابگاه";
                samane.prop = "NON";
                list.add(samane);
            }

        }
        RecyclerAdapter_samane.Init(list , this);
    }
}
