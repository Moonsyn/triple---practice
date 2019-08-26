package com.example.practice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.practice.Adapter.CityAttributeRecyclerViewAdapter;
import com.example.practice.Entities.CityAttributeRecyclerViewItem;
import com.example.practice.R;

import java.util.ArrayList;

public class NextActivity extends AppCompatActivity {

    private TextView cityMainTitle, cityWeatherTitle;

    private RecyclerView cityAttributeRecyclerView;
    private ArrayList<CityAttributeRecyclerViewItem> mList;

    private LinearLayoutManager mLinearLayoutManager;

    private CityAttributeRecyclerViewAdapter cityAttributeRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // 도시 관광 요소 리사이클러 뷰 구성 코드 (18줄)
        cityAttributeRecyclerView = findViewById(R.id.rvCityAttribute);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        cityAttributeRecyclerView.setLayoutManager(mLinearLayoutManager);

        mList = new ArrayList<>();

        mList.add(new CityAttributeRecyclerViewItem("일정"));
        mList.add(new CityAttributeRecyclerViewItem("가이드"));
        mList.add(new CityAttributeRecyclerViewItem("호텔"));
        mList.add(new CityAttributeRecyclerViewItem("관광"));
        mList.add(new CityAttributeRecyclerViewItem("맛집"));
        mList.add(new CityAttributeRecyclerViewItem("투어,티켓"));
        mList.add(new CityAttributeRecyclerViewItem("저장"));

        cityAttributeRecyclerViewAdapter = new CityAttributeRecyclerViewAdapter(NextActivity.this, mList);
        cityAttributeRecyclerView.setAdapter(cityAttributeRecyclerViewAdapter);

        // intent로 받은 도시명 textview에 적는 코드
        Intent intent = getIntent();

        String cityName = intent.getStringExtra("name");

        cityMainTitle = findViewById(R.id.tvCityMainTitle);
        cityWeatherTitle = findViewById(R.id.tvWeatherTitle);

        cityMainTitle.setText(cityName + ",");
        cityWeatherTitle.setText(cityName + "의 8월 날씨");



    }
}
