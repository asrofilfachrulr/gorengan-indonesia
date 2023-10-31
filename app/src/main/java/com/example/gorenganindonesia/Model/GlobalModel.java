package com.example.gorenganindonesia.Model;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.ViewModel.ReceiptViewModel;

public class GlobalModel extends Application {
    private AccountViewModel accountViewModel;
    private FavouriteViewModel favouriteViewModel;
    private ReceiptViewModel receiptViewModel;
    @Override
    public void onCreate() {
        super.onCreate();

        favouriteViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(FavouriteViewModel.class);
        accountViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AccountViewModel.class);
        receiptViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(ReceiptViewModel.class);
    }

    public FavouriteViewModel getFavouriteViewModel(){return favouriteViewModel;}

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public ReceiptViewModel getReceiptViewModel() {
        return receiptViewModel;
    }
}
