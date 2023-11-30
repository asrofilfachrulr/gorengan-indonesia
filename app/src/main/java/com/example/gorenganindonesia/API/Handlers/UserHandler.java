package com.example.gorenganindonesia.API.Handlers;

import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.user.UserService;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
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
    APIHandlerDAO dao;
    UserService userService;

    public APIHandlerDAO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public UserHandler(APIHandlerDAO dao) {
        this.dao = dao;
        this.userService = RetrofitClient.getInstance().create(UserService.class);
    }

    public void getUser(){
        dao.loadingView.setVisibility(View.VISIBLE);
        dao.loadingText.setText("Memuat Informasi Pengguna...");
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        userService
                .getUser(token)
                .enqueue(new Callback<GetUserResponse>() {
                    @Override
                    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                        dao.loadingView.setVisibility(View.GONE);
                        if(response.isSuccessful()){
                            UserData userDataResponse = response.body().getUserData();

                            Account account = new Account(
                                    userDataResponse.getId(),
                                    userDataResponse.getName(),
                                    userDataResponse.getUsername(),
                                    userDataResponse.getImageUrl(),
                                    userDataResponse.getEmail()
                            );

                            ((GlobalModel) dao.context.getApplicationContext()).getAccountViewModel().setAccount(account);

                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Mendaptkan Informasi Pengguna: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Mendaptkan Informasi Pengguna", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Mendapatkan Informasi Pengguna: Koneksi Gagal", dao.view, false).show();
                    }
                });
    }

    public void putUserBio(PutUserBioRequest putUserBioRequest){
        dao.loadingView.setVisibility(View.VISIBLE);
        dao.loadingText.setText("Memperbarui Informasi\nProfil Anda...");

        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();
        userService
                .putUserBio(token, putUserBioRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        dao.loadingView.setVisibility(View.GONE);

                        if(response.isSuccessful()){
                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Memperbarui Profil: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Memperbarui Profil", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Memperbarui Profil: Koneksi Gagal", dao.view, false).show();
                    }
                });
    }
}
