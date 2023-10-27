package com.example.gorenganindonesia.API;

import com.example.gorenganindonesia.Model.api.LoginRequest;
import com.example.gorenganindonesia.Model.api.LoginResponse;
import com.example.gorenganindonesia.Model.api.RegisterRequest;
import com.example.gorenganindonesia.Model.api.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface AuthService {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
