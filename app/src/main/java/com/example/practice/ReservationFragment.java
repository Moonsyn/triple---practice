package com.example.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ReservationFragment extends Fragment {

    private ArrayList<ReservationRecyclerViewItem> reservationArrayList;
    private ReservationRecyclerViewAdapter reservationRecyclerViewAdapter;
    private RecyclerView reservationRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = getContext();
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        reservationRecyclerView = view.findViewById(R.id.rvReservation);
        mLinearLayoutManager = new LinearLayoutManager(context ,LinearLayoutManager.HORIZONTAL, false);
        reservationRecyclerView.setLayoutManager(mLinearLayoutManager);

        reservationArrayList = new ArrayList<>();
        reservationArrayList.add(new ReservationRecyclerViewItem(context.getDrawable(R.drawable.danag_hotel), "다낭", "가족 모두 즐길 수 있는 호텔", "10만원대~"));
        reservationArrayList.add(new ReservationRecyclerViewItem(context.getDrawable(R.drawable.fukuoka_hotel), "후쿠오카", "포근한 온천이 있는 호텔", "20만원대~"));
        reservationArrayList.add(new ReservationRecyclerViewItem(context.getDrawable(R.drawable.guam_hotel), "괌", "태평양이 눈앞에 펼쳐져 있는 호텔", "10만원대~"));
        reservationArrayList.add(new ReservationRecyclerViewItem(context.getDrawable(R.drawable.osaka_hotel), "오사카", "야경이 아름다운 호텔", "30만원대~"));
        reservationArrayList.add(new ReservationRecyclerViewItem(context.getDrawable(R.drawable.paris_hotel), "파리", "에펠탑이 한 눈에 보이는 호텔", "30만원대~"));

        reservationRecyclerViewAdapter = new ReservationRecyclerViewAdapter(context, reservationArrayList);
        reservationRecyclerView.setAdapter(reservationRecyclerViewAdapter);

        return view;
    }

}