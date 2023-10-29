package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyReceiptViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyReceiptViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Receipt fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}