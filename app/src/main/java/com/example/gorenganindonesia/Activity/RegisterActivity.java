package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gorenganindonesia.API.Services.AuthService;
import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.api.Register.RegisterRequest;
import com.example.gorenganindonesia.Model.api.Register.RegisterResponse;
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
    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        thisActivity = this;

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordRepeat = (EditText) findViewById(R.id.et_password_repeat);


        //warna text GorenganIndonesia.
        int colorGorengan = ContextCompat.getColor(this, R.color.gorengan);
        int colorBlack = ContextCompat.getColor(this, R.color.black);

        TextView colorText2 = (TextView)findViewById(R.id.gorengansignup);
        SpannableString text2 = new SpannableString("GorenganIndonesia.");
        text2.setSpan(new ForegroundColorSpan(colorGorengan), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setSpan(new ForegroundColorSpan(colorBlack), 8, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        colorText2.setText(text2, TextView.BufferType.SPANNABLE);

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

                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Membuat Akun Anda...");
                    progressDialog.show();

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
                                    progressDialog.dismiss();
                                    if(response.isSuccessful()){
                                        new CustomToast("Registrasi selesai, silahkan masuk", v).show();
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        int statusCode = response.code();
                                        try {
                                            String errorBody = response.errorBody().string();
                                            Log.e("Status code: ", String.valueOf(statusCode));
                                            Log.e("Error Response Body", errorBody);

                                            JSONObject errorJson = new JSONObject(errorBody);
                                            String errorText = errorJson.optString("error");

                                            AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                                            builder
                                                    .setMessage("Error: " + errorText)
                                                    .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                                                    .show();

                                        } catch (IOException | JSONException e) {
                                            Log.e("error", e.toString());
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                                    progressDialog.dismiss();
                                    new CustomToast("Koneksi Error!", v).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                                    builder
                                            .setMessage("Sambungan Error. Pastikan Sambungan Internet Anda")
                                            .setPositiveButton("OKE", (dialog, which) -> {dialog.dismiss();})
                                            .show();
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