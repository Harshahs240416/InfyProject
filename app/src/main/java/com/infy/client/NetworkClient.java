package com.infy.client;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseURL) {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return mRetrofit;
    }

}
