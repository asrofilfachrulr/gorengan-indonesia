package com.example.gorenganindonesia.Model;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;

public class GlobalModel extends Application {
    private FavouriteViewModel favouriteViewModel;
    @Override
    public void onCreate() {
        super.onCreate();

        favouriteViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(FavouriteViewModel.class);
    }

    public FavouriteViewModel getFavouriteViewModel(){return favouriteViewModel;}
}
