package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;

/**
 * Tweet Model to send new tweet for request body and get in in response
 * NOTE: all of the attr should define as public and also the name should match in REST API
 */
public class CallModel {
    public int Id;
    public String Message;
    public java.util.Date Date;



    public String getTitle(){
        return this.Message;
    }

}
