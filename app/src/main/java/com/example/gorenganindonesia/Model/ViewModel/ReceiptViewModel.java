package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.Model.data.Receipt.ReceiptData;

import java.util.ArrayList;
import java.util.List;

public class ReceiptViewModel extends ViewModel {
    private final MutableLiveData<List<Receipt>> mRecipes;

    public ReceiptViewModel() {
        this.mRecipes = new MutableLiveData<>();

        List<Receipt> receipts = ReceiptData.generate();
        this.mRecipes.setValue(receipts);
    }

    public List<Receipt> getMyRecipes(String myName){
        List<Receipt> myRecipes = new ArrayList<>();

        for(Receipt receipt: mRecipes.getValue()){
            if(receipt.getAuthorUsername().contains(myName))
                myRecipes.add(receipt);
        }
        return myRecipes;
    }

    public MutableLiveData<List<Receipt>> getAllRecipes(){
        return mRecipes;
    }

    // currently delete means change the author to admin
    public void deleteMyReceipt(Receipt receipt, String myName){
        List<Receipt> receipts = new ArrayList<>(mRecipes.getValue());

        for(int i = 0; i < receipts.size(); i++){
            Receipt r = receipts.get(i);
            if(r.getAuthorUsername().equals(myName) && r.getId().equals(receipt.getId())){
                System.out.println("Removed " + r.getTitle() + " from mReceipts");
                receipts.remove(i);
                r.setAuthorUsername("admin");
                receipts.set(i, r);
            }
        }

        mRecipes.setValue(receipts);
    }

    public void addReceipt(Receipt receipt){
        List<Receipt> receipts = new ArrayList<>(mRecipes.getValue());
        receipts.add(receipt);

        mRecipes.setValue(receipts);
    }
}
