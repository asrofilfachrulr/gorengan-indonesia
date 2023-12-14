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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gorenganindonesia.Activity.RecipeEditorActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.FragmentMyRecipeBinding;
import com.example.gorenganindonesia.ui.Adapters.MyRecipeAdapter;

import java.util.List;

public class MyRecipeFragment extends Fragment {

    private FragmentMyRecipeBinding binding;

    List<Recipe> myRecipes;

    RecipeViewModel recipeViewModel;
    AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        APIHandlerDTO dto = new APIHandlerDTO(root, getContext(), null);

        recipeViewModel = ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel();
        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        String username = accountViewModel.getUsername();

        myRecipes = recipeViewModel.getMyRecipes(username);

        MyRecipeAdapter adapter = new MyRecipeAdapter(myRecipes, getContext(), dto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvMyRecipes.getContext(), DividerItemDecoration.VERTICAL);
//        binding.rvMyRecipes.addItemDecoration(dividerItemDecoration);

        binding.rvMyRecipes.setAdapter(adapter);
        binding.rvMyRecipes.setLayoutManager(linearLayoutManager);

        binding.ibAddMyReceipt.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), RecipeEditorActivity.class));
        });

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), receipts -> {
            List<Recipe> updatedMyRecipes = recipeViewModel.getMyRecipes(username);

            adapter.updateData(updatedMyRecipes);

            if(updatedMyRecipes.size() > 0) {
                binding.llEmptyMyReceiptSign.setVisibility(View.GONE);
                binding.nsvListMyReceipt.setVisibility(View.VISIBLE);
            } else {
                binding.llEmptyMyReceiptSign.setVisibility(View.VISIBLE);
                binding.nsvListMyReceipt.setVisibility(View.GONE);
            }
        });

        binding.ivUnderlayTopMyRecipe.setOnClickListener(v -> {
            binding.etSearchMyRecipe.clearFocus();
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        binding.etSearchMyRecipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                adapter.applyFiler(str);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}