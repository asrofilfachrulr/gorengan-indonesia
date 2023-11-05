package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gorenganindonesia.Activity.NewRecipeActivity;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
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

        recipeViewModel = ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel();
        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        String username = accountViewModel.getUsername();

        myRecipes = recipeViewModel.getMyRecipes(username);

        MyRecipeAdapter adapter = new MyRecipeAdapter(myRecipes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvMyRecipes.getContext(), DividerItemDecoration.VERTICAL);

        binding.rvMyRecipes.setAdapter(adapter);
        binding.rvMyRecipes.addItemDecoration(dividerItemDecoration);
        binding.rvMyRecipes.setLayoutManager(linearLayoutManager);

        binding.ibAddMyReceipt.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), NewRecipeActivity.class));
        });

        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), receipts -> {
            List<Recipe> updatedMyRecipes = recipeViewModel.getMyRecipes(username);

            adapter.updateData(updatedMyRecipes);

            if(adapter.getItemCount() > 0) {
                binding.llEmptyMyReceiptSign.setVisibility(View.GONE);
                binding.svListMyReceipt.setVisibility(View.VISIBLE);
            } else {
                binding.llEmptyMyReceiptSign.setVisibility(View.VISIBLE);
                binding.svListMyReceipt.setVisibility(View.GONE);
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