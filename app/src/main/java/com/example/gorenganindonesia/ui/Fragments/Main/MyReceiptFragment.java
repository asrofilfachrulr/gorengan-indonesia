package com.example.gorenganindonesia.ui.Fragments.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.MyReceiptViewModel;
import com.example.gorenganindonesia.databinding.FragmentMyReceiptBinding;

public class MyReceiptFragment extends Fragment {

    private FragmentMyReceiptBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyReceiptViewModel myReceiptViewModel =
                new ViewModelProvider(this).get(MyReceiptViewModel.class);

        binding = FragmentMyReceiptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyReceipt;
        myReceiptViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}