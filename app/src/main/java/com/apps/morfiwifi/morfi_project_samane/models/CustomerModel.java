package com.apps.morfiwifi.morfi_project_samane.models;

import java.util.List;

/**
 * Created by m.hosein on 11/10/2017.
 */

public class CustomerModel {
    public int Id;
    public String Name;
    public String CompanyName;
    public int PhoneNumber;
    public int PriceOfAllProductsPurchased;
    public int AllProductsPurchased;
    public List<OrderModel> Orders;
    public List<ProductModel> Products;
    public List<Order_DetailsModel> Order_Details;


    public String getTitle(){
        return this.Name;
    }

}
