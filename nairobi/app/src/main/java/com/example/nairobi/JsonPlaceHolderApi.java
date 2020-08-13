package com.example.nairobi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    String URL_BASE = "http://10.0.2.2:8080/fin/webapi/";

    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<Login> getUser(@Body String body);

    @POST("data")
    Call<data> getData(@Body String body);

    @POST("loc")
    Call<loc> getLoc(@Body String body);

    @POST("allmeter")
    Call<allmeter> getallmeterdata();

    @POST("Visualize")
    Call<Visualize> getVis();
}
