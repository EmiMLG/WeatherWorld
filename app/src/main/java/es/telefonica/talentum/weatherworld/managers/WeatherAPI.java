package es.telefonica.talentum.weatherworld.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;

import es.telefonica.talentum.weatherworld.R;

public class WeatherAPI {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String METHOD_CURRENT_WEATHER = "weather";

    public interface WeatherDownloadDataFinished{
        public void newWeatherData(String cityname, float temperature, String iconUrl);
    }

    private WeatherDownloadDataFinished listener;

    public void setOnWeatherDowloadDataFinished(WeatherDownloadDataFinished listener){
        this.listener = listener;
    }

    public void getCurrentWeatherConditions(Context context, String city, String langCode) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + METHOD_CURRENT_WEATHER + "?q=" + city + "&lang=" + langCode +
                "&appid=" + context.getString(R.string.SuperSecretToken) + "&units=metric&lang=es";

        Log.d("URL", url);

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //al ok
                Log.d("RESPONSE", response);
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Houston, tenemos un problema.
                Log.e("HORROR", "Conection went to shit to the tracks");
            }
        });

        queue.add(request);

    }

    private void parseJson(String response) {
        Gson gson = new GsonBuilder().create();
        Reader reader = new StringReader(response);
        WeatherResponse weatherResponse = gson.fromJson(reader, WeatherResponse.class);
        Log.d("response", weatherResponse.toString());

        if (listener != null);{
            listener.newWeatherData(weatherResponse.getName(),weatherResponse.getMain().getTemperature(),weatherResponse.getIconUrl());
        }
    }

}

