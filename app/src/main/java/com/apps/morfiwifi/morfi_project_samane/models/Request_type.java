package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Request_type {
    @Id
    public Long id;
    public String name;
    public String pr_name;
    @Generated(hash = 246432722)
    public Request_type(Long id, String name, String pr_name) {
        this.id = id;
        this.name = name;
        this.pr_name = pr_name;
    }
    @Generated(hash = 1608821920)
    public Request_type() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPr_name() {
        return this.pr_name;
    }
    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

}
