package com.example.naveed.darzii;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Naveed on 11/15/2017.
 */

public interface WebServices {


    @GET("/api/user")
    Call<List<User>> userRetrieve();

//
    @POST("/api/login")
    Call<User> userLogin(@Body User user);

    @POST("/api/darzi")
    Call<User> userSignUp(@Body User user);

//    @POST("/api/login")
//    Call<User> userLogin(@Body User user);

    @GET("api/information")
    Call<List<User>> getUser();

    @POST("/api/information")
    Call<Information> addInfo(@Body Information info);

    @GET("/api/information")
    Call<List<Information>> getInfo();

    @DELETE("/api/information/{id}")
    Call<ResponseBody> deleteInfo(@Path("id") int id);


}
