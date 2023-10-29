package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    SharedPreferences sharedPreferences;
    boolean isOpened, isLogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);
        isLogged = sharedPreferences.getBoolean("isLogged", false);
        isOpened = sharedPreferences.getBoolean("isOpened", false);

        System.out.println("isOpened: " + Boolean.toString(isOpened));
        System.out.println("isLogged: " + Boolean.toString(isLogged));

        if(!isOpened && !isLogged) {

            sharedPreferences
                    .edit()
                    .putBoolean("isOpened", true)
                    .apply();

            btnLogin = (Button) findViewById(R.id.btn_login);
            btnRegister = (Button) findViewById(R.id.btn_register);

            btnLogin.setOnClickListener(v -> toLoginPage(false));

            btnRegister.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                v.getContext().startActivity(intent);
            });
        } else {
            if(isLogged) {
                toMainPage();
            } else {
                toLoginPage(true);
            }
        }
    }

    public void toLoginPage(boolean dontBack){
        Intent intent = new Intent(this, LoginActivity.class);
        if(dontBack){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        this.startActivity(intent);
    }

    public void toMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        this.startActivity(intent);
    }
}