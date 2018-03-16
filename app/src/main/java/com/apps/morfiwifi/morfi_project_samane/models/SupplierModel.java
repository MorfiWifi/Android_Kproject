package com.apps.morfiwifi.morfi_project_samane.models;

import java.util.List;

/**
 * Created by m.hosein on 11/10/2017.
 */

public class SupplierModel {

    public int Id;
    public String Name;
    public String CompanyName;
    public int PhoneNumber;
    public String Address;
    public boolean IsDeleted;
    public List<ProductModel> Products;
    public int UnitsInStock;
    public int Price;

    public String getTitle(){
        return this.CompanyName;
    }
}
