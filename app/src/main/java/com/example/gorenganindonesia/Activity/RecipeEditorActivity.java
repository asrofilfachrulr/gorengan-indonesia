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
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Step.Step;
import com.example.gorenganindonesia.Util.FileUtils;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.ActivityRecipeEditorBinding;
import com.example.gorenganindonesia.ui.Adapters.NewIngredientAdapter;
import com.example.gorenganindonesia.ui.Adapters.NewStepAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeEditorActivity extends AppCompatActivity {
    RecyclerView rvIngredient, rvStep;

    private ActivityRecipeEditorBinding binding;
    private List<Step> newSteps = new ArrayList<>();
    private List<Ingredient> newIngredients = new ArrayList<>();
    private Recipe newRecipe;

    private static final int PICK_FILE_REQUEST_CODE = 1832;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeEditorBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCancelNewRecipe.setOnClickListener(v -> {
           showExitConfirmationDialog();
        });

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");

        if(recipe == null){
            newRecipe = new Recipe();

            newIngredients.add(new Ingredient());
            newSteps.add(new Step("", 1));
        } else {
            newIngredients = Arrays.asList(recipe.getIngredients());

            String[] steps = recipe.getSteps();
            for(int i = 1; i < steps.length; i++){
                newSteps.add(new Step(steps[i], Integer.valueOf(i)));
            }

            setRecipeImage("", recipe.getImgUrl());
            newRecipe = recipe;
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

        binding.btnNewCategoryRecipeEditor.setOnClickListener(v -> {
            binding.llNewCategoryRecipeEditor.setVisibility(View.VISIBLE);
            binding.etNewCategoryRecipeEditor.setText("");
        });

        binding.btnNewCategoryCancelRecipeEditor.setOnClickListener(v -> {
            binding.llNewCategoryRecipeEditor.setVisibility(View.GONE);
            binding.etNewCategoryRecipeEditor.setText("");
        });

        binding.btnSaveNewRecipe.setOnClickListener(v -> {
            try {
                newRecipe.setTitle(binding.etTitleRecipeEditor.getText().toString());
                newRecipe.setMinuteDuration(
                        Integer.valueOf(binding.etMinuteDurationRecipeEditor.getText().toString())
                );

                int portion = Integer.valueOf(binding.etPortionRecipeEditor.getText().toString());
                newRecipe.setPortion(portion);

                String username = ((GlobalModel) getApplication()).getAccountViewModel().getUsername();
                newRecipe.setAuthorUsername(username);

                String newCategory = binding.etNewCategoryRecipeEditor.getText().toString();
                String category = newCategory.isEmpty() ? binding.spCategoryRecipeEditor.getSelectedItem().toString() : newCategory;
                newRecipe.setCategory(category);

                String difficulty = binding.spDifficultyRecipeEditor.getSelectedItem().toString();
                newRecipe.setDifficulty(difficulty);

                List<Ingredient> ingredientsData = ingredientAdapter.getDataList();
                List<Step> stepsData = stepAdapter.getDataList();

                Ingredient[] ingredients = new Ingredient[ingredientsData.size()];
                for (int i = 0; i < ingredients.length; i++)
                    ingredients[i] = ingredientsData.get(i);

                newRecipe.setIngredients(ingredients);

                String[] steps = new String[stepsData.size()];

                for (int i = 0; i < steps.length; i++)
                    steps[i] = stepsData.get(i).getDescription();

                newRecipe.setSteps(steps);

                Logger.SimpleLog(newRecipe.toString());
            } catch (Exception e){
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

                    String fileName = FileUtils.getFileName(this, selectedUri);

                    setRecipeImage(fileName, selectedUri);
                    break;
            }
        }
    }

    public void setRecipeImage(String fileName, String imageUrl){
        setRecipeImage(fileName, null, imageUrl);
        newRecipe.setImgUrl(imageUrl);
    }

    public void setRecipeImage(String fileName, Uri uri){
        setRecipeImage(fileName, uri, null);
        newRecipe.setImgUrl(uri.getPath());
    }

    public void setRecipeImage(String fileName, Uri uri, String imageUrl){
        binding.tvImageFileNameNewReceipt.setVisibility(View.VISIBLE);
        binding.tvImageFileNameNewReceipt.setText(fileName);
        binding.btnPickImgNewReceipt.setText("Edit Gambar");

        Glide
                .with(this)
                .load(uri != null ? uri : imageUrl)
                .into(binding.ivPreviewNewRecipe);

        binding.ccRecipeImageNewRecipe.setVisibility(View.VISIBLE);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//        }
//        else if (event.getAction() == MotionEvent.ACTION_UP) {
//            View v = getCurrentFocus();
//            if ( v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//        }
//        return super.dispatchTouchEvent( event );
//    }
}