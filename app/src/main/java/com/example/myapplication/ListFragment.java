package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView pulldown;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.mainfragment,container,false);

        recyclerView = view.findViewById(R.id.rcview);
        swipeRefreshLayout =view.findViewById(R.id.swiprefreshing);
        pulldown = view.findViewById(R.id.pulldown);
        image = view.findViewById(R.id.arrow);

        pulldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstmethod();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                firstmethod();
            }
        });

        firstmethod();
        return view;
    }

    private void firstmethod(){
        pulldown.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Interface api = retrofit.create(Interface.class);
        Call<JsonClass> call = api.getString();

        Log.d("List","okeye 4");


        call.enqueue(new Callback<JsonClass>() {


            @Override
            public void onResponse(Call<JsonClass> call, Response<JsonClass> response) {
                Log.d("List","okeye 5");


                pulldown.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.VISIBLE);
                Log.d("Response", response.body().toString());

                if (response.isSuccessful()){
                    Log.d("list2","inja okeye 7");
                    if( response.body() != null){
                        secondmethod(response.body());
                    }
                    else {
                        Log.i("onEmptyResponse","Return empty response");
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonClass> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
                Log.d("List","okeye 6");


                pulldown.setVisibility(View.VISIBLE);
                image.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);

                Toast.makeText(getContext(),"Check your Connection",Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });


    }

    private void secondmethod(JsonClass response){

        List<Data> data = response.getData();

        RecViewAdapter adapter = new RecViewAdapter (data , getContext(),getFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
