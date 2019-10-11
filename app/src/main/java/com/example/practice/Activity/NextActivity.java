package com.example.practice.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapter.CityAttributeRecyclerViewAdapter;
import com.example.practice.Adapter.CityHotelRecyclerViewAdapter;
import com.example.practice.Adapter.CityTourRecyclerViewAdapter;
import com.example.practice.Entities.CityAttributeRecyclerViewItem;
import com.example.practice.Entities.CityHotelRecyclerViewItem;
import com.example.practice.Entities.CityTourRecyclerViewItem;
import com.example.practice.Entities.WeatherItem;
import com.example.practice.Entities.WeatherMainItem;
import com.example.practice.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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

    private RecyclerView cityAttributeRecyclerView, cityHotelRecyclerView, cityTourRecyclerView;
    private ArrayList<CityAttributeRecyclerViewItem> attributeList;
    private ArrayList<CityHotelRecyclerViewItem> hotelList;
    private ArrayList<CityTourRecyclerViewItem> tourList;

    private LinearLayoutManager weatherLayoutManager, hotelsLayoutManager, tourLayoutManager;

    private CityAttributeRecyclerViewAdapter cityAttributeRecyclerViewAdapter;
    private CityHotelRecyclerViewAdapter cityHotelRecyclerViewAdapter;
    private CityTourRecyclerViewAdapter cityTourRecyclerViewAdapter;

    private String cityName, weather, temp;

    private String URL_WEATHER = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=19062284d96edefeb8a2a10aa47b638f";
    private String URL_HOTELS = "http://15.164.86.175:8000/hotels/list";
    private String URL_TOUR = "http://15.164.86.175:8000/tour/list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // 도시 관광 요소 리사이클러 뷰 구성 코드 (18줄) + 도시 호텔 추천 리스트 리사이클러 뷰 구성 코드
        cityAttributeRecyclerView = findViewById(R.id.rvCityAttribute);
        cityHotelRecyclerView = findViewById(R.id.rvHotel);
        cityTourRecyclerView = findViewById(R.id.rvTour);

        weatherLayoutManager = new LinearLayoutManager(this);
        weatherLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        hotelsLayoutManager = new LinearLayoutManager(this);
        hotelsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        tourLayoutManager = new LinearLayoutManager(this);
        tourLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        cityAttributeRecyclerView.setLayoutManager(weatherLayoutManager);
        cityHotelRecyclerView.setLayoutManager(hotelsLayoutManager);
        cityTourRecyclerView.setLayoutManager(tourLayoutManager);

        attributeList = new ArrayList<>();

        attributeList.add(new CityAttributeRecyclerViewItem("일정"));
        attributeList.add(new CityAttributeRecyclerViewItem("가이드"));
        attributeList.add(new CityAttributeRecyclerViewItem("호텔"));
        attributeList.add(new CityAttributeRecyclerViewItem("관광"));
        attributeList.add(new CityAttributeRecyclerViewItem("맛집"));
        attributeList.add(new CityAttributeRecyclerViewItem("투어,티켓"));
        attributeList.add(new CityAttributeRecyclerViewItem("저장"));

        cityAttributeRecyclerViewAdapter = new CityAttributeRecyclerViewAdapter(NextActivity.this, attributeList);
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

        TripleAsyncTask weatherAsyncTask = new TripleAsyncTask(URL_WEATHER);
        //TripleAsyncTask hotelsAsyncTask = new TripleAsyncTask(URL_HOTELS);
        //TripleAsyncTask tourAsyncTask = new TripleAsyncTask(URL_TOUR);

        weatherAsyncTask.execute();
        //hotelsAsyncTask.execute();
        //tourAsyncTask.execute();
    }

    private class TripleAsyncTask extends AsyncTask<String, Void, Response>{

        OkHttpClient client = new OkHttpClient();
        String url;

        public TripleAsyncTask(String url) {
            this.url = url;
        }

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

            if(url.equals(URL_WEATHER)){
                JsonObject object = parser.parse(response.body().charStream()).getAsJsonObject();

                Log.d("JSON OBJECT", String.valueOf(object));

                JsonElement weatherObject = object.get("weather").getAsJsonArray().get(0).getAsJsonObject();
                JsonElement mainObject = object.get("main");

                WeatherItem result1 = gson.fromJson(weatherObject, WeatherItem.class);
                WeatherMainItem result2 = gson.fromJson(mainObject, WeatherMainItem.class);

                weather = result1.getMain();
                temp = String.valueOf((int)(Double.parseDouble(result2.getTemp()) - 273.15));

                cityWeatherDescription.setText("날씨 : " + weather + ", 기온 " + temp + "도");
            }else if(url.equals(URL_HOTELS)){
                JsonArray jsonArray = parser.parse(response.body().charStream()).getAsJsonArray();

                hotelList = new ArrayList<>();

                for(int i=0;i<jsonArray.size();i++){
                    JsonObject object = jsonArray.get(i).getAsJsonObject();

                    CityHotelRecyclerViewItem hotel = gson.fromJson(object, CityHotelRecyclerViewItem.class);

                    hotelList.add(hotel);
                }
                cityHotelRecyclerViewAdapter = new CityHotelRecyclerViewAdapter(NextActivity.this, hotelList);
                cityHotelRecyclerView.setAdapter(cityHotelRecyclerViewAdapter);
            } else if(url==URL_TOUR){
                JsonArray jsonArray = parser.parse(response.body().charStream()).getAsJsonArray();

                tourList = new ArrayList<>();

                for(int i=0;i<jsonArray.size();i++){
                    JsonObject object = jsonArray.get(i).getAsJsonObject();

                    CityTourRecyclerViewItem tour = gson.fromJson(object, CityTourRecyclerViewItem.class);

                    tourList.add(tour);
                }
                cityTourRecyclerViewAdapter = new CityTourRecyclerViewAdapter(NextActivity.this, tourList);
                cityTourRecyclerView.setAdapter(cityTourRecyclerViewAdapter);
            }

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(NextActivity.this, "통신 중 예기치 못한 에러가 발생하였습니다.", Toast.LENGTH_LONG).show();
        }
    }

}
