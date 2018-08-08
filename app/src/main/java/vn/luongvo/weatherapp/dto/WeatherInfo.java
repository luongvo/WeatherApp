package vn.luongvo.weatherapp.dto;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by luongvo on 8/9/18.
 */
public class WeatherInfo {

    @SerializedName("dt")
    private Date date;
    private Main main;
    @SerializedName("weather")
    private List<Weather> weathers;
    private Clouds clouds;
    private Wind wind;

    public Date getDate() {
        return date;
    }

    public String getDayOfWeek() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }
}
