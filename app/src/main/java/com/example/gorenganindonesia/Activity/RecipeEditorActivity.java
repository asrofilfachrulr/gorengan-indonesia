package com.example.gorenganindonesia.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.API.Handlers.RecipeHandler;
import com.example.gorenganindonesia.Model.Adapters.RecipeToPostRecipeAdapter;
import com.example.gorenganindonesia.Model.Adapters.RecipeToPutRecipeAdapter;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.Recipe.PostRecipeRequest;
import com.example.gorenganindonesia.Model.api.Recipe.PutRecipeRequest;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Step.Step;
import com.example.gorenganindonesia.Util.BitmapHelper;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.ActivityRecipeEditorBinding;
import com.example.gorenganindonesia.ui.Adapters.NewIngredientAdapter;
import com.example.gorenganindonesia.ui.Adapters.NewStepAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RecipeEditorActivity extends AppCompatActivity {
    RecyclerView rvIngredient, rvStep;

    private ActivityRecipeEditorBinding binding;
    private List<Step> newSteps = new ArrayList<>();
    private List<Ingredient> newIngredients = new ArrayList<>();
    private Recipe nRecipe;
    String mode;
    Uri selectedImageUri;
    Recipe recipeOrigin;

    private static final int PICK_FILE_REQUEST_CODE = 1832;
    private static final String REQUEST_MODE_POST = "POST";
    private static final String REQUEST_MODE_PUT = "PUT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeEditorBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCancelNewRecipe.setOnClickListener(v -> {
           showExitConfirmationDialog();
        });

        Intent intent = getIntent();
        recipeOrigin = (Recipe) intent.getParcelableExtra("recipe");

        List<String> lCategories = ((GlobalModel) getApplication()).getRecipeViewModel().getCategories().getValue();
        ArrayAdapter<String> categoryStringAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                lCategories
        );

        binding.actvCategoryRecipeEditor.setAdapter(categoryStringAdapter);

        nRecipe = new Recipe();

        if(recipeOrigin == null){
            mode = REQUEST_MODE_POST;

            newIngredients.add(new Ingredient());
            newSteps.add(new Step("", 1));
        } else {
            mode = REQUEST_MODE_PUT;
            selectedImageUri = null;

            Logger.SimpleLog("recipeOrigin: " + recipeOrigin.toString());


            binding.btnResetImgNewReceipt.setVisibility(View.VISIBLE);
            binding.btnResetImgNewReceipt.setOnClickListener(v -> {
                setRecipeImage(recipeOrigin.getImgUrl());
                selectedImageUri = null;
            });

            newIngredients = new ArrayList<>();
            for(Ingredient ingredient: recipeOrigin.getIngredients())
                newIngredients.add(ingredient);

            String[] steps = recipeOrigin.getSteps();
            for(int i = 0; i < steps.length; i++){
                newSteps.add(new Step(steps[i], i+1));
            }

            Logger.SimpleLog("newSteps: ");
            for(Step step: newSteps)
                Logger.SimpleLog(step.toString());

            setRecipeImage(recipeOrigin.getImgUrl());
            binding.etTitleRecipeEditor.setText(recipeOrigin.getTitle());
            binding.etMinuteDurationRecipeEditor.setText(String.valueOf(recipeOrigin.getMinuteDuration()));
            binding.etPortionRecipeEditor.setText(String.valueOf(recipeOrigin.getPortion()));

            switch(recipeOrigin.getDifficulty().toLowerCase()){
                case "mudah":
                    binding.spDifficultyRecipeEditor.setSelection(0);
                    break;
                case "sedang":
                    binding.spDifficultyRecipeEditor.setSelection(1);
                    break;
                case "sulit":
                    binding.spDifficultyRecipeEditor.setSelection(2);
                    break;
                default:
                    binding.spDifficultyRecipeEditor.setSelection(0);
            }

            binding.actvCategoryRecipeEditor.setText(recipeOrigin.getCategory());
        }


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
            try {
                nRecipe.setTitle(binding.etTitleRecipeEditor.getText().toString());
                nRecipe.setMinuteDuration(
                        Integer.valueOf(binding.etMinuteDurationRecipeEditor.getText().toString())
                );

                int portion = Integer.valueOf(binding.etPortionRecipeEditor.getText().toString());
                nRecipe.setPortion(portion);

                String username = ((GlobalModel) getApplication()).getAccountViewModel().getUsername();
                nRecipe.setAuthorUsername(username);


                String category = binding.actvCategoryRecipeEditor.getText().toString();
                nRecipe.setCategory(category);

                String difficulty = binding.spDifficultyRecipeEditor.getSelectedItem().toString();
                nRecipe.setDifficulty(difficulty);

                List<Ingredient> ingredientsData = ingredientAdapter.getDataList();
                List<Step> stepsData = stepAdapter.getDataList();

                Ingredient[] ingredients = new Ingredient[ingredientsData.size()];
                for (int i = 0; i < ingredients.length; i++)
                    ingredients[i] = ingredientsData.get(i);

                nRecipe.setIngredients(ingredients);

                String[] steps = new String[stepsData.size()];

                for (int i = 0; i < steps.length; i++)
                    steps[i] = stepsData.get(i).getDescription();

                nRecipe.setSteps(steps);

                Logger.SimpleLog(nRecipe.toString());

                requestAPIRecipe();
            } catch (Exception e){
                e.printStackTrace();
                ToastUseCase.showMessage(binding.getRoot(), "[Error] " + e.getMessage());
            }
//            ToastUseCase.showInDevelopment(binding.getRoot());
        });

        binding.btnNewFieldNewIngredientNewRecipe.setOnClickListener(v -> {
            ingredientAdapter.addEmptyItem();
        });

        binding.btnNewFieldNewStepNewRecipe.setOnClickListener(v -> {
            stepAdapter.addEmptyItem();
        });

        binding.btnPickImgNewReceipt.setOnClickListener(v -> {
            Intent getContentIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getContentIntent.setType("image/*");
            startActivityForResult(getContentIntent, PICK_FILE_REQUEST_CODE);
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
                    selectedImageUri = selectedUri;

                    setRecipeImage(selectedUri);
                    break;
            }
        }
    }

    void setRecipeImage(String imageUrl){
        setRecipeImage(null, imageUrl);
        nRecipe.setImgUrl(imageUrl);
    }

    void setRecipeImage(Uri uri){
        setRecipeImage(uri, null);
        nRecipe.setImgUrl(uri.getPath());
    }

    void setRecipeImage(Uri uri, String imageUrl){
        binding.btnPickImgNewReceipt.setText("Edit Gambar");

        if(uri != null) {
            Glide
                    .with(this)
                    .load(uri)
                    .into(binding.ivPreviewNewRecipe);
        } else if (imageUrl != null){
            Glide
                    .with(this)
                    .load(imageUrl)
                    .into(binding.ivPreviewNewRecipe);
        }


        binding.ccRecipeImageNewRecipe.setVisibility(View.VISIBLE);
    }

    void requestAPIRecipe(){
        if(mode.equals(REQUEST_MODE_POST))
            requestPostRecipe();
        else if(mode.equals(REQUEST_MODE_PUT))
            requestPutRecipe();
    }

    void requestPostRecipe(){
        MultipartBody.Part imagePart;

        try {
            APIHandlerDTO dto = new APIHandlerDTO(binding.getRoot(), binding.llRootLoadingNewRecipe, binding.tvRootLoadingNewRecipe, this);

            RecipeToPostRecipeAdapter adapter = new RecipeToPostRecipeAdapter(nRecipe);
            PostRecipeRequest postModel = adapter.convert();

            RecipeHandler handler = new RecipeHandler(dto);

            dto.setCallback(() -> {
                APIHandlerDTO dto1 = new APIHandlerDTO(binding.getRoot(), binding.llRootLoadingNewRecipe, binding.tvRootLoadingNewRecipe, this);
                dto1.setCallback(() -> {
                    ToastUseCase.showMessage(binding.getRoot(), "Berhasil membuat resep!");
                    this.finish();
                });

                RecipeHandler handler1 = new RecipeHandler(dto1);
                handler1.getAllRecipes();
            });

            handler.setDto(dto);

            dto.setNegativeCallback(() -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage("Kesalahan dalam membuat resep. Pastikan data yang diisi benar!")
                        .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                        .show();
            });

            Gson gson = new Gson();
            String jsonData = gson.toJson(postModel);
            RequestBody jsonRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData);

            BitmapHelper bitmapHelper = new BitmapHelper(this);
            Bitmap compressedBitmap = bitmapHelper.compressImage(selectedImageUri, getContentResolver());

            if(compressedBitmap == null)
                throw new Exception("Gagal dalam memproses gambar");

            imagePart = bitmapHelper.bitmapToMultipartBody(compressedBitmap, selectedImageUri);

            handler.postRecipe(imagePart, jsonRequestBody);
        } catch (Exception e){
            ToastUseCase.showMessage(binding.getRoot(), "[Error] " + e.getMessage());
            Log.e("Error Post Recipe", e.getMessage());
            Log.e("Error Post Recipe", e.toString());
        }
    }

    void requestPutRecipe(){
        Logger.SimpleLog("SelectedUri: " + selectedImageUri);
        MultipartBody.Part imagePart;

        try {
            APIHandlerDTO dto = new APIHandlerDTO(binding.getRoot(), binding.llRootLoadingNewRecipe, binding.tvRootLoadingNewRecipe, this);

            RecipeToPutRecipeAdapter adapter = new RecipeToPutRecipeAdapter(nRecipe);
            PutRecipeRequest putModel = adapter.convert();

            RecipeHandler handler = new RecipeHandler(dto);

            dto.setCallback(() -> {
                APIHandlerDTO dto1 = new APIHandlerDTO(binding.getRoot(), binding.llRootLoadingNewRecipe, binding.tvRootLoadingNewRecipe, this);
                dto1.setCallback(() -> {
                    ToastUseCase.showMessage(binding.getRoot(), "Berhasil memperbarui resep!");
                    this.finish();
                });

                RecipeHandler handler1 = new RecipeHandler(dto1);
                handler1.getAllRecipes();
            });

            handler.setDto(dto);

            dto.setNegativeCallback(() -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage("Kesalahan dalam memperbarui resep. Pastikan mengisi data dengan benar!")
                        .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                        .show();
            });

            Gson gson = new Gson();
            String jsonData = gson.toJson(putModel);
            RequestBody jsonRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData);

            if(selectedImageUri != null){
                BitmapHelper bitmapHelper = new BitmapHelper(this);
                Bitmap compressedBitmap = bitmapHelper.compressImage(selectedImageUri, getContentResolver());
                if(compressedBitmap == null)
                    throw new Exception("Gagal dalam memproses gambar");
                imagePart = bitmapHelper.bitmapToMultipartBody(compressedBitmap, selectedImageUri);

                handler.putRecipe(imagePart, jsonRequestBody, recipeOrigin.getId());
            } else {
                handler.putRecipe(null, jsonRequestBody, recipeOrigin.getId());
            }



        } catch (Exception e){
            ToastUseCase.showMessage(binding.getRoot(), "[Error] " + e.getMessage());
            Log.e("Error Post Recipe", e.getMessage());
            Log.e("Error Post Recipe", e.toString());
        }
    }
}