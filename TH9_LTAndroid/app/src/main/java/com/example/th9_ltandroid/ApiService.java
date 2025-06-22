package com.example.th9_ltandroid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/?results=20")
    Call<UserResponse> getUsers();
}
