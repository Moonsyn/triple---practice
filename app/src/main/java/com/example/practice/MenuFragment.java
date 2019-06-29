package com.example.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

    private ArrayList<MenuRecyclerViewItem1> menuArrayList1;
    private ArrayList<MenuRecyclerViewItem2> menuArrayList2;
    private MenuRecyclerViewAdapter1 menuRecyclerViewAdapter1;
    private MenuRecyclerViewAdapter2 menuRecyclerViewAdapter2;
    private RecyclerView menuRecyclerView1, menuRecyclerView2;
    private LinearLayoutManager mLinearLayoutManager1, mLinearLayoutManager2;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = getContext();
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        menuRecyclerView1 = view.findViewById(R.id.rvMenu1);
        menuRecyclerView2 = view.findViewById(R.id.rvMenu2);

        mLinearLayoutManager1 = new LinearLayoutManager(context ,LinearLayoutManager.VERTICAL, false);
        mLinearLayoutManager2 = new LinearLayoutManager(context ,LinearLayoutManager.VERTICAL, false);

        menuRecyclerView1.setLayoutManager(mLinearLayoutManager1);
        menuRecyclerView2.setLayoutManager(mLinearLayoutManager2);

        menuRecyclerViewAdapter1 = new MenuRecyclerViewAdapter1(context, menuArrayList1);
        menuRecyclerViewAdapter2 = new MenuRecyclerViewAdapter2(context, menuArrayList2);

        menuRecyclerView1.setAdapter(menuRecyclerViewAdapter1);
        menuRecyclerView2.setAdapter(menuRecyclerViewAdapter2);

        return view;
    }

}