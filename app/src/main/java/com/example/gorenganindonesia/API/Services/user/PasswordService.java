package com.example.gorenganindonesia.API.Services.user;

import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.PutPasswordRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface PasswordService {
    @PUT("/user/password")
    Call<BasicResponse> putPassword(
            @Header("Authorization") String token,
            @Body PutPasswordRequest requestBody
    );
}
