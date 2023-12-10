package com.example.gorenganindonesia.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://api-gorengan-indonesia.vercel.app";

    private RetrofitClient() {
        // Private constructor to prevent instantiation from other classes.
    }

    public static Retrofit getInstance() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS) // Set the connection timeout
                .readTimeout(120, TimeUnit.SECONDS)    // Set the read timeout
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(TimestampConverter.createGson()))
                    .build();
        }
        return retrofit;
    }
}
