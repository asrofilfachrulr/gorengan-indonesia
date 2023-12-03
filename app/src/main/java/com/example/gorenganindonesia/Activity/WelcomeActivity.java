package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gorenganindonesia.R;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    SharedPreferences sharedPreferences;
    boolean isOpened, isLogged;
    private ImageView imageViewWelcome;
    private int currentImageIndex = 0;
    private int[] imageResources = {R.drawable.gorengan1, R.drawable.gorengan2, R.drawable.gorengan3, R.drawable.gorengan4, R.drawable.gorengan5}; // Ganti dengan sumber gambar Anda


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
        int colorGorengan = ContextCompat.getColor(this, R.color.gorengan);
        int colorBlack = ContextCompat.getColor(this, R.color.black);

        TextView colorText2 = (TextView)findViewById(R.id.judulapp);
        SpannableString text2 = new SpannableString("GorenganIndonesia.");
        text2.setSpan(new ForegroundColorSpan(colorGorengan), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setSpan(new ForegroundColorSpan(colorBlack), 8, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        colorText2.setText(text2);
        colorText2.setText(text2, TextView.BufferType.SPANNABLE);

        imageViewWelcome = findViewById(R.id.iv_hero);
    }
    public void changeImage(View view) {
        if (currentImageIndex < imageResources.length - 1) {
            currentImageIndex++;
        } else {
            currentImageIndex = 0;
        }
        imageViewWelcome.setImageResource(imageResources[currentImageIndex]);
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