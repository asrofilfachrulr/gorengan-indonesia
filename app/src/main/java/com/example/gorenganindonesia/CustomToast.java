package com.example.gorenganindonesia;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
    CharSequence text;
    View view;

    boolean isLong; // default custom toast is long in duration

    public CustomToast(CharSequence text, View view) {
        this.text = text;
        this.view = view;
        this.isLong = true;
    }

    public CustomToast(CharSequence text, View view, boolean isLong) {
        this.text = text;
        this.view = view;
        this.isLong = isLong;
    }


    public void show(){
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View layout = inflater.inflate(R.layout.custom_toast, view.findViewById(R.id.custom_toast_container));

        ((TextView) layout.findViewById(R.id.tv_custom_toast)).setText(text);

        Toast toast = new Toast(view.getContext());
        if(isLong) toast.setDuration(Toast.LENGTH_LONG);
        else toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
