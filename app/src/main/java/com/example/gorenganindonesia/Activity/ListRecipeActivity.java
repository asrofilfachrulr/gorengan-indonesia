package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.Constants;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.Util.RegexHelper;
import com.example.gorenganindonesia.databinding.ActivityListRecipeBinding;
import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.ListCategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.ListRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeActivity extends AppCompatActivity {
    List<Recipe> recipes = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    RecipeViewModel recipeViewModel;
    ActivityListRecipeBinding binding;

    RecyclerView rvListRecipe, rvCategory;
    ListRecipeAdapter listRecipeAdapter;
    ListCategoryAdapter listCategoryAdapter;

    String categoryInput;
    boolean isSearchClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListRecipeBinding.inflate(getLayoutInflater());
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

        rvListRecipe = (RecyclerView) binding.rvRecipeListRecipe;
        listRecipeAdapter = new ListRecipeAdapter(recipes, rvListRecipe);
        RecyclerView.LayoutManager listRecipeLM = new LinearLayoutManager(this);
        rvListRecipe.setLayoutManager(listRecipeLM);
        rvListRecipe.setAdapter(listRecipeAdapter);
        rvListRecipe.addItemDecoration(new RecyclerViewItemSpacing(this, 10));

        rvCategory = (RecyclerView) binding.rvCategoryListRecipe;
        listCategoryAdapter = new ListCategoryAdapter(categories, listRecipeAdapter, rvListRecipe, rvCategory);
        RecyclerView.LayoutManager listCategoryLM = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(listCategoryLM);
        rvCategory.setAdapter(listCategoryAdapter);

        recipeViewModel.getAllRecipes().observe(this, updatedRecipes -> {
            Logger.SimpleLog("updatedRecipes size: " + String.valueOf(recipes.size()));
            listRecipeAdapter.updateData(updatedRecipes, listCategoryAdapter.getCurrentCategory());
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

                RegexHelper regexHelper = new RegexHelper();
                if(regexHelper.create(s.toString()).isBlank())
                    listRecipeAdapter.clearTitle();

                listRecipeAdapter.applyFilter(Constants.EMPTY_STRING, s.toString());
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