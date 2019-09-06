package com.example.practice.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.practice.Entities.CityHotelRecyclerViewItem;
import com.example.practice.Entities.CityTourRecyclerViewItem;
import com.example.practice.R;

import java.util.ArrayList;

public class CityTourRecyclerViewAdapter extends RecyclerView.Adapter<CityTourRecyclerViewAdapter.CityTourViewHolder>{

    private ArrayList<CityTourRecyclerViewItem> mList;
    private final Context context;

    public class CityTourViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imgTour;
        protected TextView tvTourName;
        protected TextView tvTourAddressRate;

        public CityTourViewHolder(@NonNull View view) {
            super(view);
            this.imgTour = view.findViewById(R.id.imgTour);
            this.tvTourName = view.findViewById(R.id.tvTourName);
            this.tvTourAddressRate = view.findViewById(R.id.tvTourAddressRate);
            final View mView = view;
        }
    }

    public CityTourRecyclerViewAdapter(Context context, ArrayList<CityTourRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public CityTourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.city_tour_recyclerview_item, viewGroup, false);

        CityTourViewHolder viewHolder = new CityTourViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityTourViewHolder viewHolder, int position) {
        //viewHolder.imgHotel.setImageDrawable(mList.get(position).getHotelImage());
        Glide.with(context).asDrawable().load(mList.get(position).getImage()).into(viewHolder.imgTour);
        viewHolder.tvTourName.setText(mList.get(position).getName());
        viewHolder.tvTourAddressRate.setText(mList.get(position).getAddress() + ", " + mList.get(position).getRate() + "점");

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
