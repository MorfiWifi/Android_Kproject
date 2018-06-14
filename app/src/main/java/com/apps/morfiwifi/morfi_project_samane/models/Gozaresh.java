package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;

@Entity
public class Gozaresh {
    public enum  State{
        open , working_on  , finished  ;

        public static Gozaresh.State fromInteger(int x) {
            int count = values().length;
            return values()[x % count];
        }

        @Override
        public String toString() {
            switch (this){
                case open:
                    return "باز";
                case finished:
                    return "تمام شده";
                case working_on:
                    return "درحال پیگیری";
                default:
                    return "مشخص نیست";
            }

            //return super.toString();

        }
    }


    public void set_State (Gozaresh.State state){
        this.State_gozaresh = state.ordinal();
    }

    public Gozaresh.State get_State (){
        return Gozaresh.State.fromInteger(this.State_gozaresh);
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getSeen_date() {
        return this.seen_date;
    }

    public void setSeen_date(Date seen_date) {
        this.seen_date = seen_date;
    }



    @Id(autoincrement = true)
    public Long id;
    public String sharh;
    public Long type_id;
    public Long user_id; // sender of this!
    public int State_gozaresh;
    public Date date; // date of set !
    public Date seen_date; // it's shown

    @Transient
    public Gozaresh_type gozaresh_type;

    @Generated(hash = 1759818905)
    public Gozaresh(Long id, String sharh, Long type_id, Long user_id,
            int State_gozaresh, Date date, Date seen_date) {
        this.id = id;
        this.sharh = sharh;
        this.type_id = type_id;
        this.user_id = user_id;
        this.State_gozaresh = State_gozaresh;
        this.date = date;
        this.seen_date = seen_date;
    }

    @Generated(hash = 637639576)
    public Gozaresh() {
    }

}
