package com.example.gorenganindonesia;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
    CharSequence text;
    View view;

    CustomToast(CharSequence text, View view) {
        this.text = text;
        this.view = view;
    }

    public void show(){
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View layout = inflater.inflate(R.layout.custom_toast, view.findViewById(R.id.custom_toast_container));

        ((TextView) layout.findViewById(R.id.tv_custom_toast)).setText(text);

        Toast toast = new Toast(view.getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
