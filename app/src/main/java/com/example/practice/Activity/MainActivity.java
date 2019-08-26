package com.example.practice.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.practice.Fragment.CityFragment;
import com.example.practice.Fragment.MenuFragment;
import com.example.practice.Fragment.NoneFragment;
import com.example.practice.Adapter.MainRecyclerViewAdapter;
import com.example.practice.Entities.MainRecyclerViewItem;
import com.example.practice.R;
import com.example.practice.Fragment.ReservationFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainRecyclerViewItem> mainArrayList;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mLinearLayoutManager1;
    private TextView cityButton, reservationButton;
    private Button menuExitButton;
    private CityFragment cityFragment;
    private ReservationFragment reservationFragment;
    private MenuFragment menuFragment;
    private NoneFragment noneFragment;

    private ImageButton menuButton;

    private int fragmentState;
    public int menuFragmentState = 0;
    private int CITY_FRAGMENT = 0;
    private int RESERVATION_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentState = RESERVATION_FRAGMENT;
        replaceFragment(fragmentState);

        mainRecyclerView = findViewById(R.id.rvMainList);
        mLinearLayoutManager1 = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager1);

        cityButton = findViewById(R.id.tvMyCity);
        cityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replaceFragment(fragmentState);
            }
        });
        reservationButton = findViewById(R.id.tvHotelReservation);
        reservationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replaceFragment(fragmentState);
            }
        });
        menuButton = findViewById(R.id.btnMenu);
        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);

                menuFragment = new MenuFragment();
                fragmentTransaction.replace(R.id.frgMenu, menuFragment);
                fragmentTransaction.commit();
                menuFragmentState = 1;
            }
        });

        mainArrayList = new ArrayList<>();
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.coffee), "알고 먹어야 더 맛있는, 베트남 커피", "왜 한국 커피보다 맛있을까?"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.chiang_mai), "다낭 여행 마지막 날 꼭 해야 할 5가지", "마지막 1분도 놓치지 않을 거에요-"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.ocean), "올여름 떠나기 좋은 시원~한 여행지 추천", "여름 휴가지로 딱!"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.pasta), "이탈리아에서 현지인 맛집 찾아내는 법", "2019년 6월 기준 로마 정보 업데이트"));
        mainArrayList.add(new MainRecyclerViewItem(getDrawable(R.drawable.vancouver), "살고 싶은 도시 1위, 밴쿠버로 오세요", "지금이 여행하기 딱 좋은 시기거든요"));

        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(MainActivity.this, mainArrayList);
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);
    }
    public void replaceFragment(int fragstate){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        if(fragstate == RESERVATION_FRAGMENT){
            cityFragment = new CityFragment();
            fragmentTransaction.replace(R.id.frgCityOrReservation, cityFragment);
            fragmentTransaction.commit();
            fragmentState = CITY_FRAGMENT;
        }
        else if(fragstate == CITY_FRAGMENT){
            reservationFragment = new ReservationFragment();
            fragmentTransaction.replace(R.id.frgCityOrReservation, reservationFragment);
            fragmentTransaction.commit();
            fragmentState = RESERVATION_FRAGMENT;
        }
    }

    public void hideMenu(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);

        noneFragment = new NoneFragment();
        fragmentTransaction.replace(R.id.frgMenu, noneFragment);
        fragmentTransaction.commit();
        //menuFragmentState = 0;
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frgMenu);
        if(currentFragment == menuFragment){
            hideMenu();
        }
        else{
            super.onBackPressed();
        }
    }
}
