package com.example.gorenganindonesia.API.Services.user;

import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.GetUserResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface UserService {
    @GET("/user")
    Call<GetUserResponse> getUser(@Header("Authorization") String token);

    @PUT("/user/bio")
    Call<BasicResponse> putUserBio(
            @Header("Authorization") String token,
            @Body PutUserBioRequest body
    );
}
