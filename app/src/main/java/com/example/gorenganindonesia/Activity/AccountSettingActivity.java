package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.InputMethodHelper;
import com.example.gorenganindonesia.databinding.ActivityAccountSettingBinding;

public class AccountSettingActivity extends AppCompatActivity {
    ActivityAccountSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.ibBackAccountSetting.setOnClickListener(v -> {
            this.finish();
        });

        binding.btnUserProfileAccountSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileSettingActivity.class);
            this.startActivity(intent);
        });

        binding.btnUserPasswordAccountSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, PasswordSettingActivity.class);
            this.startActivity(intent);
        });
    }
}