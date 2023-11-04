package com.example.gorenganindonesia.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.ViewModel.RecipeViewModel;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    AccountViewModel accountViewModel;
    RecipeViewModel recipeViewModel;
    SharedPreferences sharedPreferences;

    Button btnDebugClearData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
        recipeViewModel = ((GlobalModel) getApplication()).getRecipeViewModel();


        sharedPreferences = getSharedPreferences("gorenganindonesia", Context.MODE_PRIVATE);

        boolean isLogged = sharedPreferences.getBoolean("isLogged", false);

        if(isLogged){
            Account account = new Account(
                    sharedPreferences.getString("account_id", "undefined"),
                    sharedPreferences.getString("account_name", "undefined"),
                    sharedPreferences.getString("account_username", "undefined"),
                    sharedPreferences.getString("account_image_url", "undefined"),
                    sharedPreferences.getString("account_email", "undefined")
            );

            accountViewModel.setAccount(account);

            System.out.println("Account: " + accountViewModel.getAccount().getValue().toString());
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

        setContentView(R.layout.activity_main);

        btnDebugClearData = (Button) findViewById(R.id.btn_debug_clear_data);

        btnDebugClearData.setOnClickListener(v -> {
            sharedPreferences.edit().clear().apply();
            deleteCache(this);
            clearAppData(this);
        });

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
    public static void deleteCache(Context context) {
        try {
            File cacheDirectory = context.getCacheDir();
            File[] cacheFiles = cacheDirectory.listFiles();
            if (cacheFiles != null) {
                for (File file : cacheFiles) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearAppData(Context context) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear " + context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restartApp() {
        Intent intent = new Intent(this, MainActivity.class); // Replace YourMainActivity with your app's main activity class
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }
}
