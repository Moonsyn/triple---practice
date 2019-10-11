package com.example.practice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Activity.NextActivity;
import com.example.practice.Entities.MainRecyclerViewItem;
import com.example.practice.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>{

    private ArrayList<MainRecyclerViewItem> mList;
    private final Context context;
    private String TAG = "MainRecyclerViewAdapter";

    public class MainViewHolder extends RecyclerView.ViewHolder{
        protected ImageView main_image;
        protected TextView main_description;
        protected TextView main_description2;

        public MainViewHolder(@NonNull View view) {
            super(view);
            this.main_image = view.findViewById(R.id.btnMainRecyclerImage);
            this.main_description = view.findViewById(R.id.tvDescription);
            this.main_description2 = view.findViewById(R.id.tvDescription2);
            final View mView = view;
        }
    }

    public MainRecyclerViewAdapter(Context context, ArrayList<MainRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_recyclerview_item, viewGroup, false);

        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder viewHolder, int position) {
        String imgName = mList.get(position).getMain_image();
        StorageReference imgRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://triple-practice.appspot.com/images/Main/" + imgName);

        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(viewHolder.main_image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Download image Url from Storage is Falied", e);
            }
        });

        //viewHolder.main_image.setImageDrawable(mList.get(position).getMain_image());
        viewHolder.main_description.setText(mList.get(position).getMain_description1());
        viewHolder.main_description2.setText(mList.get(position).getMain_description2());

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
