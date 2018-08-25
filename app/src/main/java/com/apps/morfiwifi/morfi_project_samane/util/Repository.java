package com.apps.morfiwifi.morfi_project_samane.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.apps.morfiwifi.morfi_project_samane.models.DaoMaster;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;

public class Repository {

    private static DaoMaster.DevOpenHelper helper;
    private static DaoSession daoSession;

    public static DaoSession GetInstant (Context context){
        if (daoSession == null){
            helper = new DaoMaster.DevOpenHelper(context,"azsoftwaredb" , null);
            // TODO: 6/8/2018 can use On upgrade here .....
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        return daoSession;
    }

    public static DaoSession GetNewInstant (Context context){
        //Force building new One
        helper = new DaoMaster.DevOpenHelper(context,"azsoftwaredb" , null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        return daoSession;
    }



}