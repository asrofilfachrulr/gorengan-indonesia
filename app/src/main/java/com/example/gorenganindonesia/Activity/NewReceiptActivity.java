package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.gorenganindonesia.R;

public class NewReceiptActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_receipt);

        ((ImageButton) findViewById(R.id.ib_back_new_receipt)).setOnClickListener(v -> {
           this.finish();
        });
    }
}