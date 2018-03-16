package com.apps.morfiwifi.morfi_project_samane.models;

import java.util.List;

/**
 * Created by m.hosein on 11/10/2017.
 */

public class ContractModel {

    public int Id;
    public String Content;
    public List<OrderModel> Orders;

    public String getTitle(){
        return this.Content;
    }
}
