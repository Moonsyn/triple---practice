package com.example.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainRecyclerViewItem> mainArrayList;
    private ArrayList<MainHorizontalRecyclerViewItem> cityArrayList;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private MainHorizontalRecyclerViewAdapter cityRecyclerViewAdapter;
    private RecyclerView mainRecyclerView;
    private RecyclerView cityRecyclerView;
    private LinearLayoutManager mLinearLayoutManager1, mLinearLayoutManager2;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = findViewById(R.id.rvMainList);
        cityRecyclerView = findViewById(R.id.rvCity);
        mLinearLayoutManager1 = new LinearLayoutManager(this);
        mLinearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager1);
        cityRecyclerView.setLayoutManager(mLinearLayoutManager2);

        mainArrayList = new ArrayList<>();
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.coffee), "알고 먹어야 더 맛있는, 베트남 커피", "왜 한국 커피보다 맛있을까?"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.chiang_mai), "다낭 여행 마지막 날 꼭 해야 할 5가지", "마지막 1분도 놓치지 않을 거에요-"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.ocean), "올여름 떠나기 좋은 시원~한 여행지 추천", "여름 휴가지로 딱!"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.pasta), "이탈리아에서 현지인 맛집 찾아내는 법", "2019년 6월 기준 로마 정보 업데이트"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.vancouver), "살고 싶은 도시 1위, 밴쿠버로 오세요", "지금이 여행하기 딱 좋은 시기거든요"));

        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(MainActivity.this, mainArrayList);
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);

        cityArrayList = new ArrayList<>();
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.vancouver), "VANCOUVER"));
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.barcelona), "BARCELONA"));
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.chiang_mai), "CHINAG MAI"));
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.cebu), "CEBU"));
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.prague), "PRAGUE"));
        cityArrayList.add(new MainHorizontalRecyclerViewItem(getDrawable(R.drawable.toronto), "TORONTO"));

        cityRecyclerViewAdapter = new MainHorizontalRecyclerViewAdapter(MainActivity.this, cityArrayList);
        cityRecyclerView.setAdapter(cityRecyclerViewAdapter);

        menuButton = findViewById(R.id.btnMenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
