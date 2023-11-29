package com.example.gorenganindonesia.Model.DAO;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class APIHandlerDAO {
    public View view;
    public View loadingView;
    public TextView loadingText;
    public Context context;
    public Runnable callback;

    public APIHandlerDAO(View view, View loadingView, TextView loadingText, Context context) {
        this.view = view;
        this.loadingView = loadingView;
        this.loadingText = loadingText;
        this.context = context;
        this.callback = null;
    }

    public APIHandlerDAO(View view, View loadingView, TextView loadingText, Context context, Runnable callback) {
        this.view = view;
        this.loadingView = loadingView;
        this.loadingText = loadingText;
        this.context = context;
        this.callback = callback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }
}
