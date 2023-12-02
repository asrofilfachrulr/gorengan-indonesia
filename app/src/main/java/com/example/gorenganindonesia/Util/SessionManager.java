package com.example.gorenganindonesia.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.gorenganindonesia.Activity.LoginActivity;
import com.example.gorenganindonesia.Activity.MainActivity;
import com.example.gorenganindonesia.Model.data.Account.Account;

public class SessionManager {
    SharedPreferences sharedPreferences;
    private String token;

    public SessionManager(SharedPreferences sp){
        this.sharedPreferences = sp;
        this.token = sp.getString("token", "");
    }

    public String getToken() {
        return token;
    }

    public String getJwtHeaderValue(){
        return "Bearer " + token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void login(Context context, Activity activity, String token, Account account, String msg){
        setToken(token);
        saveLoginInfo();

        saveAccountInfo(account);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("TOAST_MSG", msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logout(Context context, Activity activity, String msg){
        sharedPreferences
                .edit()
                .clear()
                .apply();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("TOAST_MSG", msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public void saveAccountInfo(Account account){
        sharedPreferences.edit()
                .putString("account_id", account.getId())
                .putString("account_name", account.getName())
                .putString("account_username", account.getUsername())
                .putString("account_image_url", account.getImageUrl())
                .putString("account_image_path", account.getImagePath())
                .putString("account_email", account.getEmail())
                .apply();

    }

    public void saveLoginInfo(){
        sharedPreferences
                .edit()
                .putBoolean("isLogged", true)
                .putString("token", token)
                .apply();
    }

    public void saveToken(){
        sharedPreferences
                .edit()
                .putString("token", token)
                .apply();
    }
}
