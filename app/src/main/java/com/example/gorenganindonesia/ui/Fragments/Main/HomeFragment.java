package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
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

import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.databinding.FragmentHomeBinding;
import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.RecipeAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    List<Recipe> recipes;
    List<String> categories;

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;
    RecipeAdapter recipeAdapter;

    LinearLayout llParentContent;

    EditText etSearch;
    private FragmentHomeBinding binding;

    RecipeViewModel recipeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        recipeViewModel = ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel();

        View root = binding.getRoot();
        categories = CategoryData.generate();
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

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), updatedReceipts -> {
            recipeAdapter.updateData(updatedReceipts);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rvReceipt.scrollToPosition(0);
                recipeAdapter.applyFilterTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}