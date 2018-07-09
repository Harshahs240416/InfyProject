package com.infy.client;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseURL) {

        if (mRetrofit == null) {
            //Added First three lines to check for the Logs
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).addInterceptor(interceptor).build();
            //Retrofit Client
            mRetrofit = new Retrofit.Builder().baseUrl(baseURL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return mRetrofit;
    }

}
