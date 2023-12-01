package com.example.gorenganindonesia.API.Handlers;

import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.user.UserService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.GetUserResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioRequest;
import com.example.gorenganindonesia.Model.api.User.UserData;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHandler {
    APIHandlerDTO dto;
    UserService userService;

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public UserHandler(APIHandlerDTO dto) {
        this.dto = dto;
        this.userService = RetrofitClient.getInstance().create(UserService.class);
    }

    public void getUser(){
        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE){
            dto.loadingView.setVisibility(View.VISIBLE);
            dto.loadingText.setText("Memuat Informasi Pengguna...");
        }
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        userService
                .getUser(token)
                .enqueue(new Callback<GetUserResponse>() {
                    @Override
                    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE)
                            dto.loadingView.setVisibility(View.GONE);
                        if(response.isSuccessful()){
                            UserData userDataResponse = response.body().getUserData();

                            Account account = new Account(
                                    userDataResponse.getId(),
                                    userDataResponse.getName(),
                                    userDataResponse.getUsername(),
                                    userDataResponse.getImageUrl(),
                                    userDataResponse.getImagePath(),
                                    userDataResponse.getEmail()
                            );

                            ((GlobalModel) dto.context.getApplicationContext()).getAccountViewModel().setAccount(account);
                            ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().saveAccountInfo(account);

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Mendaptkan Informasi Pengguna: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Mendaptkan Informasi Pengguna", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserResponse> call, Throwable t) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE)
                            dto.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Mendapatkan Informasi Pengguna: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void putUserBio(PutUserBioRequest putUserBioRequest){
        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE) {
            dto.loadingView.setVisibility(View.VISIBLE);
            dto.loadingText.setText("Memperbarui Informasi\nProfil Anda...");
        }
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();
        userService
                .putUserBio(token, putUserBioRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE)
                            dto.loadingView.setVisibility(View.GONE);

                        if(response.isSuccessful()){
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Memperbarui Profil: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Memperbarui Profil", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE)
                            dto.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Memperbarui Profil: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
