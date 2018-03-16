package com.apps.morfiwifi.morfi_project_samane.models;

/**
 * Created by WifiMorfi on 12/9/2017.
 */

public class LogInViewModel {
    String UserName;
    String Password;

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }
}
