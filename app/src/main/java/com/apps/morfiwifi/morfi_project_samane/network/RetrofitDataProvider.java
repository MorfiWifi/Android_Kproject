package com.apps.morfiwifi.morfi_project_samane.network;


import com.apps.morfiwifi.morfi_project_samane.utility.ClientConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class make Retrofit API Service
 */
public class RetrofitDataProvider {

    private RetrofitDataService mTService;
    private Retrofit mRetrofitClient;
    //private AppPreferenceTools mAppPreferenceTools;

    /**
     * config Retrofit in initialization
     */
    public RetrofitDataProvider() {
        //this.mAppPreferenceTools = new AppPreferenceTools(TApplication.applicationContext);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //create new gson object to define custom converter on Date type
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new UTCDateTypeAdapter())
                .create();




        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientConfigs.REST_API_BASE_URL) // set Base URL , should end with '/'
                .client(httpClient.build()) // add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        mTService = mRetrofitClient.create(RetrofitDataService.class);

        //mRetrofitClient.converterFactories().add(mRetrofitClient.converterFactories().);

    }

    /**
     * can get Retrofit Service
     *
     * @return
     */
    public RetrofitDataService getTService() {
        return mTService;
    }

    /**
     * get Retrofit client
     * used in ErrorUtil class
     *
     * @return
     */
    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }
}
