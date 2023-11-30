package com.example.gorenganindonesia.Util;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodHelper {
    public static void hideKeyboard(View view, InputMethodManager imm){
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
