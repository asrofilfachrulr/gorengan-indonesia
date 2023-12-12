package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.user.PasswordService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.User.PutPasswordRequest;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordHandler {
    APIHandlerDTO dto;
    PasswordService passwordService;

    public PasswordHandler(APIHandlerDTO dto) {
        this.dto = dto;

        this.passwordService = RetrofitClient.getInstance().create(PasswordService.class);
    }

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public void putPassword(PutPasswordRequest putPasswordRequest){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memperbarui Kata Sandi");
        progressDialog.setCancelable(false);
        progressDialog.show();

        passwordService
                .putPassword(token, putPasswordRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            int statusCode = response.code();
                            if(statusCode == 401)
                                if(dto.negativeCallback != null)
                                    dto.negativeCallback.run();
                            else {
                                try {
                                    new CustomToast("Gagal Memperbarui Kata Sandi: " + response.errorBody().string(), dto.view, false).show();
                                } catch (IOException e) {
                                    new CustomToast("Gagal Memperbarui Kata Sandi", dto.view, false).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Memperbarui Kata Sandi: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
