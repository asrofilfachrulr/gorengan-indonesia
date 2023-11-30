package com.example.gorenganindonesia.API.Handlers;

import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.user.PasswordService;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.PutRatingRequest;
import com.example.gorenganindonesia.Model.api.User.PutPasswordRequest;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordHandler {
    APIHandlerDAO dao;
    PasswordService passwordService;

    public PasswordHandler(APIHandlerDAO dao) {
        this.dao = dao;

        this.passwordService = RetrofitClient.getInstance().create(PasswordService.class);
    }

    public APIHandlerDAO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public void putPassword(PutPasswordRequest putPasswordRequest){
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();

        dao.loadingView.setVisibility(View.VISIBLE);
        dao.loadingText.setText("Memperbarui\nKata Sandi...");

        passwordService
                .putPassword(token, putPasswordRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        dao.loadingView.setVisibility(View.GONE);
                        if(response.isSuccessful()){
                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            int statusCode = response.code();
                            if(statusCode == 401)
                                if(dao.negativeCallback != null)
                                    dao.negativeCallback.run();
                            else {
                                try {
                                    new CustomToast("Gagal Memperbarui Kata Sandi: " + response.errorBody().string(), dao.view, false).show();
                                } catch (IOException e) {
                                    new CustomToast("Gagal Memperbarui Kata Sandi", dao.view, false).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Memperbarui Kata Sandi: Koneksi Gagal", dao.view, false).show();
                    }
                });
    }
}
