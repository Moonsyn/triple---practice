package com.example.practice.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.Entities.CityAttributeRecyclerViewItem;
import com.example.practice.R;

import java.util.ArrayList;

public class CityAttributeRecyclerViewAdapter extends RecyclerView.Adapter<CityAttributeRecyclerViewAdapter.CityAttributeViewHolder>{

    private ArrayList<CityAttributeRecyclerViewItem> mList;
    private final Context context;

    public class CityAttributeViewHolder extends RecyclerView.ViewHolder{
        protected TextView attribute_name;

        public CityAttributeViewHolder(@NonNull View view) {
            super(view);
            this.attribute_name = view.findViewById(R.id.tvCityAttribute);
            final View mView = view;
        }
    }

    public CityAttributeRecyclerViewAdapter(Context context, ArrayList<CityAttributeRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public CityAttributeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.city_attribute_recyclerview_item, viewGroup, false);

        CityAttributeViewHolder viewHolder = new CityAttributeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityAttributeViewHolder viewHolder, int position) {
        viewHolder.attribute_name.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
