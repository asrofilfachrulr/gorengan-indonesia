package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gorenganindonesia.API.AuthService;
import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.api.LoginRequest;
import com.example.gorenganindonesia.Model.api.LoginResponse;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText etIdentifier, etPassword;

//    LinearLayout llRootLogin;
//    ProgressBar progressBar;

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

        // TODO: implement progress bar on login request loading
//        llRootLogin = (LinearLayout) findViewById(R.id.ll_root_login);
//        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
//
//        llRootLogin.addView(progressBar, params);
//        progressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            boolean isCompleted = true;

            EditText[] required = {etIdentifier, etPassword};
            for (EditText et : required) {
                if (et.getText().toString().isEmpty()) {
                    et.setError("Harus diisi");
                    isCompleted = false;
                } else {
                    et.setError(null);
                }
            }

            if (isCompleted) {
                String identifier = etIdentifier.getText().toString();
                String password = etPassword.getText().toString();

//                    progressBar.setVisibility(View.VISIBLE);

                RetrofitClient
                        .getInstance()
                        .create(AuthService.class)
                        .login(new LoginRequest(identifier, password))
                        .enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                int statusCode = response.code();
                                Log.e("Response Account", response.body().getAccount().toString());

                                if (response.isSuccessful()) {
                                    new CustomToast("Login sukses!", v, false).show();

                                    sharedPreferences
                                            .edit()
                                            .putBoolean("isLogged", true)
                                            .putString("login_token", response.body().getToken())
                                            .apply();

                                    com.example.gorenganindonesia.Model.api.Account accountJson = response.body().getAccount();

                                    Account account = new Account(
                                            accountJson.getId(),
                                            accountJson.getName(),
                                            accountJson.getUsername(),
                                            accountJson.getImageUrl(),
                                            accountJson.getEmail()
                                    );

                                    AccountViewModel accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
                                    accountViewModel.setAccount(account);

                                    System.out.println("Account: " + accountViewModel.getAccount().getValue().toString());

                                    sharedPreferences.edit()
                                            .putString("account_id", account.getId())
                                            .putString("account_name", account.getName())
                                            .putString("account_username", account.getUsername())
                                            .putString("account_image_url", account.getImageUrl())
                                            .putString("account_email", account.getEmail())
                                            .apply();
                                    v.getContext().startActivity(intent);
                                } else {
                                    try {
                                        String errorBody = response.errorBody().string();
                                        Log.e("Status code: ", String.valueOf(statusCode));
                                        Log.e("Error Response Body", errorBody);

                                        JSONObject errorJson = new JSONObject(errorBody);
                                        String errorMessage = errorJson.optString("message");
                                        String errorText = errorJson.optString("error");

                                        switch (statusCode) {
                                            case 401:
                                                etPassword.setError(errorMessage);
                                            case 404:
                                                new CustomToast(errorMessage, v).show();
                                                etIdentifier.setError(errorMessage);
                                                break;
                                            case 500:
                                                new CustomToast(errorText, v).show();
                                        }
                                    } catch (IOException | JSONException e) {
                                        Log.e("error", e.toString());
                                        e.printStackTrace();
                                    }
                                }
//                                    progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                System.out.println("LOGIN FAILURE: " + t.getMessage());
                                new CustomToast("Koneksi Error!", v).show();
//                                    progressBar.setVisibility(View.GONE);
                            }
                        });
            } else {
                new CustomToast("Isi seluruh field!", v).show();
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