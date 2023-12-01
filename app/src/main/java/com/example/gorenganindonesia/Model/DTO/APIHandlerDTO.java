package com.example.gorenganindonesia.Model.DTO;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class APIHandlerDTO {
    public View view;
    public View loadingView;
    public TextView loadingText;
    public Context context;
    public Runnable callback;
    public Runnable negativeCallback;
    public boolean isDaemonMode;

    public static boolean DAEMON_MODE = true;
    public static boolean SCREAMING_MODE = false;

    public APIHandlerDTO(View view, Context context, Runnable callback) {
        this.view = view;
        this.context = context;
        this.callback = callback;
        this.isDaemonMode = DAEMON_MODE;
    }

    public APIHandlerDTO(View view, View loadingView, TextView loadingText, Context context) {
        this.view = view;
        this.loadingView = loadingView;
        this.loadingText = loadingText;
        this.context = context;
        this.callback = null;
        this.negativeCallback = null;
        this.isDaemonMode = SCREAMING_MODE;
    }

    public APIHandlerDTO(View view, View loadingView, TextView loadingText, Context context, Runnable callback) {
        this(view, loadingView, loadingText, context);
        this.callback = callback;
        this.negativeCallback = null;
    }

    public APIHandlerDTO(View view, View loadingView, TextView loadingText, Context context, Runnable callback, Runnable negativeCallback) {
        this(view, loadingView, loadingText, context, callback);
        this.negativeCallback = negativeCallback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void setNegativeCallback(Runnable callback){
        this.negativeCallback = callback;
    }

    public boolean getDaemonMode() {
        return isDaemonMode;
    }

    public void setDaemonMode(boolean daemonMode) {
        isDaemonMode = daemonMode;
    }
}
