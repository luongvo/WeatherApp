package vn.luongvo.weatherapp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luongvo on 8/9/18.
 */
public class Main {

    private float temp;
    @SerializedName("temp_min")
    private float tempMin;
    @SerializedName("temp_max")
    private float tempMax;
    private float pressure;
    @SerializedName("sea_level")
    private float seaLevel;
    @SerializedName("grnd_level")
    private float grndLevel;
    private float humidity;
    @SerializedName("temp_kf")
    private float tempKf;

    public float getTemp() {
        return temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public float getSeaLevel() {
        return seaLevel;
    }

    public float getGrndLevel() {
        return grndLevel;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTempKf() {
        return tempKf;
    }
}
