package vn.luongvo.weatherapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luongvo on 8/9/18.
 */
public class Main implements Parcelable {

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

    protected Main(Parcel in) {
        temp = in.readFloat();
        tempMin = in.readFloat();
        tempMax = in.readFloat();
        pressure = in.readFloat();
        seaLevel = in.readFloat();
        grndLevel = in.readFloat();
        humidity = in.readFloat();
        tempKf = in.readFloat();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(temp);
        parcel.writeFloat(tempMin);
        parcel.writeFloat(tempMax);
        parcel.writeFloat(pressure);
        parcel.writeFloat(seaLevel);
        parcel.writeFloat(grndLevel);
        parcel.writeFloat(humidity);
        parcel.writeFloat(tempKf);
    }

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
