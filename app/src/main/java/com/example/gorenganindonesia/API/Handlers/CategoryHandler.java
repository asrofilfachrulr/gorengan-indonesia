package com.example.gorenganindonesia.API.Handlers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.util.Log;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.CategoriesService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.GetCategoriesResponse;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Util.ToastUseCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryHandler {
    private APIHandlerDTO dto;

    public CategoryHandler(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public void getCategories(){
        ProgressDialog progressDialog = new ProgressDialog(dto.context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Memuat Kategori...");
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(CategoriesService.class)
                .getCategories()
                .enqueue(new Callback<GetCategoriesResponse>() {
                    @Override
                    public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            String[] categories = response.body().getData();
                            List<String> lCategories = new ArrayList<>(Arrays.asList(categories));
                            lCategories.add(0, "semua");

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(lCategories);
                        } else {
                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(CategoryData.generate());
                            try {
                                ToastUseCase.showMessage(dto.view,"Gagal Memuat Kategori: " + response.errorBody().string());
                                Log.e("Error", response.errorBody().string());
                            } catch (IOException e) {
                                ToastUseCase.showMessage(dto.view,"Gagal Memuat Kategori");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<GetCategoriesResponse> call, Throwable t) {
                        ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(CategoryData.generate());
                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(dto.context);
                        builder.setMessage("Sambungan Error. Harap Periksa Sambungan Internet Anda")
                                .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                                .show();
                    }
                });
    }
}
