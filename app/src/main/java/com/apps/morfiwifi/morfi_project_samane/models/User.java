package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider;
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService;
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.orm.SugarRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WifiMorfi on 3/20/2018.
 */


public class User  extends SugarRecord {
    public  User(){
        Id = "";
        FName = "";
        LName = "";
        Pass = "";
        Pass_hash = "";
        Kaet_meli = "";
        Type = kind.Student;
    }
    private static RetrofitDataService mTService;
    public String Id;
    public String FName;
    public String LName;
    public String Pass;
    public String Pass_hash;
    public String Kaet_meli;
    public kind Type;


    public enum kind {
        Student, Master, Technical, Site_Master, Self_Service, Admin;


        @Override
        public String toString() {
            int dis = this.compareTo(kind.Student);
            switch (dis){
                case 0 :
                    return "دانشجو";
                case 1 :
                    return "استاد";
                case 2 :
                    return "فنی";
                case  3 :
                    return "مسئول سایت";
                case 4 :
                    return "سلف";
                case 5:
                    return "ادمین کل";
                default:
                    return "نامشخص";
            }

        }
    }

    public static User GetUser (final Context context , final AppCompatActivity activity){

        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<User> call = mTService.GetUser(TokenModel.TokenSTR);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN User !");
                    Init.Println("Why Null IN User !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        ((MessageActivity) activity).update_user(response.body());
                    }
                }
                else {
                    Init.Println("mid error in user Get");
                    Init.Toas(context , "mid error in user Get");
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Init.Println("User Really Failed!");
                Init.Toas(context , "User Really Failed!");
            }
        });

        return new User();
    }
}
