package com.example.practice.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practice.Activity.NextActivity;
import com.example.practice.Entities.CityRecyclerViewItem;
import com.example.practice.R;

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
            this.city_name = view.findViewById(R.id.tvCityAttribute);
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
    public void onBindViewHolder(@NonNull final CityViewHolder viewHolder, int position) {
        viewHolder.city_image.setImageDrawable(mList.get(position).getCity_image());
        viewHolder.city_name.setText(mList.get(position).getCity_name());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context, NextActivity.class);
                intent.putExtra("name", viewHolder.city_name.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
