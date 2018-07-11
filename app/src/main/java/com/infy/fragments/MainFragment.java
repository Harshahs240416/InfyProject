package com.infy.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.infosysproficiencyexcercise.R;
import com.infy.adapter.ListAdapter;
import com.infy.client.ClientInterface;
import com.infy.client.NetworkClient;
import com.infy.main.MainActivity;
import com.infy.model.Model;
import com.infy.model.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    RecyclerView mRecyclerView;
    String mHeaderTitle;
    List<Row> rowLists = new ArrayList<Row>();
    ProgressDialog mProgressBar;
    MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startProgressBar();
        ClientInterface clientInterface = NetworkClient.getClient("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/").create(ClientInterface.class);
        retrofit2.Call<Model> call = clientInterface.getRowData();
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(retrofit2.Call<Model> call, Response<Model> response) {
                rowLists = response.body().getRows();
                mainActivity.setActionBarTitle(response.body().getTitle());
                if (mProgressBar.isShowing()) {
                    mProgressBar.dismiss();
                }
                ListAdapter listAdapter = new ListAdapter(mHeaderTitle, rowLists);
                mRecyclerView.setAdapter(listAdapter);

            }

            @Override
            public void onFailure(retrofit2.Call<Model> call, Throwable t) {
                if (mProgressBar.isShowing()) {
                    mProgressBar.dismiss();
                }
                Toast.makeText(getActivity().getApplicationContext(), "Response Failure im inside On Failure", Toast.LENGTH_SHORT).show();
                Log.d("inOnFailure", "Check if im in");
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    public void startProgressBar() {
        mProgressBar = new ProgressDialog(getActivity());
        mProgressBar.setCancelable(true);
        mProgressBar.setMessage("File downloading ...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setIndeterminate(true);
        mProgressBar.show();
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
