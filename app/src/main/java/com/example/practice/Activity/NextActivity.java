package com.example.practice.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practice.Adapter.CityAttributeRecyclerViewAdapter;
import com.example.practice.Adapter.CityHotelRecyclerViewAdapter;
import com.example.practice.Entities.CityAttributeRecyclerViewItem;
import com.example.practice.Entities.CityHotelRecyclerViewItem;
import com.example.practice.Entities.WeatherItem;
import com.example.practice.Entities.WeatherMainItem;
import com.example.practice.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NextActivity extends AppCompatActivity {

    private TextView cityMainTitle, cityWeatherTitle, cityWeatherDescription;

    private RecyclerView cityAttributeRecyclerView, cityHotelRecyclerView;
    private ArrayList<CityAttributeRecyclerViewItem> attributeList;
    private ArrayList<CityHotelRecyclerViewItem> hotelList;

    private LinearLayoutManager mLinearLayoutManager;

    private CityAttributeRecyclerViewAdapter cityAttributeRecyclerViewAdapter;
    private CityHotelRecyclerViewAdapter cityHotelRecyclerViewAdapter;

    private String cityName, weather, temp;

    private String url = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=19062284d96edefeb8a2a10aa47b638f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // 도시 관광 요소 리사이클러 뷰 구성 코드 (18줄) + 도시 호텔 추천 리스트 리사이클러 뷰 구성 코드
        cityAttributeRecyclerView = findViewById(R.id.rvCityAttribute);
        cityHotelRecyclerView = findViewById(R.id.rvHotel);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        cityAttributeRecyclerView.setLayoutManager(mLinearLayoutManager);
        cityHotelRecyclerView.setLayoutManager(mLinearLayoutManager);

        attributeList = new ArrayList<>();

        attributeList.add(new CityAttributeRecyclerViewItem("일정"));
        attributeList.add(new CityAttributeRecyclerViewItem("가이드"));
        attributeList.add(new CityAttributeRecyclerViewItem("호텔"));
        attributeList.add(new CityAttributeRecyclerViewItem("관광"));
        attributeList.add(new CityAttributeRecyclerViewItem("맛집"));
        attributeList.add(new CityAttributeRecyclerViewItem("투어,티켓"));
        attributeList.add(new CityAttributeRecyclerViewItem("저장"));

        hotelList = new ArrayList<>();

        hotelList.add(new CityHotelRecyclerViewItem(getDrawable(R.drawable.barcelona), "코타키나발루에 바르셀로나 호텔", "4성급, 도심 지역", "218,937원"));
        hotelList.add(new CityHotelRecyclerViewItem(getDrawable(R.drawable.chiang_mai), "프리미엄 S Class 라운지 호텔", "4성급, 외곽 지역", "123,528원"));
        hotelList.add(new CityHotelRecyclerViewItem(getDrawable(R.drawable.cebu), "참 좋은 호텔", "3성급, 도심 지역", "87,700원"));
        hotelList.add(new CityHotelRecyclerViewItem(getDrawable(R.drawable.danang), "가성비만 좋은 호텔", "1.5성급, 도심 지역", "34,176원"));
        hotelList.add(new CityHotelRecyclerViewItem(getDrawable(R.drawable.fukuoka_hotel), "적당히 좋은 그저 그런 호텔", "2.5성급, 도심 지역", "50,000원"));

        cityAttributeRecyclerViewAdapter = new CityAttributeRecyclerViewAdapter(NextActivity.this, attributeList);
        cityAttributeRecyclerView.setAdapter(cityAttributeRecyclerViewAdapter);

        cityHotelRecyclerViewAdapter = new CityHotelRecyclerViewAdapter(NextActivity.this, hotelList);
        cityHotelRecyclerView.setAdapter(cityHotelRecyclerViewAdapter);

        // intent로 받은 도시명 textview에 적는 코드. 이때 날씨도 같이 적는다.
        Intent intent = getIntent();

        cityName = intent.getStringExtra("name");

        cityMainTitle = findViewById(R.id.tvCityMainTitle);
        cityWeatherTitle = findViewById(R.id.tvWeatherTitle);

        cityMainTitle.setText(cityName + ",");
        cityWeatherTitle.setText(cityName + "의 8월 날씨");

        // 도시 날씨 AsyncTask 실행
        cityWeatherDescription = findViewById(R.id.tvWeatherDescription);

        WeatherAsyncTask mWeatherAsyncTask = new WeatherAsyncTask();
        mWeatherAsyncTask.execute();

    }

    private class WeatherAsyncTask extends AsyncTask<String, Void, Response>{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Response doInBackground(String... strings) {

            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response;
            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            if(response.body() == null){
                return;
            }

            Gson gson = new GsonBuilder().create();

            JsonParser parser = new JsonParser();

            JsonObject object = parser.parse(response.body().charStream()).getAsJsonObject();

            Log.d("JSON OBJECT", String.valueOf(object));

            JsonElement weatherObject = object.get("weather").getAsJsonArray().get(0).getAsJsonObject();
            JsonElement mainObject = object.get("main");

            Log.d("WEATHER DATA", String.valueOf(weatherObject));
            Log.d("MAIN DATA", String.valueOf(mainObject));

            WeatherItem result1 = gson.fromJson(weatherObject, WeatherItem.class);
            WeatherMainItem result2 = gson.fromJson(mainObject, WeatherMainItem.class);

            weather = result1.getMain();
            temp = String.valueOf((int)(Double.parseDouble(result2.getTemp()) - 273.15));

            cityWeatherDescription.setText("날씨 : " + weather + ", 기온 " + temp + "도");

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(NextActivity.this, "날씨 정보를 띄우지 못하였습니다", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeRecyclerView(RecyclerView view, RecyclerView.Adapter adapter, ArrayList<Class> list){

    }
}
