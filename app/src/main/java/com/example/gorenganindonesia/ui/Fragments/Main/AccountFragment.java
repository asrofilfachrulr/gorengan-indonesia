package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.FragmentAccountBinding;
import com.example.gorenganindonesia.ui.Fragments.Account.AppInfoFragment;
import com.example.gorenganindonesia.ui.Fragments.Account.TermsOfServiceFragment;

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

        binding.ibEditImageAccount.setOnClickListener(v -> {
            ToastUseCase.showInDevelopment(root);
        });

        binding.btnAccountAccount.setOnClickListener(v -> {
            ToastUseCase.showInDevelopment(root);
        });

        binding.btnOfflineRecipeAccount.setOnClickListener(v -> {
            ToastUseCase.showInDevelopment(root);
        });

        binding.btnAppInfoAccount.setOnClickListener(v -> {
            AppInfoFragment appInfoFragment = new AppInfoFragment();
            appInfoFragment.show(requireActivity().getSupportFragmentManager(), "APPINFO_FRAGMENT");
        });

        binding.btnTermsOfServiceAccount.setOnClickListener(v -> {
            TermsOfServiceFragment tosFragment = new TermsOfServiceFragment();
            tosFragment.show(requireActivity().getSupportFragmentManager(), "TOS_FRAGMENT");
        });

        binding.btnCustomerServiceAccount.setOnClickListener(v -> {
            String phoneNumber = getString(R.string.cs_phone);
            String url = "https://api.whatsapp.com/send?phone="+ phoneNumber;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            new CustomToast("Mengalihkan ke WhatsApp", root, false).show();
            startActivity(i);
        });

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