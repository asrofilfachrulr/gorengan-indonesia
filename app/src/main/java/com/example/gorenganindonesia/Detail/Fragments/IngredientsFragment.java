package com.example.gorenganindonesia.Detail.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gorenganindonesia.Model.ui.Ingredients.Ingredients;
import com.example.gorenganindonesia.Model.ui.Ingredients.IngredientsAdapter;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsFragment extends Fragment {
    RecyclerView rvIngridients;
    List<Ingredients> ingredients;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    public IngredientsFragment(Ingredients[] ingredients) {
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        rvIngridients = (RecyclerView) view.findViewById(R.id.rv_ingredients);
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        RecyclerView.LayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        rvIngridients.setLayoutManager(ingredientsLayoutManager);
        rvIngridients.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvIngridients.getContext(), DividerItemDecoration.VERTICAL);
        rvIngridients.addItemDecoration(dividerItemDecoration);

        return view;
    }
}