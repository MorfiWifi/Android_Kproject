package com.apps.morfiwifi.morfi_project_samane.models;

/**
 * Created by WifiMorfi on 4/13/2018.
 */

public class Value {
    public boolean res =false;
    public User user;
    public Value (User user1 , boolean result){
        res = result;
        user = user1;
    }
    public Value (){
        res = false;
        user = null;
    }
}