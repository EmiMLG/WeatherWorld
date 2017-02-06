package es.telefonica.talentum.weatherworld;

import android.app.Application;
import android.util.Log;

import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class WeatherWorldApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application", "Creating application");

        Realm.init(getApplicationContext());

        Picasso.with(getApplicationContext()).setIndicatorsEnabled(false);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("Application", "Low memory detected");
    }
}
