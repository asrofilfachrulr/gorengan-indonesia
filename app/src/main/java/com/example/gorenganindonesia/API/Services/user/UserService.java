package com.example.gorenganindonesia.API.Services.user;

import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.GetUserResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioImgResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioRequest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserService {
    @GET("/user")
    Call<GetUserResponse> getUser(@Header("Authorization") String token);

    @PUT("/user/bio")
    Call<BasicResponse> putUserBio(
            @Header("Authorization") String token,
            @Body PutUserBioRequest body
    );

    @Multipart
    @PUT("/user/bio/img")
    Call<PutUserBioImgResponse> putUserBioImg(
            @Header("Authorization") String token,
            @Query("prev_path") String prevPath,
            @Part MultipartBody.Part file
    );
}
