package com.example.practice.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practice.Adapter.MenuRecyclerViewAdapter1;
import com.example.practice.Adapter.MenuRecyclerViewAdapter2;
import com.example.practice.Entities.MenuRecyclerViewItem1;
import com.example.practice.Entities.MenuRecyclerViewItem2;
import com.example.practice.R;

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
    private Button menuExitButton;
    protected View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        context = getContext();
        view = inflater.inflate(R.layout.fragment_menu, container, false);

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
        return view;
    }

}