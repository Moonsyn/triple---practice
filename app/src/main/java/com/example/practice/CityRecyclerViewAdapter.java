package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder>{

    private ArrayList<CityRecyclerViewItem> mList;
    private final Context context;

    public class CityViewHolder extends RecyclerView.ViewHolder{
        protected ImageView city_image;
        protected TextView city_name;

        public CityViewHolder(@NonNull View view) {
            super(view);
            this.city_image = view.findViewById(R.id.imgCity);
            this.city_name = view.findViewById(R.id.tvCityName);
            final View mView = view;
        }
    }

    public CityRecyclerViewAdapter(Context context, ArrayList<CityRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_city_recyclerview_item, viewGroup, false);

        CityViewHolder viewHolder = new CityViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder viewHolder, int position) {
        viewHolder.city_image.setImageDrawable(mList.get(position).getCity_image());
        viewHolder.city_name.setText(mList.get(position).getCity_name());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, NextActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
