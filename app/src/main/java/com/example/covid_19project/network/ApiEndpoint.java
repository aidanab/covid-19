package com.example.covid_19project.network;

import com.example.covid_19project.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("indonesia")
    Call<List<MainModel>> getData();
}
