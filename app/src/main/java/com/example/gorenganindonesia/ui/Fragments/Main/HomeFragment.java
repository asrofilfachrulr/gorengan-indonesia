package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.API.Handlers.CategoryHandler;
import com.example.gorenganindonesia.API.Handlers.FavouriteHandler;
import com.example.gorenganindonesia.API.Handlers.RecipeHandler;
import com.example.gorenganindonesia.Activity.LoginActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.Constants;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.GreetingGenerator;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.Util.RegexHelper;
import com.example.gorenganindonesia.databinding.FragmentHomeBinding;

import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.MostViewedRecipeAdapter;
import com.example.gorenganindonesia.ui.Adapters.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    List<Recipe> recipes = new ArrayList<>();
    List<Recipe> mostViewedRecipes = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    RecyclerView rvRecipe, rvCategory, rvMostViewedRecipe;

    CategoryAdapter categoryAdapter;
    RecipeAdapter recipeAdapter;

    MostViewedRecipeAdapter mostViewedRecipeAdapter;

    LinearLayout llParentContent;

    EditText etSearch;
    private FragmentHomeBinding binding;

    RecipeViewModel recipeViewModel;
    private String token;
    private String greeting;
    private String userFullName;

    private View root;

    RecipeHandler recipeHandler;
    FavouriteHandler favouriteHandler;
    CategoryHandler categoryHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        token = "Bearer " + ((GlobalModel) getContext().getApplicationContext()).getSessionManager().getToken();
        if(token.isEmpty()){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("custom_toast_msg", "Sesi telah berakhir, masuk kembali");
            startActivity(intent);
            getActivity().finish();
        }

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        recipeViewModel = ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel();

        root = binding.getRoot();

        userFullName = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel().getName();
        greeting = GreetingGenerator.generateGreeting();

        binding.tvGreetingHomeFragment.setText(greeting + ", ");
        binding.tvUserFullNameHomeFragment.setText(userFullName);

        recipes = new ArrayList<>();
        categories = new ArrayList<>();

        APIHandlerDTO dao = new APIHandlerDTO(root,getContext());

        recipeHandler = new RecipeHandler(dao);
        favouriteHandler = new FavouriteHandler(dao);
        categoryHandler = new CategoryHandler(dao);

        if(recipeViewModel.getAllRecipes().getValue().size() == 0){
            APIHandlerDTO tempDAO = recipeHandler.getDto();
            tempDAO.setCallback(() -> {
                favouriteHandler.getFavourites();
            });
            recipeHandler.setDto(tempDAO);
            categoryHandler.getCategories();
            recipeHandler.getAllRecipes();
        }

        recipes = recipeViewModel.getAllRecipes().getValue();

        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.recipe_spacing);
        rvRecipe = (RecyclerView) binding.rvReceipt;
        recipeAdapter = new RecipeAdapter(recipes, rvRecipe);
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(getContext());
        rvRecipe.setLayoutManager(receiptLayoutManager);
        rvRecipe.setAdapter(recipeAdapter);
        rvRecipe.addItemDecoration(new RecyclerViewItemSpacing(getContext(), receiptSpacing));

        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.category_spacing);
        rvCategory = (RecyclerView) binding.rvCategory;
        categoryAdapter = new CategoryAdapter(categories, recipeAdapter, rvRecipe, rvCategory);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(categoryLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new RecyclerViewItemSpacing(getContext(), categorySpacing));

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            recipeAdapter.updateData(updatedRecipes, categoryAdapter.getCurrentCategory());

            // mocking implementation for get data most viewed recipes
            List<Recipe> uMostViewedRecipes = new ArrayList<>();
            int sz = updatedRecipes.size() > 5 ? 5 : updatedRecipes.size();
            for(int i = 0; i < sz; i++)
                uMostViewedRecipes.add(updatedRecipes.get(i));

            mostViewedRecipeAdapter.updateData(uMostViewedRecipes);
        });

        recipeViewModel.getCategories().observe(getViewLifecycleOwner(), updatedCategories -> {
            categoryAdapter.updateData(updatedCategories);
        });


        int mostViewedRecipeSpacing = getResources().getDimensionPixelSize(R.dimen.most_viewed_recipes_spacing);
        rvMostViewedRecipe = (RecyclerView) binding.rvMostViewedRecipe;
        mostViewedRecipeAdapter = new MostViewedRecipeAdapter(mostViewedRecipes, requireContext());
        RecyclerView.LayoutManager mostViewedRecipeLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMostViewedRecipe.setAdapter(mostViewedRecipeAdapter);
        rvMostViewedRecipe.setLayoutManager(mostViewedRecipeLM);
        rvMostViewedRecipe.addItemDecoration(new RecyclerViewItemSpacing(getContext(), mostViewedRecipeSpacing));


        llParentContent = (LinearLayout) binding.llParentContent;
        etSearch = (EditText) binding.etSearch;

        llParentContent.setOnTouchListener((v, event) -> {
            etSearch.clearFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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
                    recipeAdapter.clearTitle();

                recipeAdapter.applyFilter(Constants.EMPTY_STRING, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();

//        new CustomToast("Current Category: " + categoryAdapter.getCurrentCategory(), getView().getRootView(), false).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}