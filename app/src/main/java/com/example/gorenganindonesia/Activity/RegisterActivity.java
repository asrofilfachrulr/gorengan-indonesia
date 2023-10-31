package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.gorenganindonesia.API.AuthService;
import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.CustomToast;
import com.example.gorenganindonesia.Model.api.RegisterRequest;
import com.example.gorenganindonesia.Model.api.RegisterResponse;
import com.example.gorenganindonesia.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            v.getContext().startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            boolean isCompleted = true;

            EditText[] required = {etName, etUsername, etEmail, etPasswordRepeat, etPassword};
            for(EditText et: required) {
                if (et.getText().toString().isEmpty()) {
                    et.setError("Harus diisi");
                    isCompleted = false;
                } else {
                    et.setError(null);
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
                    String username, name, email, password;

                    username = etUsername.getText().toString();
                    name = etName.getText().toString();
                    email = etEmail.getText().toString();
                    password = etPassword.getText().toString();

                    RetrofitClient
                            .getInstance()
                            .create(AuthService.class)
                            .register(new RegisterRequest(username, email, name, password))
                            .enqueue(new Callback<RegisterResponse>() {
                                @Override
                                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                                    if(response.isSuccessful()){
                                        new CustomToast("Registrasi selesai, silahkan masuk", v).show();
                                        v.getContext().startActivity(intent);
                                    } else {
                                        int statusCode = response.code();
                                        try {
                                            String errorBody = response.errorBody().string();
                                            Log.e("Status code: ", String.valueOf(statusCode));
                                            Log.e("Error Response Body", errorBody);

                                            JSONObject errorJson = new JSONObject(errorBody);
                                            String errorText = errorJson.optString("error");

                                            new CustomToast("error: " + errorText, v).show();

                                        } catch (IOException | JSONException e) {
                                            Log.e("error", e.toString());
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                                    new CustomToast("Koneksi Error!", v).show();
                                }
                            });

                } else {
                    String msg = "Password harus sama";
                    etPassword.setError(msg);
                    etPasswordRepeat.setError(msg);
                    new CustomToast(msg, v).show();
                }
            } else {
                new CustomToast("Isi seluruh field!", v).show();
            }
        });

    }
}