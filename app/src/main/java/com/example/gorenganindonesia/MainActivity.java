package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Receipt> receipts;
    ArrayList<String> categories;

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;
    ReceiptAdapter receiptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = CategoryData.generate();
        receipts = ReceiptData.generate();

        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.receipt_spacing);
        rvReceipt = (RecyclerView) findViewById(R.id.rv_receipt);
        receiptAdapter = new ReceiptAdapter(receipts);
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvReceipt.setLayoutManager(receiptLayoutManager);
        rvReceipt.setAdapter(receiptAdapter);
        rvReceipt.addItemDecoration(new RecyclerViewItemSpacing(this, receiptSpacing));

        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.category_spacing);
        rvCategory = (RecyclerView) findViewById(R.id.rv_category);
        categoryAdapter = new CategoryAdapter(categories, receiptAdapter);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(categoryLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new RecyclerViewItemSpacing(this, categorySpacing));
    }

}