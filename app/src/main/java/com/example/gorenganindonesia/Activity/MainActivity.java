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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation
                .findNavController(this, R.id.nav_host_fragment_activity_main2);

        NavigationUI.setupWithNavController(navView, navController);

        navView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // This method will be called once the layout has been populated, and you can obtain its height.
                int height = navView.getHeight();

                // give empty space at the end of recycler view of receipt list so last
                // item wont be covered by bottom navigation bar
                View vw = (View) findViewById(R.id.vw_empty_space);
                ViewGroup.LayoutParams param = vw.getLayoutParams();
                param.height = height;

                vw.setLayoutParams(param);

                // Remove the listener to avoid multiple calls
                navView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }
}
