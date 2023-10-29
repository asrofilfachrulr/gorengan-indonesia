package com.example.gorenganindonesia.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.gorenganindonesia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation
                .findNavController(this, R.id.nav_host_fragment_activity_main2);

        NavigationUI.setupWithNavController(navView, navController);
    }
}
