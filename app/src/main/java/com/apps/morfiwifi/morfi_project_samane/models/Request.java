package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Request {

    public Message.State get_State (){
        return Message.State.fromInteger(this.State_request);
    }

    public void set_State (Message.State state){
        this.State_request = state.ordinal();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getState_request() {
        return this.State_request;
    }

    public void setState_request(int State_request) {
        this.State_request = State_request;
    }

    public Request(){
        State_request = 0; // pre_defining
    }

    @Generated(hash = 1727964616)
    public Request(Long id, int count, Long user_id, int State_request) {
        this.id = id;
        this.count = count;
        this.user_id = user_id;
        this.State_request = State_request;
    }




    @Id
    public Long id;
    public int count;
    public Long user_id;
    @NotNull
    public int State_request;

}
