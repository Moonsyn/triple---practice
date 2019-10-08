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
import com.example.practice.Entities.MenuRecyclerViewItem1;
import com.example.practice.R;

import java.util.ArrayList;

public class MenuRecyclerViewAdapter1 extends RecyclerView.Adapter<MenuRecyclerViewAdapter1.MenuViewHolder1>{

    private ArrayList<MenuRecyclerViewItem1> mList;
    private final Context context;

    public class MenuViewHolder1 extends RecyclerView.ViewHolder{
        protected ImageView menu_image;
        protected TextView menu_name;

        public MenuViewHolder1(@NonNull View view) {
            super(view);
            this.menu_image = view.findViewById(R.id.imgMenu1);
            this.menu_name = view.findViewById(R.id.tvMenuName1);
            final View mView = view;
        }
    }

    public MenuRecyclerViewAdapter1(Context context, ArrayList<MenuRecyclerViewItem1> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public MenuViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_recyclerview_item1, viewGroup, false);

        MenuViewHolder1 viewHolder = new MenuViewHolder1(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder1 viewHolder, int position) {
        viewHolder.menu_image.setImageDrawable(mList.get(position).getMenu_image());
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
