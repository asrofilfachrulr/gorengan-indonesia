package com.example.gorenganindonesia.ui.Fragments.Account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gorenganindonesia.R;

public class TermsOfServiceFragment extends DialogFragment {

    Button btnClose;

    public TermsOfServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_terms_of_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundResource(android.R.color.transparent);

        btnClose = (Button) view.findViewById(R.id.btn_close_term_of_service_account);

        btnClose.setOnClickListener(v -> closeDialog());
    }

    public void closeDialog(){ dismiss(); }
}