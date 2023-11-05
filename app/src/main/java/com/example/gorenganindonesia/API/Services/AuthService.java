package com.example.gorenganindonesia.API.Services;

import com.example.gorenganindonesia.Model.api.Login.LoginRequest;
import com.example.gorenganindonesia.Model.api.Login.LoginResponse;
import com.example.gorenganindonesia.Model.api.Register.RegisterRequest;
import com.example.gorenganindonesia.Model.api.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface AuthService {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
