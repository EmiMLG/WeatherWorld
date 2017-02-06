package es.telefonica.talentum.weatherworld.managers;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherResponse {
    private @SerializedName("name") String name;
    private @SerializedName("main") Main main;
    private @SerializedName("coord") Coord coordinates;
    private @SerializedName("weather")List<Weather> weather;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coord getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coord coordinates) {
        this.coordinates = coordinates;
    }
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public class Weather{
        @SerializedName("main") String main;
        @SerializedName("description") String description;
        @SerializedName("icon") String icon;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
    public String getIconUrl(){
        String iconUrl = "";

        if (this.weather != null && this.weather.size()>0){
            Weather firstWeather = this.weather.get(0);

            //Buscar imagen de alta resolución
            iconUrl = fetchHighResolutionImage(firstWeather.getIcon());

            //si no se ha encontrado nos quedamos con la imagen en baja resolución
            if (iconUrl == null)
            {
                iconUrl = "http://openweathermap.org/img/w/" + firstWeather.getIcon() + ".png";
            }
        }
        return iconUrl;
    }

    private String fetchHighResolutionImage(String lowResIcon) {

        //final String baseURL = "https://www.yahoo.com/sy/os/weather/1.0.1/shadow_icon/60x60/";
        Map<String, String> imgs = new HashMap<>();
        final String baseURL = "http://192.168.0.121:9999/";
        imgs.put("01n" ,"clear_night%402x.png");
        imgs.put("02n" ,"fair_night%402x.png");
        imgs.put("04d" ,"partly_cloudy_day%402x.png");
        imgs.put("03d" ,"fair_day%402x.png");
        imgs.put("01d" ,"clear_day%402x.png");
        imgs.put("09n" ,"rain_day_night%402x.png");

        String highResIcon = imgs.get(lowResIcon);
        if (highResIcon != null){
            return baseURL + highResIcon;
        }
        return null;
    }

    public class Coord {
        private @SerializedName("lon") float logitude;
        private @SerializedName("lat") float latitude;
        public float getLogitude() {
            return logitude;
        }
        public void setLogitude(float logitude) {
            this.logitude = logitude;
        }
        public float getLatitude() {
            return latitude;
        }
        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }
    }
    public class Main {
        private @SerializedName("temp") float temperature;
        public float getTemperature() {
            return temperature;
        }
        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }
    }
}
