package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Samane {

    @Transient
    public static String khab = "06"; //yet just Samane KHAB!


    @Id(autoincrement = true)
    public Long id;
    public String Code;
    public String Name;
    public String prop; // Yet NO USE
    @Generated(hash = 517378157)
    public Samane(Long id, String Code, String Name, String prop) {
        this.id = id;
        this.Code = Code;
        this.Name = Name;
        this.prop = prop;
    }
    @Generated(hash = 2131972725)
    public Samane() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return this.Code;
    }
    public void setCode(String Code) {
        this.Code = Code;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getProp() {
        return this.prop;
    }
    public void setProp(String prop) {
        this.prop = prop;
    }

}
