package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText etEmail, etUsername, etPassword, etName, etPasswordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordRepeat = (EditText) findViewById(R.id.et_password_repeat);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                boolean isCompleted = true;

                EditText[] required = {etName, etUsername, etEmail, etPasswordRepeat, etPassword};
                for(EditText et: required) {
                    if (et.getText().toString().isEmpty()) {
                        et.setError("Harus diisi");
                        isCompleted = false;
                    }
                }

                if(isCompleted) {
                    if(etPassword
                            .getText()
                            .toString()
                            .equals(etPasswordRepeat
                                    .getText()
                                    .toString())
                    ){
                        // TODO: Request POST New User Using Retrofit
                        new CustomToast("Registrasi selesai, silahkan masuk", v).show();
                        v.getContext().startActivity(intent);
                    } else {
                        String msg = "Password harus sama";
                        etPassword.setError(msg);
                        etPasswordRepeat.setError(msg);
                        new CustomToast(msg, v).show();
                    }
                } else {
                    new CustomToast("Isi seluruh field!", v).show();
                }
            }
        });

    }
}