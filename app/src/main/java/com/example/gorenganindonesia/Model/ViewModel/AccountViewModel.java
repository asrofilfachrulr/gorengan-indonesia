package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Account.Account;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<Account> mAccount;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Account fragment");

        mAccount = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setAccount(Account account){
        mAccount.setValue(account);
    }

    public LiveData<Account> getAccount(){
        return mAccount;
    }

    public String getName(){
        return mAccount.getValue().getName();
    }

    public String getUsername(){
        return mAccount.getValue().getUsername();
    }

    public String getEmail() { return mAccount.getValue().getEmail();}
}