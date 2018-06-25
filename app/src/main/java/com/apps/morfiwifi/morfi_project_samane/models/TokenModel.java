/*
package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.content.Intent;

import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider;
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService;
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

*/
/**
 * Token Model used in sign in, sign up and refresh token response
 *//*

public class TokenModel {

    private static RetrofitDataService mTService;
    public String access_token;
  //  public Long expire_in_sec;
    //public Date expire_at;
   // public String refresh_token;
    public String app_id;
    public final static String bad = "BADTOKEN";
    public static String shit = "";

    public static  String TokenSTR = "";

    public  static String GetText(){

        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();
        Call<String> call = mTService.Tokem();
        call.enqueue((new Callback <String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){

                    shit = response.body();
                    TokenSTR = "akjsfhakjsfajskfhaiksfahsfk";
                    System.out.println("Test Good : "+ response.body());
                }else {
                    System.out.println("Minor Error in Test!!");
                    shit = "BAD";
                    TokenSTR = bad;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("Major Error in Test!!");
                shit = "WORTH";
                TokenSTR = bad;
            }
        }));

        WaitForToken();
        return shit;
    }
    public static void getToken (){

        if (isTokenGood()){
            return;
        }

        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName("admin");
        loginModel.setPassword("bbBB11!!");

        Call<String> call = mTService.GetToken(loginModel);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){
                    TokenSTR = response.body();
                    System.out.println("Token Good : "+ response.body());
                }else {

                    TokenSTR = bad;
                    System.out.println("Minor Error in Token!!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                TokenSTR = bad;
                System.out.println("Serious Error in Togen!");
            }
        });
        WaitForToken();
    }
    public static String getToken (String username , String pass){

        if (isTokenGood()){
            return "";
        }

        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(username);
        loginModel.setPassword(pass);

        Call<String> call = mTService.GetToken(loginModel);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){

                    TokenSTR = response.body();
                    System.out.println("Token Good : "+ response.body());
                }else {

                    TokenSTR = bad;
                    System.out.println("Minor Error in Token!!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                TokenSTR = bad;
                System.out.println("Serious Error in Togen!");
            }
        });
        WaitForToken();
        return TokenSTR;
    }
    public static String getToken (String username , String pass , final Context context){
        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(username);
        loginModel.setPassword(pass);

        Call<String> call = mTService.GetToken(loginModel);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){

                    TokenSTR = response.body();
                    System.out.println("Token Good : "+ response.body());
                    Intent intent = new Intent(context , MessageActivity.class);
                    context.startActivity(intent);
                }else {

                    TokenSTR = bad;
                    System.out.println("Minor Error in Token!!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                TokenSTR = bad;
                System.out.println("Serious Error in Togen!");
            }
        });
        return TokenSTR;
    }
    public static boolean isTokenGood (){
        if (TokenSTR.equals(bad)){
            return false;
        }
        if (TokenSTR.equals("")){
            return false;
        }
        return true;

    }
    public static boolean isTokenEmpty () {
        if (TokenSTR.equals("")){
            return true;
        }
        return false;
    }
    public static boolean isTokenBad (){
        if (TokenSTR.equals(bad)){
            return true;
        }
        if (TokenSTR.equals("")){
            return true;
        }
        return false;
    }
    public static void  WaitForToken (){
        while (TokenModel.isTokenEmpty()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
*/
