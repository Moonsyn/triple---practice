package com.example.practice.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.practice.Entities.CityHotelRecyclerViewItem;
import com.example.practice.R;

import java.util.ArrayList;

public class CityHotelRecyclerViewAdapter extends RecyclerView.Adapter<CityHotelRecyclerViewAdapter.CityHotelViewHolder>{

    private ArrayList<CityHotelRecyclerViewItem> mList;
    private final Context context;

    public class CityHotelViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imgHotel;
        protected TextView tvHotelName;
        protected TextView tvHotelCategory;
        protected TextView tvHotelPrice;

        public CityHotelViewHolder(@NonNull View view) {
            super(view);
            this.imgHotel = view.findViewById(R.id.imgHotel);
            this.tvHotelName = view.findViewById(R.id.tvHotelName);
            this.tvHotelCategory = view.findViewById(R.id.tvHotelCategory);
            this.tvHotelPrice = view.findViewById(R.id.tvHotelPrice);
            final View mView = view;
        }
    }

    public CityHotelRecyclerViewAdapter(Context context, ArrayList<CityHotelRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public CityHotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.city_hotel_recyclerview_item, viewGroup, false);

        CityHotelViewHolder viewHolder = new CityHotelViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityHotelViewHolder viewHolder, int position) {
        //viewHolder.imgHotel.setImageDrawable(mList.get(position).getHotelImage());
        Glide.with(context).asDrawable().load(mList.get(position).getImage()).into(viewHolder.imgHotel);
        viewHolder.tvHotelName.setText(mList.get(position).getName());
        viewHolder.tvHotelCategory.setText(mList.get(position).getGrade() + "성급, " + mList.get(position).getLocation());
        viewHolder.tvHotelPrice.setText(String.valueOf(mList.get(position).getPrice()) + "원");

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
