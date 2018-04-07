package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataProvider;
import com.apps.morfiwifi.morfi_project_samane.network.RetrofitDataService;
import com.apps.morfiwifi.morfi_project_samane.ui.MessageActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.ReciverActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.message_RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WifiMorfi on 3/20/2018.
 */

public class Message {
    public  static ArrayList<Message> arrayList = new ArrayList<>();

    private static RetrofitDataService mTService;
    public String Id;
    public String Send_Date;
    public String Recive_Date;
    public String Tags;
    public String Matn;
    public String Sender_ID;
    public String Reciver_ID;
    public String Readed;
    public User.kind Reciver_Type;

    public Message(){
        Id = "";
        Send_Date = "";
        Recive_Date = "";
        Tags = "";
        Matn = "";
        Sender_ID = "";
        Reciver_ID = "";
        Readed = "";
        Reciver_Type = User.kind.Student;
    }

    public static String GetMessages (final Context context , final AppCompatActivity activity){
        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<List<Message>> call = mTService.GetMessages(TokenModel.TokenSTR);

        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN Message !");
                    Init.Println("Why Null IN Message !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        ((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                    if (activity instanceof ReciverActivity){
                        message_RecyclerAdapter.Init(response.body() , activity);
                        //((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                }
                else {
                    Init.Println("mid error in Message Get");
                    Init.Toas(context , "mid error in Message Get");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Init.Println("Real error in Message Get");
                Init.Toas(context , "Real error in Message Get");
            }
        });


        return "no its Async";
    }

    public static String InsertMessages (final Context context , final AppCompatActivity activity , Message message){
        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<Message> call = mTService.InsertMessages(TokenModel.TokenSTR ,message);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN Message Put !");
                    Init.Println("Why Null IN Message Put !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        Init.Toas(context , "Message Sent (Sucsess)");
                        //((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                }
                else {
                    Init.Println("mid error in Message put");
                    Init.Toas(context , "mid error in Message put");
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Init.Println("Real error in Message put");
                Init.Toas(context , "Real error in Message put");
            }


        });


        return "no its Async";
    }


    private static String STR (List<Message> messages){
        String res = "";
        for (int i = 0; i < messages.size(); i++) {
            res = res + messages.get(i).Matn;
        }
        return res;
    }


}
