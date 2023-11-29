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
    public Runnable negativeCallback;

    public APIHandlerDAO(View view, View loadingView, TextView loadingText, Context context) {
        this.view = view;
        this.loadingView = loadingView;
        this.loadingText = loadingText;
        this.context = context;
        this.callback = null;
        this.negativeCallback = null;
    }

    public APIHandlerDAO(View view, View loadingView, TextView loadingText, Context context, Runnable callback) {
        this(view, loadingView, loadingText, context);
        this.callback = callback;
        this.negativeCallback = null;
    }

    public APIHandlerDAO(View view, View loadingView, TextView loadingText, Context context, Runnable callback, Runnable negativeCallback) {
        this(view, loadingView, loadingText, context, callback);
        this.negativeCallback = negativeCallback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void setNegativeCallback(Runnable callback){
        this.negativeCallback = callback;
    }
}
