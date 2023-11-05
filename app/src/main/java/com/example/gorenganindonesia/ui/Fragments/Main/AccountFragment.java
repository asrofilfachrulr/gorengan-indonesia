package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gorenganindonesia.Activity.LoginActivity;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Util.SessionManager;
import com.example.gorenganindonesia.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        binding.tvNameAccount.setText(
                accountViewModel.getName()
        );

        binding.tvEmailAccount.setText(
                accountViewModel.getEmail()
        );

        binding.btnLogoutAccount.setOnClickListener(v -> {
            ((GlobalModel) getContext().getApplicationContext())
                    .getSessionManager()
                    .logout(getContext(), getActivity(), "Logout berhasil");
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}