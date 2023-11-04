package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Recipe.RecipeData;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {
    private final MutableLiveData<List<Recipe>> mRecipes;

    public RecipeViewModel() {
        this.mRecipes = new MutableLiveData<>();

        List<Recipe> recipes = RecipeData.generate();
        this.mRecipes.setValue(recipes);
    }

    public List<Recipe> getMyRecipes(String myName){
        List<Recipe> myRecipes = new ArrayList<>();

        for(Recipe recipe : mRecipes.getValue()){
            if(recipe.getAuthorUsername().contains(myName))
                myRecipes.add(recipe);
        }
        return myRecipes;
    }

    public MutableLiveData<List<Recipe>> getAllRecipes(){
        return mRecipes;
    }

    // currently delete means change the author to admin
    public void deleteMyReceipt(Recipe recipe, String myName){
        List<Recipe> recipes = mRecipes.getValue();

        for(int i = 0; i < recipes.size(); i++){
            Recipe r = recipes.get(i);
            if(r.getAuthorUsername().equals(myName) && r.getId().equals(recipe.getId())){
                System.out.println("Removed " + r.getTitle() + " from mReceipts");
                recipes.remove(i);
                r.setAuthorUsername("admin");
                recipes.set(i, r);
            }
        }

        mRecipes.setValue(recipes);
    }

    public void addReceipt(Recipe recipe){
        List<Recipe> recipes = mRecipes.getValue();
        recipes.add(recipe);

        mRecipes.setValue(recipes);
    }
}
