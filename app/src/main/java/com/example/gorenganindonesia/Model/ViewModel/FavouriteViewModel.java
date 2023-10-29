package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Receipt.Receipt;

import java.util.ArrayList;
import java.util.List;

public class FavouriteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Receipt>> mFavourites;

    public FavouriteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Favourite fragment");

        mFavourites = new MutableLiveData<>();
        mFavourites.setValue(new ArrayList<>());
    }
    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Receipt>> getFavourites() {
        return mFavourites;
    }
    
    public void pushFavourite(Receipt receipt){
        List<Receipt> favourites = mFavourites.getValue();

        favourites.add(receipt);
        mFavourites.setValue(favourites);
    }
}