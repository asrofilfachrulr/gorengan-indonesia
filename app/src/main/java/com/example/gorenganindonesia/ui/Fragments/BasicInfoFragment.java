package com.example.gorenganindonesia.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.databinding.FragmentBasicInfoBinding;

import java.util.Timer;
import java.util.TimerTask;

public class BasicInfoFragment extends DialogFragment {
    FragmentBasicInfoBinding binding;
    String title;
    String content;
    int symbolImg;
    int hideAfterMs;

    public static final int DEFAULT_TIMER = 5000;
    public static final int NO_TIMER = 0;

    public static final String EMPTY_CONTENT = "";

    public static final int DEFAULT_IMG = 0;

    public BasicInfoFragment() {
        // Required empty public constructor
    }

    public BasicInfoFragment(String title, String content){
        this(title, content, NO_TIMER, DEFAULT_IMG);
    }

    public BasicInfoFragment(String title, String content, int hideAfterMs, int symbolImg){
        this.title = title;
        this.content = content;
        this.hideAfterMs = hideAfterMs;
        this.symbolImg = symbolImg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasicInfoBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundResource(android.R.color.transparent);

        if(symbolImg != DEFAULT_IMG)
            binding.ivBasicInfoFragment.setImageResource(symbolImg);

        binding.tvTitleBasicInfoFragment.setText(title);

        if(!binding.tvContentBasicInfoFragment.equals(EMPTY_CONTENT)){
            binding.tvContentBasicInfoFragment.setText(content);
            binding.tvContentBasicInfoFragment.setVisibility(View.VISIBLE);
        }

        if(hideAfterMs != NO_TIMER){
            scheduleAutoClose();
        }
    }

    public void finishDialog(){ dismiss(); }

    public void scheduleAutoClose(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finishDialog();
            }
        }, hideAfterMs);
    }
}