package com.example.gorenganindonesia.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Step.Step;
import com.example.gorenganindonesia.Util.FileUtils;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.ActivityNewRecipeBinding;
import com.example.gorenganindonesia.ui.Adapters.NewIngredientAdapter;
import com.example.gorenganindonesia.ui.Adapters.NewStepAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewRecipeActivity extends AppCompatActivity {
    RecyclerView rvIngredient, rvStep;

    private ActivityNewRecipeBinding binding;
    private List<Step> newSteps = new ArrayList<>();
    private List<Ingredient> newIngredients = new ArrayList<>();
    private Recipe newRecipe;

    private static final int PICK_FILE_REQUEST_CODE = 1832;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewRecipeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCancelNewRecipe.setOnClickListener(v -> {
           showExitConfirmationDialog();
        });

        // populate model with empty data as init
        newIngredients.add(new Ingredient());

        newSteps.add(new Step("", 1));

        rvIngredient = (RecyclerView) binding.rvAddIngredientNewRecipe;
        LinearLayoutManager ingredientLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        NewIngredientAdapter ingredientAdapter = new NewIngredientAdapter(newIngredients);
        rvIngredient.setLayoutManager(ingredientLM);
        rvIngredient.setAdapter(ingredientAdapter);

        rvStep = (RecyclerView) binding.rvAddStepNewRecipe;
        LinearLayoutManager stepLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        NewStepAdapter stepAdapter = new NewStepAdapter(newSteps);
        rvStep.setLayoutManager(stepLM);
        rvStep.setAdapter(stepAdapter);

        binding.btnSaveNewRecipe.setOnClickListener(v -> {
            List<Ingredient> ingredientsData = ingredientAdapter.getDataList();
            List<Step> stepsData = stepAdapter.getDataList();

            for(Ingredient ingredient: ingredientsData)
                Logger.SimpleLog(ingredient.toString());

            for(Step step: stepsData)
                Logger.SimpleLog(step.toString());

            ToastUseCase.showInDevelopment(binding.getRoot());
        });

        binding.btnNewFieldNewIngredientNewRecipe.setOnClickListener(v -> {
            ingredientAdapter.addEmptyItem();
        });

        binding.btnNewFieldNewStepNewRecipe.setOnClickListener(v -> {
            stepAdapter.addEmptyItem();
        });

        binding.btnPickImgNewReceipt.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        });

        binding.ibBackNewReceipt.setOnClickListener(v -> {
            showExitConfirmationDialog();
        });
    }

    private void showExitConfirmationDialog(){
        Logger.SimpleLog("cancel clicked");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Apakah Anda yakin ingin membatalkan? Semua data yang ada telah isi akan hilang")
                .setPositiveButton("Ya", (dialog, which) -> {
                    dialog.dismiss();
                    finish();
                })
                .setNegativeButton("Tidak", (dialog, which) -> {
                    dialog.dismiss();
                });

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PICK_FILE_REQUEST_CODE:
                    Uri selectedUri = data.getData();

                    String fileName = FileUtils.getFileName(this, selectedUri);

                    binding.tvImageFileNameNewReceipt.setVisibility(View.VISIBLE);
                    binding.tvImageFileNameNewReceipt.setText(fileName);
                    binding.btnPickImgNewReceipt.setText("Edit Gambar");

                    Glide
                            .with(this)
                            .load(selectedUri)
                            .into(binding.ivPreviewNewRecipe);

                    binding.ccRecipeImageNewRecipe.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}