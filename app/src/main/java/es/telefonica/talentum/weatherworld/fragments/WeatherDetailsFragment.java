package es.telefonica.talentum.weatherworld.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.managers.WeatherAPI;

public class WeatherDetailsFragment extends Fragment {

    TextView cityNameText;
    TextView temperatureText;
    TextView getTemperature;
    ImageView icon;

    public WeatherDetailsFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_details,container,false);
        cityNameText = (TextView) view.findViewById(R.id.fragment_weather_details_city_name);
        temperatureText = (TextView) view.findViewById(R.id.fragment_weather_details_temperature);
        icon = (ImageView)view.findViewById(R.id.fragment_weather_details_icon);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon.setZ(1);
            cityNameText.setZ(1000);
            temperatureText.setZ(1000);
        }

        return view;
    }

    public void showWeather(String cityname, String lang) {
        WeatherAPI api = new WeatherAPI();

        api.setOnWeatherDowloadDataFinished(new WeatherAPI.WeatherDownloadDataFinished() {
            @Override
            public void newWeatherData(String cityname, float temperature, String iconUrl) {
                cityNameText.setText(cityname);
                String formattedTemp = String.format("%.1f ºC", temperature);//10.3203232 ---> 10.3

                temperatureText.setText(formattedTemp);
                Picasso.with(getContext()).load(iconUrl).placeholder(android.R.drawable.ic_menu_compass).into(icon);
            }
        });
        api.getCurrentWeatherConditions(getContext(), cityname, lang);


    }
}
