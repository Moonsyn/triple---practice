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
import com.example.practice.Entities.CityAttributeRecyclerViewItem;
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

    private RecyclerView cityAttributeRecyclerView;
    private ArrayList<CityAttributeRecyclerViewItem> mList;

    private LinearLayoutManager mLinearLayoutManager;

    private CityAttributeRecyclerViewAdapter cityAttributeRecyclerViewAdapter;

    private String cityName, weather, temp;

    private String url = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=19062284d96edefeb8a2a10aa47b638f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // 도시 관광 요소 리사이클러 뷰 구성 코드 (18줄)
        cityAttributeRecyclerView = findViewById(R.id.rvCityAttribute);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        cityAttributeRecyclerView.setLayoutManager(mLinearLayoutManager);

        mList = new ArrayList<>();

        mList.add(new CityAttributeRecyclerViewItem("일정"));
        mList.add(new CityAttributeRecyclerViewItem("가이드"));
        mList.add(new CityAttributeRecyclerViewItem("호텔"));
        mList.add(new CityAttributeRecyclerViewItem("관광"));
        mList.add(new CityAttributeRecyclerViewItem("맛집"));
        mList.add(new CityAttributeRecyclerViewItem("투어,티켓"));
        mList.add(new CityAttributeRecyclerViewItem("저장"));

        cityAttributeRecyclerViewAdapter = new CityAttributeRecyclerViewAdapter(NextActivity.this, mList);
        cityAttributeRecyclerView.setAdapter(cityAttributeRecyclerViewAdapter);

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
}
