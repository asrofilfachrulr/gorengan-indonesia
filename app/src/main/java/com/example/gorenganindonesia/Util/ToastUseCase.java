package com.example.gorenganindonesia.Util;

import android.view.View;

public class ToastUseCase {
    public static void showInDevelopment(View view){
        new CustomToast("Fitur masih dalam tahap pengembangan", view, false).show();
    }

    public static void showUnexpectedError(View view){
        new CustomToast("Error tidak terduga", view, false).show();
    }
}
