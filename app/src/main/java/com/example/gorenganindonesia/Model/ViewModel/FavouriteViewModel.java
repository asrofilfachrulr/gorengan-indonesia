package com.example.gorenganindonesia.Model.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FavouriteViewModel extends ViewModel {

    private final MutableLiveData<List<Recipe>> mFavourites;

    public FavouriteViewModel() {
        mFavourites = new MutableLiveData<>();
        mFavourites.setValue(new ArrayList<>());
    }

    public LiveData<List<Recipe>> getFavourites() {
        return mFavourites;
    }

    public void setFavourites(List<Recipe> recipes){
        mFavourites.setValue(recipes);
    }

    public void pushFavourite(Recipe recipe){
        List<Recipe> favourites = mFavourites.getValue();

        favourites.add(recipe);
        mFavourites.setValue(favourites);
    }

    public void removeFavourite(int pos){
        List<Recipe> favourites = mFavourites.getValue();

        try{
            favourites.remove(pos);
        } catch (Exception e){
            Log.e("Favourite Model Error", e.getMessage());
        }

        mFavourites.setValue(favourites);
    }

    public void removeFavourite(Recipe recipe){
        List<Recipe> favourites = mFavourites.getValue();

        try{
            for(int i = 0; i < favourites.size(); i++){
                if(favourites.get(i).getId().equals(recipe.getId())){
                    favourites.remove(i);
                    break;
                }
            }
        } catch (Exception e){
            Log.e("Favourite Model Error", e.getMessage());
        }

        mFavourites.setValue(favourites);
    }


    public boolean ifFavouriteExist(Recipe recipe){
        List<Recipe> favourites = mFavourites.getValue();
        for(Recipe favourite: mFavourites.getValue()){
            if(favourite.getId().equals(recipe.getId())) return true;
        }

        return false;
    }

}