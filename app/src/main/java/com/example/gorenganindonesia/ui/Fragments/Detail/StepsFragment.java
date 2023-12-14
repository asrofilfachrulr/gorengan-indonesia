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
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.ui.Adapters.StepAdapter;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StepsFragment extends Fragment {
    RecyclerView rvSteps;
    List<String> steps;
    String recipeId;

    int index;

    public StepsFragment() {  }

    public StepsFragment(String recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        rvSteps = (RecyclerView) view.findViewById(R.id.rv_steps);

        steps = getSteps();

        if(steps == null)
            steps = new ArrayList<>();

        StepAdapter adapter = new StepAdapter(steps);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSteps.setLayoutManager(layoutManager);
        rvSteps.setAdapter(adapter);
        rvSteps.addItemDecoration(new DividerItemDecoration(rvSteps.getContext(), DividerItemDecoration.VERTICAL));

        ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel().getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            List<String> updatedSteps = getSteps();

            if(updatedSteps != null)
                adapter.updateData(updatedSteps);
        });

        return view;
    }

    private List<String> getSteps(){
        String[] steps = ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel().getRecipeById(recipeId).getSteps();

        return steps == null ? new ArrayList<>() : Arrays.asList(steps);
    }
}