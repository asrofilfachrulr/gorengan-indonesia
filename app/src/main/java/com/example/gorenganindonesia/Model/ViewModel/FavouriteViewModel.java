package com.example.gorenganindonesia.Model.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Receipt.Receipt;

import java.util.ArrayList;
import java.util.List;

public class FavouriteViewModel extends ViewModel {

    private final MutableLiveData<List<Receipt>> mFavourites;

    public FavouriteViewModel() {
        mFavourites = new MutableLiveData<>();
        mFavourites.setValue(new ArrayList<>());
    }

    public LiveData<List<Receipt>> getFavourites() {
        return mFavourites;
    }
    
    public void pushFavourite(Receipt receipt){
        List<Receipt> favourites = mFavourites.getValue();

        favourites.add(receipt);
        mFavourites.setValue(favourites);
    }

    public void removeFavourite(int pos){
        List<Receipt> favourites = mFavourites.getValue();

        try{
            favourites.remove(pos);
        } catch (Exception e){
            Log.e("Favourite Model Error", e.getMessage());
        }

        mFavourites.setValue(favourites);
    }

    public void removeFavourite(Receipt receipt){
        List<Receipt> favourites = mFavourites.getValue();

        try{
            for(int i = 0; i < favourites.size(); i++){
                if(favourites.get(i).getId().equals(receipt.getId())){
                    favourites.remove(i);
                    break;
                }
            }
        } catch (Exception e){
            Log.e("Favourite Model Error", e.getMessage());
        }

        mFavourites.setValue(favourites);
    }


    public boolean ifFavouriteExist(Receipt receipt){
        List<Receipt> favourites = mFavourites.getValue();
        for(Receipt favourite: mFavourites.getValue()){
            if(favourite.getId().equals(receipt.getId())) return true;
        }

        return false;
    }

}