package com.example.gorenganindonesia.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.Constants;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.databinding.ActivityListRecipeGvBinding;
import com.example.gorenganindonesia.ui.Adapters.ListCategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.RecipeListRVGAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeActivityRVG extends AppCompatActivity {
    List<Recipe> recipes = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    RecipeViewModel recipeViewModel;
    ActivityListRecipeGvBinding binding;

    RecyclerView rvCategory;
    RecyclerView rvgRecipe;
    RecipeListRVGAdapter recipeListRVGAdapter;
    ListCategoryAdapter listCategoryAdapter;

    String categoryInput;
    boolean isSearchClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListRecipeGvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        isSearchClicked = intent.getBooleanExtra("isSearchClicked", false);
        categoryInput = intent.getStringExtra("categoryInput");

        if(isSearchClicked)
            binding.etSearchListRecipe.requestFocus();


        recipeViewModel = ((GlobalModel) getApplication()).getRecipeViewModel();

        recipes = recipeViewModel.getAllRecipes().getValue();
        Logger.SimpleLog("recipes size: " + String.valueOf(recipes.size()));

        categories = recipeViewModel.getCategories().getValue();

        rvgRecipe = (RecyclerView) binding.rvgListRecipe;
        recipeListRVGAdapter = new RecipeListRVGAdapter(recipes, rvgRecipe);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvgRecipe.setLayoutManager(gridLayoutManager);
        rvgRecipe.setAdapter(recipeListRVGAdapter);

        rvCategory = (RecyclerView) binding.rvCategoryListRecipe;
        listCategoryAdapter = new ListCategoryAdapter(categories, recipeListRVGAdapter, rvgRecipe, rvCategory);
        RecyclerView.LayoutManager listCategoryLM = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(listCategoryLM);
        rvCategory.setAdapter(listCategoryAdapter);

        recipeViewModel.getAllRecipes().observe(this, updatedRecipes -> {
            Logger.SimpleLog("updatedRecipes size: " + String.valueOf(recipes.size()));
            recipeListRVGAdapter.updateData(updatedRecipes, listCategoryAdapter.getCurrentCategory());
        });

        recipeViewModel.getCategories().observe(this, updatedCategories -> {
            listCategoryAdapter.updateData(updatedCategories);
        });

        LinearLayout llParentListRecipe = (LinearLayout) binding.llParentListRecipe;
        EditText etSearch = (EditText) binding.etSearchListRecipe;

        llParentListRecipe.setOnTouchListener((v, event) -> {
            etSearch.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.SimpleLog("Search Input String: " + s.toString());

                if(TextUtils.isEmpty(s.toString()))
                    recipeListRVGAdapter.clearTitle();

                recipeListRVGAdapter.applyFilter(Constants.EMPTY_STRING, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(binding == null)
            return;

        binding.etSearchListRecipe.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
    }
}