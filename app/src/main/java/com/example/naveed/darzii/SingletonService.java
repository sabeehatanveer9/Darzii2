package com.example.naveed.darzii;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Naveed on 12/19/2017.
 */

public class SingletonService {

    private static WebServices services = null;

    public static WebServices getService() {
        if (services == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.20.126.22:8000")
//                    .baseUrl("http://172.20.126.22/Darzii/public/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            services = retrofit.create(WebServices.class);

        }
        return services;
    }
}
