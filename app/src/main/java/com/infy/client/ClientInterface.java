package com.infy.client;

import com.infy.model.Model;
import com.infy.model.Row;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientInterface {

    @GET("facts.json")
    Call<Model> getRowData();

}
