package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.databinding.ActivityPasswordSettingBinding;

public class PasswordSettingActivity extends AppCompatActivity {
    ActivityPasswordSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPasswordSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ibBackPasswordSetting.setOnClickListener(v -> {
            this.finish();
        });
    }
}