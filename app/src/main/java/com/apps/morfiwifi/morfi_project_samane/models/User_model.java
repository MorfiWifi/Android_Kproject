package com.apps.morfiwifi.morfi_project_samane.models;

import com.parse.ParseUser;

public class User_model {
    public ParseUser parseUser;
    public User_model (ParseUser user){
        if (user != null){
            parseUser = user;
        }else {
            parseUser = new ParseUser();
        }
    }
    // Some thing about role is needed
}
