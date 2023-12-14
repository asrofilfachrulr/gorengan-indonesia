package com.example.gorenganindonesia.ui.Fragments.Detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.ui.Adapters.IngredientAdapter;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsFragment extends Fragment {
    RecyclerView rvIngridients;
    List<Ingredient> ingredients;
    String recipeId;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    public IngredientsFragment(String recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        rvIngridients = (RecyclerView) view.findViewById(R.id.rv_ingredients);

        ingredients = getIngredients();

        if(ingredients == null)
            ingredients = new ArrayList<>();

        IngredientAdapter adapter = new IngredientAdapter(ingredients);
        RecyclerView.LayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        rvIngridients.setLayoutManager(ingredientsLayoutManager);
        rvIngridients.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvIngridients.getContext(), DividerItemDecoration.VERTICAL);
        rvIngridients.addItemDecoration(dividerItemDecoration);

        ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel().getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            List<Ingredient> updatedIngredients = getIngredients();

            if(updatedIngredients != null)
                adapter.updateData(updatedIngredients);
        });

        return view;
    }

    public List<Ingredient> getIngredients(){
        return Arrays.asList(((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel().getRecipeById(recipeId).getIngredients());
    }
}