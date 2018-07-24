package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Report_type {
    // Thise Are just ITEMS FOR CHOOSING !
    @Id(autoincrement = true)
    public Long id ;
    public String Name;
    public String pr_name;

    @Generated(hash = 1109987613)
    public Report_type(Long id, String Name, String pr_name) {
        this.id = id;
        this.Name = Name;
        this.pr_name = pr_name;
    }
    @Generated(hash = 215531032)
    public Report_type() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getPr_name() {
        return this.pr_name;
    }
    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

    @Override
    public String toString() {
        if (pr_name == null){
            pr_name = "";
        }
        if (pr_name.isEmpty()){
            return "بی نام!";
        }else {
            return pr_name;
        }
    }
}
