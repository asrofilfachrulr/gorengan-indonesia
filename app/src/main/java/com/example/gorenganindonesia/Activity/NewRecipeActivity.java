package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.databinding.ActivityNewRecipeBinding;
import com.example.gorenganindonesia.ui.Adapters.NewIngredientAdapter;
import com.example.gorenganindonesia.ui.Adapters.NewStepAdapter;

public class NewRecipeActivity extends AppCompatActivity {
    RecyclerView rvIngredient, rvStep;

    private ActivityNewRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        ((ImageButton) findViewById(R.id.ib_back_new_receipt)).setOnClickListener(v -> {
           this.finish();
        });

        binding = ActivityNewRecipeBinding.inflate(getLayoutInflater());

        rvIngredient = (RecyclerView) findViewById(R.id.rv_add_ingredient_new_recipe);
        LinearLayoutManager ingredientLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        NewIngredientAdapter ingredientAdapter = new NewIngredientAdapter();
        rvIngredient.setLayoutManager(ingredientLM);
        rvIngredient.setAdapter(ingredientAdapter);

        rvStep = (RecyclerView) findViewById(R.id.rv_add_step_new_recipe);
        LinearLayoutManager stepLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        NewStepAdapter stepAdapter = new NewStepAdapter();
        rvStep.setLayoutManager(stepLM);
        rvStep.setAdapter(stepAdapter);
    }
}