package curatetechnologies.com.curate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mremondi on 10/2/17.
 */

public class CurateConnection {

    public static CurateAPI setUpRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://guarded-caverns-87227.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(CurateAPI.class);
    }
}