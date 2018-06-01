package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Othagh {
    @Id
    public Long id;
    public String code;
    public String naem = "";

    @Generated(hash = 487051572)
    public Othagh(Long id, String code, String naem) {
        this.id = id;
        this.code = code;
        this.naem = naem;
    }

    @Generated(hash = 605531113)
    public Othagh() {
    }

    @Override
    public String toString() {
        return  naem;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNaem() {
        return this.naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }
}
