package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider;
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService;
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.orm.SugarRecord;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WifiMorfi on 3/20/2018.
 */


@Entity
public class User   {
    public enum kind {
        Student(), Master(), Technical(), Site_Master(), Self_Service(), Admin()
    }


    private static RetrofitDataService mTService;
    @org.greenrobot.greendao.annotation.Id
    public Long Id;
    public String FName;
    public String LName;
    public String Pass;
    public String Pass_hash;
    public String Kaet_meli;
    public int Type;





    @Generated(hash = 1830581726)
    public User(Long Id, String FName, String LName, String Pass, String Pass_hash,
            String Kaet_meli, int Type) {
        this.Id = Id;
        this.FName = FName;
        this.LName = LName;
        this.Pass = Pass;
        this.Pass_hash = Pass_hash;
        this.Kaet_meli = Kaet_meli;
        this.Type = Type;
    }





    @Generated(hash = 586692638)
    public User() {
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





    public Long getId() {
        return this.Id;
    }





    public void setId(Long Id) {
        this.Id = Id;
    }





    public String getFName() {
        return this.FName;
    }





    public void setFName(String FName) {
        this.FName = FName;
    }





    public String getLName() {
        return this.LName;
    }





    public void setLName(String LName) {
        this.LName = LName;
    }





    public String getPass() {
        return this.Pass;
    }





    public void setPass(String Pass) {
        this.Pass = Pass;
    }





    public String getPass_hash() {
        return this.Pass_hash;
    }





    public void setPass_hash(String Pass_hash) {
        this.Pass_hash = Pass_hash;
    }





    public String getKaet_meli() {
        return this.Kaet_meli;
    }





    public void setKaet_meli(String Kaet_meli) {
        this.Kaet_meli = Kaet_meli;
    }





    public int getType() {
        return this.Type;
    }





    public void setType(int Type) {
        this.Type = Type;
    }

    public kind getKind (){
        return kind.values()[getType()% kind.values().length];
    }

    public void setkind(kind kinde){
        kind[] vals = kind.values();
        setType(0);
        for (int i = 0; i <vals.length ; i++) {
            if (vals[i].equals(kinde)){
                setType(i);
                break;
            }
        }
    }
}
