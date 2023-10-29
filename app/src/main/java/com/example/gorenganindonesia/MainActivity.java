package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.gorenganindonesia.Model.ui.Category.CategoryAdapter;
import com.example.gorenganindonesia.Model.ui.Category.CategoryData;
import com.example.gorenganindonesia.Model.ui.Receipt.Receipt;
import com.example.gorenganindonesia.Model.ui.Receipt.ReceiptAdapter;
import com.example.gorenganindonesia.Model.ui.Receipt.ReceiptData;

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
    }

}