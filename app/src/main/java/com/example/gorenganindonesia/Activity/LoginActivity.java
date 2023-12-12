package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
    Button btnLogin, btnRegister;
    EditText etIdentifier, etPassword;

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


        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etIdentifier = (EditText) findViewById(R.id.et_identifier);
        etPassword = (EditText) findViewById(R.id.et_password);

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
                String identifier = etIdentifier.getText().toString();
                String password = etPassword.getText().toString();

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Memuat Akun Anda...");
                progressDialog.show();

                RetrofitClient
                        .getInstance()
                        .create(AuthService.class)
                        .login(new LoginRequest(identifier, password))
                        .enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                progressDialog.dismiss();
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
                                                etIdentifier.setError(errorMessage);
                                                AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                                                builder
                                                        .setMessage("Error: " + errorMessage)
                                                        .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                                                        .show();
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
                                progressDialog.dismiss();

                                AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                                builder
                                        .setMessage("Sambungan Error. Pastikan Sambungan Internet Anda")
                                        .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                                        .show();

                                System.out.println("LOGIN FAILURE: " + t.getMessage());

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

        int colorGorengan = ContextCompat.getColor(this, R.color.gorengan);
        int colorBlack = ContextCompat.getColor(this, R.color.black);

        TextView colorText2 = (TextView)findViewById(R.id.gorenganlogin);
        SpannableString text2 = new SpannableString("GorenganIndonesia.");
        text2.setSpan(new ForegroundColorSpan(colorGorengan), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setSpan(new ForegroundColorSpan(colorBlack), 8, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        colorText2.setText(text2);
        colorText2.setText(text2, TextView.BufferType.SPANNABLE);
    }
}