package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Activity.MainActivity;
import com.example.gorenganindonesia.Activity.MainActivity2;
import com.example.gorenganindonesia.Model.ViewModel.HomeViewModel;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.Model.data.Receipt.ReceiptData;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.databinding.FragmentHomeBinding;
import com.example.gorenganindonesia.ui.Adapters.CategoryAdapter;
import com.example.gorenganindonesia.ui.Adapters.ReceiptAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<Receipt> receipts;
    ArrayList<String> categories;

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;
    ReceiptAdapter receiptAdapter;

    LinearLayout llParentContent;

    EditText etSearch;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        categories = CategoryData.generate();
        receipts = ReceiptData.generate();

        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.receipt_spacing);
        rvReceipt = (RecyclerView) binding.rvReceipt;
        receiptAdapter = new ReceiptAdapter(receipts, rvReceipt);
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(getContext());
        rvReceipt.setLayoutManager(receiptLayoutManager);
        rvReceipt.setAdapter(receiptAdapter);
        rvReceipt.addItemDecoration(new RecyclerViewItemSpacing(getContext(), receiptSpacing));

        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.category_spacing);
        rvCategory = (RecyclerView) binding.rvCategory;
        categoryAdapter = new CategoryAdapter(categories, receiptAdapter);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(categoryLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new RecyclerViewItemSpacing(getContext(), categorySpacing));


        llParentContent = (LinearLayout) binding.llParentContent;
        etSearch = (EditText) binding.etSearch;

        llParentContent.setOnTouchListener((v, event) -> {
            etSearch.clearFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}