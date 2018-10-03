package com.apps.morfiwifi.morfi_project_samane.advance;

import com.apps.morfiwifi.morfi_project_samane.models.Cancellation;

import java.util.ArrayList;
import java.util.List;

public class test_model {


    public void test1  (){
        List<Cancellation> cancellations =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cancellations.add(new Cancellation());
        }

        Holder<Cancellation> holder = new Holder<>(cancellations);


    }
}
