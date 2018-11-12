package com.apps.morfiwifi.morfi_project_samane.models;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.ParseObject;
import org.jetbrains.annotations.NotNull;

public class Ticket {
    public ParseObject parseObject ;
    public boolean isLoading = false;
    public boolean isSaved = false;
    public String id ;


    public Ticket(boolean isLoading ,@NotNull ParseObject parseObject){
        this.parseObject = parseObject;
        this.isLoading = isLoading;
        if (parseObject.getObjectId() != null){
            id = parseObject.getObjectId();
            isSaved = true;
        }else {
            isSaved = false;
            id = Init.Empty;
        }
    }
}
