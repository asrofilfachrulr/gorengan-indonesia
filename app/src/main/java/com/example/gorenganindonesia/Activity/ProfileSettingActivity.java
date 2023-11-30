package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.databinding.ActivityProfileSettingBinding;

public class ProfileSettingActivity extends AppCompatActivity {
    ActivityProfileSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.ibBackProfileSetting.setOnClickListener(v -> {
            this.finish();
        });
    }
}