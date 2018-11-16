package com.apps.morfiwifi.morfi_project_samane.models;

import com.parse.ParseObject;

public class Role_model {
    public ParseObject parseObject;

    public Role_model (ParseObject parseObject){
        if (parseObject != null){
            this.parseObject = parseObject;
        }else {
            this.parseObject = new ParseObject("role");
        }
    }
}
