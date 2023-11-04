package com.example.gorenganindonesia.Model;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;

public class GlobalModel extends Application {
    private AccountViewModel accountViewModel;
    private FavouriteViewModel favouriteViewModel;
    private RecipeViewModel recipeViewModel;
    @Override
    public void onCreate() {
        super.onCreate();

        favouriteViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(FavouriteViewModel.class);
        accountViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AccountViewModel.class);
        recipeViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(RecipeViewModel.class);
    }

    public FavouriteViewModel getFavouriteViewModel(){return favouriteViewModel;}

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public RecipeViewModel getRecipeViewModel() {
        return recipeViewModel;
    }
}
