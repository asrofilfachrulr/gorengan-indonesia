package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.user.UserService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.GetUserResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioImgResponse;
import com.example.gorenganindonesia.Model.api.User.PutUserBioRequest;
import com.example.gorenganindonesia.Model.api.User.UserData;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import okhttp3.MultipartBody;
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
        ProgressDialog progressDialog;
        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE){
            progressDialog = dto.createProgressDialog();
            progressDialog.setMessage("Memuat Informasi Pengguna");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else
            progressDialog = null;

        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        userService
                .getUser(token)
                .enqueue(new Callback<GetUserResponse>() {
                    @Override
                    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE && progressDialog != null)
                            progressDialog.dismiss();
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
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE && progressDialog != null)
                            progressDialog.dismiss();
                        new CustomToast("Gagal Mendapatkan Informasi Pengguna: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void putUserBio(PutUserBioRequest putUserBioRequest){
        ProgressDialog progressDialog;
        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE) {
            progressDialog = dto.createProgressDialog();
            progressDialog.setMessage("Memperbarui Informasi Profil");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else
            progressDialog = null;

        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();
        userService
                .putUserBio(token, putUserBioRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(dto.getDaemonMode() == APIHandlerDTO.SCREAMING_MODE)
                            progressDialog.dismiss();

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
                            progressDialog.dismiss();
                        new CustomToast("Gagal Memperbarui Profil: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void putUserBioImg(MultipartBody.Part imageData, String prevPath){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memperbarui Gambar Profil");
        progressDialog.setCancelable(false);
        progressDialog.show();

        userService
                .putUserBioImg(token, prevPath, imageData)
                .enqueue(new Callback<PutUserBioImgResponse>() {
                    @Override
                    public void onResponse(Call<PutUserBioImgResponse> call, Response<PutUserBioImgResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            PutUserBioImgResponse respBody = response.body();

                            Account account = ((GlobalModel) dto.context.getApplicationContext()).getAccountViewModel().getAccount();
                            account.setImageUrl(respBody.getImageUrl());
                            account.setImagePath(respBody.getImagePath());

                            ((GlobalModel) dto.context.getApplicationContext()).getAccountViewModel().setAccount(account);

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Memperbarui Gambar Profil: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Memperbarui Gambar Profil", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PutUserBioImgResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Memperbarui Gambar Profil: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
