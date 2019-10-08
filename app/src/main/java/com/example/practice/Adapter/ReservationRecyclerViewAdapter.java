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
import com.example.practice.R;
import com.example.practice.Entities.ReservationRecyclerViewItem;

import java.util.ArrayList;

public class ReservationRecyclerViewAdapter extends RecyclerView.Adapter<ReservationRecyclerViewAdapter.ReservationViewHolder>{

    private ArrayList<ReservationRecyclerViewItem> mList;
    private final Context context;

    public class ReservationViewHolder extends RecyclerView.ViewHolder{
        protected ImageView reservation_image;
        protected TextView reservation_name;
        protected TextView reservation_description;
        protected TextView reservation_price;

        public ReservationViewHolder(@NonNull View view) {
            super(view);
            this.reservation_image = view.findViewById(R.id.imgReservation);
            this.reservation_name = view.findViewById(R.id.tvReservationName);
            this.reservation_description = view.findViewById(R.id.tvReservationDescription);
            this.reservation_price = view.findViewById(R.id.tvReservationPrice);
            final View mView = view;
        }
    }

    public ReservationRecyclerViewAdapter(Context context, ArrayList<ReservationRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_reservation_recyclerview_item, viewGroup, false);

        ReservationViewHolder viewHolder = new ReservationViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder viewHolder, int position) {
        viewHolder.reservation_image.setImageDrawable(mList.get(position).getReservation_image());
        viewHolder.reservation_name.setText(mList.get(position).getReservation_name());
        viewHolder.reservation_description.setText(mList.get(position).getReservation_description());
        viewHolder.reservation_price.setText(mList.get(position).getReservation_price());

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
