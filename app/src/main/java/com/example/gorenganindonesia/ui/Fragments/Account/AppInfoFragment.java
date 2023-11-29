package com.example.gorenganindonesia.ui.Fragments.Account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gorenganindonesia.R;

public class AppInfoFragment extends DialogFragment {
    Button btnClose;
    TextView tvLinkSourceCode;

    public AppInfoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundResource(android.R.color.transparent);

        btnClose = (Button) view.findViewById(R.id.btn_close_app_info_account);
        tvLinkSourceCode = (TextView) view.findViewById(R.id.tv_link_sc_app_info_account);

        btnClose.setOnClickListener(v -> { closeDialog(); });
        tvLinkSourceCode.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.link_repo)));

            requireActivity().startActivity(intent);
        });
    }

    public void closeDialog() { dismiss(); }
}
