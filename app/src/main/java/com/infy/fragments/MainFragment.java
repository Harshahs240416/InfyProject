package com.infy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.infosysproficiencyexcercise.R;
import com.infy.adapter.ListAdapter;
import com.infy.client.ClientInterface;
import com.infy.client.NetworkClient;
import com.infy.model.Model;
import com.infy.model.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment  extends Fragment{

    RecyclerView mRecyclerView;
    String mHeaderTitle;
    List<Row> rowLists = new ArrayList<Row>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientInterface clientInterface = NetworkClient.getClient("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/").create(ClientInterface.class);
        retrofit2.Call<Model> call = clientInterface.getRowData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(retrofit2.Call<Model> call, Response<Model> response) {
                if(response != null){
                    rowLists = response.body().getRows();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Response Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Model> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Response Failure", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getActivity().getApplicationContext(), "Out of Web Service", Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ListAdapter listAdapter = new ListAdapter(mHeaderTitle, rowLists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView)view.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
