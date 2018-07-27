package com.apps.morfiwifi.morfi_project_samane.models;

// THIS IS A CLASS FOR HANDLING ASYNC THING NON RELETIVE TO PROJECT MODELS
public class Result {
    public Exception exception;
    int code = 0;
    private Object message;
    private boolean res = false;

    public boolean isok(){
        return res;
    }

    public Object getMessage (){
        return message;
    }

    public int getCode(){
        return code;
    }

    Result (Object message , int code , boolean res){
        exception = null ;
        this.code = code;
        this.res = res;
        this.message = message;
    }

    Result (Exception e , int code){
        this.code = code;
        this.exception = e;
    }


}
