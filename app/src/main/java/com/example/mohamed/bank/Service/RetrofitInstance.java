package com.example.mohamed.bank.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed on 10/15/2018.
 */

public class RetrofitInstance {


    private static Retrofit retrofit = null;
    private static String Base_url = "http://ipda3.com/blood-bank/api/v1/";
    public static GetDataService getDataService(){
        if (retrofit == null)
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit.create(GetDataService.class);
    }
}
