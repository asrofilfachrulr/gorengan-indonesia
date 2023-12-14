package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.databinding.ActivityCongratsBinding;

public class CongratsActivity extends AppCompatActivity {
    ActivityCongratsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCongratsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnStartCongrats.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        });
    }
}