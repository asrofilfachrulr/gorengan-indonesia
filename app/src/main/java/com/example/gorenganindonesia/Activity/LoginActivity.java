package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gorenganindonesia.API.Services.AuthService;
import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.Model.api.AccountResponse;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Model.api.Login.LoginRequest;
import com.example.gorenganindonesia.Model.api.Login.LoginResponse;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvLoading;
    Button btnLogin, btnRegister;
    EditText etIdentifier, etPassword;

    LinearLayout llRootLoadingLogin;

//    LinearLayout llRootLogin;
//    ProgressBar progressBar;

    SharedPreferences sharedPreferences;

    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        thisActivity = this;

        Intent intent = getIntent();
        String toastMsg = intent.getStringExtra("TOAST_MSG");
        if(toastMsg != null){
            if(!toastMsg.isEmpty())
                new CustomToast(
                        toastMsg,
                        LayoutInflater.from(this).inflate(R.layout.activity_login, null)
                ).show();
        }

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);

        tvLoading = (TextView) findViewById(R.id.tv_root_loading_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etIdentifier = (EditText) findViewById(R.id.et_identifier);
        etPassword = (EditText) findViewById(R.id.et_password);

        llRootLoadingLogin = (LinearLayout) findViewById(R.id.ll_root_loading_login);

        btnLogin.setOnClickListener(v -> {
            etIdentifier.clearFocus();
            etPassword.clearFocus();
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

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
                llRootLoadingLogin.setVisibility(View.VISIBLE);

                String identifier = etIdentifier.getText().toString();
                String password = etPassword.getText().toString();

                RetrofitClient
                        .getInstance()
                        .create(AuthService.class)
                        .login(new LoginRequest(identifier, password))
                        .enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                int statusCode = response.code();

                                if (response.isSuccessful()) {
                                    AccountResponse accountResponseJson = response.body().getAccount();

                                    Account account = new Account(
                                            accountResponseJson.getId(),
                                            accountResponseJson.getName(),
                                            accountResponseJson.getUsername(),
                                            accountResponseJson.getImageUrl(),
                                            accountResponseJson.getImagePath(),
                                            accountResponseJson.getEmail()
                                    );

                                    AccountViewModel accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
                                    accountViewModel.setAccount(account);

                                    System.out.println("Account: " + accountViewModel.getLiveAccount().getValue().toString());

                                    llRootLoadingLogin.setVisibility(View.INVISIBLE);

                                    ((GlobalModel) getApplication())
                                            .getSessionManager()
                                            .login(
                                                    thisActivity,
                                                    thisActivity,
                                                    response.body().getToken(),
                                                    account,
                                                    "login sukses!"
                                            );
                                } else {
                                    llRootLoadingLogin.setVisibility(View.INVISIBLE);
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
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                System.out.println("LOGIN FAILURE: " + t.getMessage());
                                new CustomToast("Koneksi Error!", v).show();
                                llRootLoadingLogin.setVisibility(View.INVISIBLE);
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