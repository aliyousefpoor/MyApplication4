package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    String JSONURL ="https://reqres.in/api/users?page=2/";
    @GET("users?page=2/")Call<JsonClass> getString();

}
