package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Gozaresh {

    public Message.State get_State (){
        return Message.State.fromInteger(this.State_gozaresh);
    }

    public void set_State (Message.State state){
        this.State_gozaresh = state.ordinal();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSharh() {
        return this.sharh;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public Long getType_id() {
        return this.type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getState_gozaresh() {
        return this.State_gozaresh;
    }

    public void setState_gozaresh(int State_gozaresh) {
        this.State_gozaresh = State_gozaresh;
    }



    @Id(autoincrement = true)
    public Long id;
    public String sharh;
    public Long type_id;
    public Long user_id; // sender of this!
    public int State_gozaresh;
    @Generated(hash = 1645069386)
    public Gozaresh(Long id, String sharh, Long type_id, Long user_id,
            int State_gozaresh) {
        this.id = id;
        this.sharh = sharh;
        this.type_id = type_id;
        this.user_id = user_id;
        this.State_gozaresh = State_gozaresh;
    }

    @Generated(hash = 637639576)
    public Gozaresh() {
    }

}
