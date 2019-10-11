package com.example.practice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapter.MainRecyclerViewAdapter;
import com.example.practice.Entities.MainRecyclerViewItem;
import com.example.practice.Fragment.CityFragment;
import com.example.practice.Fragment.MenuFragment;
import com.example.practice.Fragment.NoneFragment;
import com.example.practice.Fragment.ReservationFragment;
import com.example.practice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainRecyclerViewItem> mainArrayList;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mLinearLayoutManager1;
    private TextView cityButton, reservationButton, tvUserName;
    private Button menuExitButton;
    private CityFragment cityFragment;
    private ReservationFragment reservationFragment;
    private MenuFragment menuFragment;
    private NoneFragment noneFragment;

    private ImageButton menuButton;

    private String mUserName;
    private String mPhotoUrl;

    // Google or Firebase Variables
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseStorage mStorage;

    private int fragmentState;
    public int menuFragmentState = 0;
    private int CITY_FRAGMENT = 0;
    private int RESERVATION_FRAGMENT = 1;
    private long ONE_MEGABYTE = 1024 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if(mUser == null){
            // Not signed in, launch the SignIn Activity
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
            return;
        }else{
            mUserName = mUser.getDisplayName();
            tvUserName = findViewById(R.id.tvUserId);
            tvUserName.setText(mUserName);
            if(mUser.getPhotoUrl() != null){
                mPhotoUrl = mUser.getPhotoUrl().toString();
            }
        }

        fragmentState = RESERVATION_FRAGMENT;
        replaceFragment(fragmentState);

        mainRecyclerView = findViewById(R.id.rvMainList);
        mLinearLayoutManager1 = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager1);

        cityButton = findViewById(R.id.tvMyCity);
        cityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replaceFragment(fragmentState);
            }
        });
        reservationButton = findViewById(R.id.tvHotelReservation);
        reservationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                replaceFragment(fragmentState);
            }
        });
        menuButton = findViewById(R.id.btnMenu);
        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);

                menuFragment = new MenuFragment();
                fragmentTransaction.replace(R.id.frgMenu, menuFragment);
                fragmentTransaction.commit();
                menuFragmentState = 1;
            }
        });

        mStorage = FirebaseStorage.getInstance();
        // App의 Firebase Storage 참조를 만든다.
        StorageReference storageRef = mStorage.getReference();
        // storageRef의 child 참조를 만든다. 이 imgRef는 내 Storage에서 "imgaes"를 가리킨다.
        // 이 외에도 getParent()나 getRoot() 메소드를 이용해서 Reference를 탐색할 수 있다.
        // getPath()는 파일까지의 경로, getName()는 파일의 이름을 리턴한다.
        StorageReference imgRef = storageRef.child("images/Main");
        // Storage 자체 url을 이용해서 참조하는 것도 가능하다.
        StorageReference gsReference = mStorage.getReferenceFromUrl("gs://triple-practice.appspot.com");

        mainArrayList = new ArrayList<>();
        mainArrayList.add(new MainRecyclerViewItem("coffee.jpg", "알고 먹어야 더 맛있는, 베트남 커피", "왜 한국 커피보다 맛있을까?"));
        mainArrayList.add(new MainRecyclerViewItem("chiang_mai.jpg", "다낭 여행 마지막 날 꼭 해야 할 5가지", "마지막 1분도 놓치지 않을 거에요-"));
        mainArrayList.add(new MainRecyclerViewItem("ocean.jpg", "올여름 떠나기 좋은 시원~한 여행지 추천", "여름 휴가지로 딱!"));
        mainArrayList.add(new MainRecyclerViewItem("pasta.jpg", "이탈리아에서 현지인 맛집 찾아내는 법", "2019년 6월 기준 로마 정보 업데이트"));
        mainArrayList.add(new MainRecyclerViewItem("vancouver.jpg", "살고 싶은 도시 1위, 밴쿠버로 오세요", "지금이 여행하기 딱 좋은 시기거든요"));

        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(MainActivity.this, mainArrayList);
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);
    }
    public void replaceFragment(int fragstate){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        if(fragstate == RESERVATION_FRAGMENT){
            cityFragment = new CityFragment();
            fragmentTransaction.replace(R.id.frgCityOrReservation, cityFragment);
            fragmentTransaction.commit();
            fragmentState = CITY_FRAGMENT;
        }
        else if(fragstate == CITY_FRAGMENT){
            reservationFragment = new ReservationFragment();
            fragmentTransaction.replace(R.id.frgCityOrReservation, reservationFragment);
            fragmentTransaction.commit();
            fragmentState = RESERVATION_FRAGMENT;
        }
    }

    public void hideMenu(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);

        noneFragment = new NoneFragment();
        fragmentTransaction.replace(R.id.frgMenu, noneFragment);
        fragmentTransaction.commit();
        //menuFragmentState = 0;
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frgMenu);
        if(currentFragment == menuFragment){
            hideMenu();
        }
        else{
            super.onBackPressed();
        }
    }
}
