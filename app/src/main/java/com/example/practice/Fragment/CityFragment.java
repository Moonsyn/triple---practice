package com.example.practice.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practice.Adapter.CityRecyclerViewAdapter;
import com.example.practice.Entities.CityRecyclerViewItem;
import com.example.practice.R;

import java.util.ArrayList;


public class CityFragment extends Fragment {

    private ArrayList<CityRecyclerViewItem> cityArrayList;
    private CityRecyclerViewAdapter cityRecyclerViewAdapter;
    private RecyclerView cityRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private Context context;
    private Layout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = getContext();
        View view = inflater.inflate(R.layout.fragment_city, container, false);

        cityArrayList = new ArrayList<>();
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.vancouver), "VANCOUVER"));
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.barcelona), "BARCELONA"));
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.chiang_mai), "CHINAG MAI"));
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.cebu), "CEBU"));
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.prague), "PRAGUE"));
        cityArrayList.add(new CityRecyclerViewItem(context.getDrawable(R.drawable.toronto), "TORONTO"));

        cityRecyclerView = view.findViewById(R.id.rvCity);
        mLinearLayoutManager = new LinearLayoutManager(context ,LinearLayoutManager.HORIZONTAL, false);
        cityRecyclerView.setLayoutManager(mLinearLayoutManager);

        cityRecyclerViewAdapter = new CityRecyclerViewAdapter(context, cityArrayList);
        cityRecyclerView.setAdapter(cityRecyclerViewAdapter);

        return view;
    }

}