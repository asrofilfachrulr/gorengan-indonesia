package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.gorenganindonesia.Activity.ListRecipeActivity;
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
    List<Recipe> someRecipes = new ArrayList<>();
    List<Recipe> mostViewedRecipes = new ArrayList<>();

    RecyclerView rvRecipe, rvMostViewedRecipe;

    RecipeAdapter recipeAdapter;

    MostViewedRecipeAdapter mostViewedRecipeAdapter;


    private FragmentHomeBinding binding;

    RecipeViewModel recipeViewModel;
    private String token;
    private String greeting;
    private String userFullName;

    private View root;

    RecipeHandler recipeHandler;
    FavouriteHandler favouriteHandler;
    CategoryHandler categoryHandler;

    private final int LIST_RECIPES_SIZE = 3;
    private final int LIST_TOP_RECIPES_SIZE = 5;


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

        someRecipes = new ArrayList<>();

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

        someRecipes = getSomeRecipes();
        mostViewedRecipes = getTopRecipes();

        int recipeSpacing = getResources().getDimensionPixelSize(R.dimen.recipe_spacing);
        rvRecipe = (RecyclerView) binding.rvReceipt;
        recipeAdapter = new RecipeAdapter(someRecipes, requireContext());
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(getContext());
        rvRecipe.setLayoutManager(receiptLayoutManager);
        rvRecipe.setAdapter(recipeAdapter);
        rvRecipe.addItemDecoration(new RecyclerViewItemSpacing(getContext(), recipeSpacing));

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            List<Recipe> someRecipesUpdated = getSomeRecipes();
            recipeAdapter.updateData(someRecipesUpdated);

            List<Recipe> uMostViewedRecipes = getTopRecipes();
            mostViewedRecipeAdapter.updateData(uMostViewedRecipes);
        });


        int mostViewedRecipeSpacing = getResources().getDimensionPixelSize(R.dimen.most_viewed_recipes_spacing);
        rvMostViewedRecipe = (RecyclerView) binding.rvMostViewedRecipe;
        mostViewedRecipeAdapter = new MostViewedRecipeAdapter(mostViewedRecipes, requireContext());
        RecyclerView.LayoutManager mostViewedRecipeLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMostViewedRecipe.setAdapter(mostViewedRecipeAdapter);
        rvMostViewedRecipe.setLayoutManager(mostViewedRecipeLM);
        rvMostViewedRecipe.addItemDecoration(new RecyclerViewItemSpacing(getContext(), mostViewedRecipeSpacing));

        binding.etSearch.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Intent intent = new Intent(getActivity(), ListRecipeActivity.class);
                intent.putExtra("isSearchClicked", true);
                getActivity().startActivity(intent);
                return true;  // Consume the event to prevent further propagation
            }
            return false;  // Allow other touch events to be handled
        });

        binding.btnMoreRecipeHomeFragment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ListRecipeActivity.class);
            getActivity().startActivity(intent);
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Recipe> getSomeRecipes(){
        if(recipeViewModel == null)
            return new ArrayList<>();
        return recipeViewModel.getRecipesBy(LIST_RECIPES_SIZE);
    }

    private List<Recipe> getTopRecipes(){
        if(recipeViewModel == null)
            return new ArrayList<>();
        return recipeViewModel.findTopNRecipes(LIST_TOP_RECIPES_SIZE);
    }


}