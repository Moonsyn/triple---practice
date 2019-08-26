package com.example.practice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.Activity.NextActivity;
import com.example.practice.Entities.MenuRecyclerViewItem2;
import com.example.practice.R;

import java.util.ArrayList;

public class MenuRecyclerViewAdapter2 extends RecyclerView.Adapter<MenuRecyclerViewAdapter2.MenuViewHolder2>{

    private ArrayList<MenuRecyclerViewItem2> mList;
    private final Context context;

    public class MenuViewHolder2 extends RecyclerView.ViewHolder{
        protected TextView menu_name;

        public MenuViewHolder2(@NonNull View view) {
            super(view);
            this.menu_name = view.findViewById(R.id.tvMenuName2);
            final View mView = view;
        }
    }

    public MenuRecyclerViewAdapter2(Context context, ArrayList<MenuRecyclerViewItem2> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public MenuViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_recyclerview_item2, viewGroup, false);

        MenuViewHolder2 viewHolder = new MenuViewHolder2(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder2 viewHolder, int position) {
        viewHolder.menu_name.setText(mList.get(position).getMenu_name());

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
