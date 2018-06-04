package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Enteghali {

    @Id
    Long id;
    Long block_id;
    Long khabghah_id;
    Long othagh_id;
    int year; // Date of transfer Req
    int month;
    int day;
    Date date; // date of request ! (not sure)
    @Generated(hash = 1023609981)
    public Enteghali(Long id, Long block_id, Long khabghah_id, Long othagh_id,
            int year, int month, int day, Date date) {
        this.id = id;
        this.block_id = block_id;
        this.khabghah_id = khabghah_id;
        this.othagh_id = othagh_id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = date;
    }
    @Generated(hash = 1638628221)
    public Enteghali() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getBlock_id() {
        return this.block_id;
    }
    public void setBlock_id(Long block_id) {
        this.block_id = block_id;
    }
    public Long getKhabghah_id() {
        return this.khabghah_id;
    }
    public void setKhabghah_id(Long khabghah_id) {
        this.khabghah_id = khabghah_id;
    }
    public Long getOthagh_id() {
        return this.othagh_id;
    }
    public void setOthagh_id(Long othagh_id) {
        this.othagh_id = othagh_id;
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return this.month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return this.day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
