package es.telefonica.talentum.weatherworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.fragments.WeatherDetailsFragment;

public class CityDetailActivity extends AppCompatActivity {

    //El proyecto lo hemos sacado de http://api.openweathermap.org/data/2.5/weather?q=Arenas,es&appid=1745672471a183e64b5e81ac9cf2594b&units=metric&lang=es

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WeatherDetailsFragment fragment = (WeatherDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_weather_details);

        Intent i = getIntent();
        String cityName = i.getStringExtra("CITY_DETAIL_NAME");
        if (cityName != null){
        fragment.showWeather(cityName, "es");
    }else{
        finish();
    }
    }
}
