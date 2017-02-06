package es.telefonica.talentum.weatherworld.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.telefonica.talentum.weatherworld.R;
import es.telefonica.talentum.weatherworld.model.City;

public class CityViewHolder extends RecyclerView.ViewHolder {

    private City city;

    private TextView cityNameTextView;
    private TextView cityTemperatureTextView;
    private ImageView cityIconImageView;

    public CityViewHolder(View row) {
        super(row);

        cityNameTextView = (TextView) row.findViewById(R.id.row_city___city_name);
        cityTemperatureTextView = (TextView) row.findViewById(R.id.row_city___temperature);
        cityIconImageView = (ImageView) row.findViewById(R.id.fragment_weather_details_icon);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;

        cityNameTextView.setText(city.getName());



    }
}
