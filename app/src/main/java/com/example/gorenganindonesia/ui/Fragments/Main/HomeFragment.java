package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.example.gorenganindonesia.API.Handlers.FavouriteHandler;
import com.example.gorenganindonesia.API.Handlers.RecipeHandler;
import com.example.gorenganindonesia.API.Services.RecipesService;
import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.Activity.LoginActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.api.Recipes.RecipeData;
import com.example.gorenganindonesia.Model.api.Recipes.GetAllRecipesResponse;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.databinding.FragmentHomeBinding;
import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.RecipeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    List<com.example.gorenganindonesia.Model.data.Recipe.Recipe> recipes = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;
    RecipeAdapter recipeAdapter;

    LinearLayout llParentContent;

    EditText etSearch;
    private FragmentHomeBinding binding;

    RecipeViewModel recipeViewModel;
    private String token;

    private View root;

    RecipeHandler recipeHandler;
    FavouriteHandler favouriteHandler;

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

        recipes = new ArrayList<>();
        categories = new ArrayList<>();

        APIHandlerDTO dao = new APIHandlerDTO(
                root,
                binding.llRootLoadingHome,
                binding.tvRootLoadingHome,
                getContext()
        );

        recipeHandler = new RecipeHandler(dao);
        favouriteHandler = new FavouriteHandler(dao);

        if(recipeViewModel.getAllRecipes().getValue().size() == 0){
            APIHandlerDTO tempDAO = recipeHandler.getDto();
            tempDAO.setCallback(() -> {
                favouriteHandler.getFavourites();
            });
            recipeHandler.setDto(tempDAO);
            recipeHandler.getAllRecipes();
//            getAllRecipesRequest(token);
        }

        recipes = recipeViewModel.getAllRecipes().getValue();

        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.receipt_spacing);
        rvReceipt = (RecyclerView) binding.rvReceipt;
        recipeAdapter = new RecipeAdapter(recipes, rvReceipt);
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(getContext());
        rvReceipt.setLayoutManager(receiptLayoutManager);
        rvReceipt.setAdapter(recipeAdapter);
        rvReceipt.addItemDecoration(new RecyclerViewItemSpacing(getContext(), receiptSpacing));

        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.category_spacing);
        rvCategory = (RecyclerView) binding.rvCategory;
        categoryAdapter = new CategoryAdapter(categories, recipeAdapter, rvReceipt, rvCategory);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(categoryLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new RecyclerViewItemSpacing(getContext(), categorySpacing));

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            recipeAdapter.updateData(updatedRecipes, categoryAdapter.getCurrentCategory());
        });

        recipeViewModel.getCategories().observe(getViewLifecycleOwner(), updatedCategories -> {
            categoryAdapter.updateData(updatedCategories);
        });

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
                rvReceipt.scrollToPosition(0);
                recipeAdapter.applyFilterTitle(s.toString());
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

    private void getAllRecipesRequest(String token){
        RetrofitClient
                .getInstance()
                .create(RecipesService.class)
                .getAllRecipes(token)
                .enqueue(new Callback<GetAllRecipesResponse>() {
                    @Override
                    public void onResponse(Call<GetAllRecipesResponse> call, Response<GetAllRecipesResponse> response) {
                        int statusCode = response.code();
                        binding.llRootLoadingHome.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            categories = CategoryData.generate();

                            List<Recipe> tempRecipes = new ArrayList<>();

                            for (RecipeData recipeData : response.body().getData()) {
                                tempRecipes.add(new Recipe(
                                        recipeData.getId(),
                                        recipeData.getTitle(),
                                        recipeData.getUsername(),
                                        recipeData.getStars(),
                                        recipeData.getCategory(),
                                        recipeData.getMinuteDuration(),
                                        recipeData.getImgUrl(),
                                        recipeData.getDifficulty(),
                                        recipeData.getPortion(),
                                        null,
                                        null,
                                        null
                                ));
                            }

                            recipeViewModel.setRecipesData(tempRecipes);
                            //TODO: Automate category list from available recipes on backend or database!
                            recipeViewModel.setCategoriesData(CategoryData.generate());
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Status code: ", String.valueOf(statusCode));
                                Log.e("Error Response Body", errorBody);

                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.optString("message");

                                new CustomToast("Error: " + errorMessage, root.getRootView()).show();

                            } catch (IOException | JSONException e) {
                                Log.e("error", e.toString());
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllRecipesResponse> call, Throwable t) {
                        binding.llRootLoadingHome.setVisibility(View.GONE);

                        new CustomToast("Tidak dapat memuat data dari jaringan", root.getRootView(), false).show();
                    }
                });
    }
}