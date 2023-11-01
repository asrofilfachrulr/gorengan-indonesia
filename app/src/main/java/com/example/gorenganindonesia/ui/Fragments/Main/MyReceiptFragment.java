package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gorenganindonesia.Activity.NewReceiptActivity;
import com.example.gorenganindonesia.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.ReceiptViewModel;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.databinding.FragmentMyReceiptBinding;
import com.example.gorenganindonesia.ui.Adapters.MyReceiptAdapter;

import java.util.List;

public class MyReceiptFragment extends Fragment {

    private FragmentMyReceiptBinding binding;

    List<Receipt> myRecipes;

    ReceiptViewModel receiptViewModel;
    AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyReceiptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        receiptViewModel = ((GlobalModel) getContext().getApplicationContext()).getReceiptViewModel();
        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        String username = accountViewModel.getUsername();

        myRecipes = receiptViewModel.getMyRecipes(username);

        MyReceiptAdapter adapter = new MyReceiptAdapter(myRecipes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvMyRecipes.getContext(), DividerItemDecoration.VERTICAL);

        binding.rvMyRecipes.setAdapter(adapter);
        binding.rvMyRecipes.addItemDecoration(dividerItemDecoration);
        binding.rvMyRecipes.setLayoutManager(linearLayoutManager);

        binding.ibAddMyReceipt.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), NewReceiptActivity.class));
        });

        receiptViewModel.getAllRecipes().observe(getViewLifecycleOwner(), receipts -> {
            List<Receipt> updatedMyRecipes = receiptViewModel.getMyRecipes(username);

            adapter.updateData(updatedMyRecipes);

            if(adapter.getItemCount() > 0) {
                binding.llEmptyMyReceiptSign.setVisibility(View.GONE);
                binding.svListMyReceipt.setVisibility(View.VISIBLE);
            } else {
                binding.llEmptyMyReceiptSign.setVisibility(View.VISIBLE);
                binding.svListMyReceipt.setVisibility(View.GONE);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}