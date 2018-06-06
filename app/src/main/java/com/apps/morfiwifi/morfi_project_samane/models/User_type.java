package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User_type {
    @Id(autoincrement = true)
    Long id;
    String name;
    String pr_name;

    boolean can_see_all;

    boolean can_see_request;
    boolean can_see_gozaresh;
    boolean can_see_enteghali;
    boolean can_see_message;

    boolean can_send_message;
    boolean can_send_gozaresh;
    boolean can_send_request;
    boolean can_send_enteghad;
    @Generated(hash = 2112384375)
    public User_type(Long id, String name, String pr_name, boolean can_see_all,
            boolean can_see_request, boolean can_see_gozaresh,
            boolean can_see_enteghali, boolean can_see_message,
            boolean can_send_message, boolean can_send_gozaresh,
            boolean can_send_request, boolean can_send_enteghad) {
        this.id = id;
        this.name = name;
        this.pr_name = pr_name;
        this.can_see_all = can_see_all;
        this.can_see_request = can_see_request;
        this.can_see_gozaresh = can_see_gozaresh;
        this.can_see_enteghali = can_see_enteghali;
        this.can_see_message = can_see_message;
        this.can_send_message = can_send_message;
        this.can_send_gozaresh = can_send_gozaresh;
        this.can_send_request = can_send_request;
        this.can_send_enteghad = can_send_enteghad;
    }
    @Generated(hash = 578459302)
    public User_type() {
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
    public boolean getCan_see_all() {
        return this.can_see_all;
    }
    public void setCan_see_all(boolean can_see_all) {
        this.can_see_all = can_see_all;
    }
    public boolean getCan_see_request() {
        return this.can_see_request;
    }
    public void setCan_see_request(boolean can_see_request) {
        this.can_see_request = can_see_request;
    }
    public boolean getCan_see_gozaresh() {
        return this.can_see_gozaresh;
    }
    public void setCan_see_gozaresh(boolean can_see_gozaresh) {
        this.can_see_gozaresh = can_see_gozaresh;
    }
    public boolean getCan_see_enteghali() {
        return this.can_see_enteghali;
    }
    public void setCan_see_enteghali(boolean can_see_enteghali) {
        this.can_see_enteghali = can_see_enteghali;
    }
    public boolean getCan_see_message() {
        return this.can_see_message;
    }
    public void setCan_see_message(boolean can_see_message) {
        this.can_see_message = can_see_message;
    }
    public boolean getCan_send_message() {
        return this.can_send_message;
    }
    public void setCan_send_message(boolean can_send_message) {
        this.can_send_message = can_send_message;
    }
    public boolean getCan_send_gozaresh() {
        return this.can_send_gozaresh;
    }
    public void setCan_send_gozaresh(boolean can_send_gozaresh) {
        this.can_send_gozaresh = can_send_gozaresh;
    }
    public boolean getCan_send_request() {
        return this.can_send_request;
    }
    public void setCan_send_request(boolean can_send_request) {
        this.can_send_request = can_send_request;
    }
    public boolean getCan_send_enteghad() {
        return this.can_send_enteghad;
    }
    public void setCan_send_enteghad(boolean can_send_enteghad) {
        this.can_send_enteghad = can_send_enteghad;
    }




}
