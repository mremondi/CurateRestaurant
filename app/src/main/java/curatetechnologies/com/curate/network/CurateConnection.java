package curatetechnologies.com.curate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import curatetechnologies.com.curate.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mremondi on 10/2/17.
 */

public class CurateConnection {

    public static CurateAPI setUpRetrofit(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(CurateAPI.class);
    }
}