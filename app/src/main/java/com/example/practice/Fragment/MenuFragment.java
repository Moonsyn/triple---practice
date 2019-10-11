package com.example.practice.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Activity.SignInActivity;
import com.example.practice.Adapter.MenuRecyclerViewAdapter1;
import com.example.practice.Adapter.MenuRecyclerViewAdapter2;
import com.example.practice.Entities.MenuRecyclerViewItem1;
import com.example.practice.Entities.MenuRecyclerViewItem2;
import com.example.practice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

    private ArrayList<MenuRecyclerViewItem1> menuArrayList1;
    private ArrayList<MenuRecyclerViewItem2> menuArrayList2;

    private MenuRecyclerViewAdapter1 menuRecyclerViewAdapter1;
    private MenuRecyclerViewAdapter2 menuRecyclerViewAdapter2;
    private RecyclerView menuRecyclerView1, menuRecyclerView2;
    private LinearLayoutManager mLinearLayoutManager1, mLinearLayoutManager2;

    private Context context;

    private FragmentActivity fragmentActivity;

    private TextView tvUserId;
    private ImageView imgUserProfile;
    private Button menuExitButton, logOutButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    protected View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = getContext();
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        menuArrayList1 = new ArrayList<>();
        menuArrayList2 = new ArrayList<>();

        menuArrayList1.add(new MenuRecyclerViewItem1(context.getDrawable(R.drawable.good), "트리플 여행자 클럽"));
        menuArrayList1.add(new MenuRecyclerViewItem1(context.getDrawable(R.drawable.person), "내 예약"));
        menuArrayList1.add(new MenuRecyclerViewItem1(context.getDrawable(R.drawable.security), "알림"));

        menuArrayList2.add(new MenuRecyclerViewItem2("공지사항"));
        menuArrayList2.add(new MenuRecyclerViewItem2("친구에게 추천하기"));
        menuArrayList2.add(new MenuRecyclerViewItem2("문의하기"));

        menuRecyclerView1 = view.findViewById(R.id.rvMenu1);
        menuRecyclerView2 = view.findViewById(R.id.rvMenu2);

        mLinearLayoutManager1 = new LinearLayoutManager(context ,LinearLayoutManager.VERTICAL, false);
        mLinearLayoutManager2 = new LinearLayoutManager(context ,LinearLayoutManager.VERTICAL, false);

        menuRecyclerView1.setLayoutManager(mLinearLayoutManager1);
        menuRecyclerView2.setLayoutManager(mLinearLayoutManager2);

        menuRecyclerViewAdapter1 = new MenuRecyclerViewAdapter1(context, menuArrayList1);
        menuRecyclerViewAdapter2 = new MenuRecyclerViewAdapter2(context, menuArrayList2);

        menuRecyclerView1.setAdapter(menuRecyclerViewAdapter1);
        menuRecyclerView2.setAdapter(menuRecyclerViewAdapter2);

        menuExitButton = view.findViewById(R.id.btn_menu_exit);
        menuExitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                NoneFragment noneFragment = new NoneFragment();
                fragmentTransaction.replace(R.id.frgMenu, noneFragment);
                fragmentTransaction.commit();
            }
        });
        logOutButton = view.findViewById(R.id.btnSignOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(context, SignInActivity.class));
                getActivity().finish();
            }
        });

        tvUserId = view.findViewById(R.id.tv_menu_userId);
        tvUserId.setText(mUser.getDisplayName());

        imgUserProfile = view.findViewById(R.id.img_menu_userProfile);
        if(mUser.getPhotoUrl() != null){
            Glide.with(context).load(mUser.getPhotoUrl()).into(imgUserProfile);
        }

        return view;
    }

}