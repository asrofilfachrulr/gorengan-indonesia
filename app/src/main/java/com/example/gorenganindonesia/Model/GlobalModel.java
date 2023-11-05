package com.example.gorenganindonesia.Model;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Util.SessionManager;

public class GlobalModel extends Application {
    private AccountViewModel accountViewModel;
    private FavouriteViewModel favouriteViewModel;
    private RecipeViewModel recipeViewModel;
    private SessionManager sessionManager;
    @Override
    public void onCreate() {
        super.onCreate();

        favouriteViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(FavouriteViewModel.class);
        accountViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AccountViewModel.class);
        recipeViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(RecipeViewModel.class);
        sessionManager = new SessionManager(getSharedPreferences("gorenganindonesia", Context.MODE_PRIVATE));
    }

    public FavouriteViewModel getFavouriteViewModel(){return favouriteViewModel;}

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public RecipeViewModel getRecipeViewModel() {
        return recipeViewModel;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
