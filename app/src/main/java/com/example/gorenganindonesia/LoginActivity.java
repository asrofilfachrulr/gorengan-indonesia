package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText etIdentifier, etPassword;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etIdentifier = (EditText) findViewById(R.id.et_identifier);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                boolean isCompleted = true;

                EditText[] required = {etIdentifier, etPassword};
                for(EditText et: required) {
                    if (et.getText().toString().isEmpty()) {
                        et.setError("Harus diisi");
                        isCompleted = false;
                    }
                }

                if(isCompleted){
                    // TODO: Request POST Login User Using Retrofit
                    sharedPreferences
                            .edit()
                            .putBoolean("isLogged", true)
                            .apply();
                    v.getContext().startActivity(intent);
                }
                else {
                    new CustomToast("Isi seluruh field!", v).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}