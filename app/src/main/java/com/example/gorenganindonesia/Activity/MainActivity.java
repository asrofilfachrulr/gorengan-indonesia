package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.ui.Adapters.ReceiptAdapter;
import com.example.gorenganindonesia.Model.data.Receipt.ReceiptData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Receipt> receipts;
    ArrayList<String> categories;

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;
    ReceiptAdapter receiptAdapter;

    LinearLayout llParentContent;

    EditText etSearch;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = CategoryData.generate();
        receipts = ReceiptData.generate();

        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.receipt_spacing);
        rvReceipt = (RecyclerView) findViewById(R.id.rv_receipt);
        receiptAdapter = new ReceiptAdapter(receipts, rvReceipt);
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


        llParentContent = (LinearLayout) findViewById(R.id.ll_parent_content);
        etSearch = (EditText) findViewById(R.id.et_search);

        llParentContent.setOnTouchListener((v, event) -> {
            etSearch.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                receiptAdapter.applyFilterTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ((Button) findViewById(R.id.btn_debug_mainactivity2)).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });

    }

}