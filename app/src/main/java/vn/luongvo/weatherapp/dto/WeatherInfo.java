package vn.luongvo.weatherapp.dto;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.utils.DateUtils;

/**
 * Created by luongvo on 8/9/18.
 */
public class WeatherInfo implements Parcelable {

    public static final long DAY_IN_MS = 86400000L;

    @SerializedName("dt")
    private Date date;
    private Main main;
    @SerializedName("weather")
    private List<Weather> weathers;
    private Clouds clouds;
    private Wind wind;

    protected WeatherInfo(Parcel in) {
        date = DateUtils.readDate(in);
        main = in.readParcelable(Main.class.getClassLoader());
        weathers = in.createTypedArrayList(Weather.CREATOR);
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
    }

    public static final Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        DateUtils.writeDate(parcel, date);
        parcel.writeParcelable(main, i);
        parcel.writeTypedList(weathers);
        parcel.writeParcelable(clouds, i);
        parcel.writeParcelable(wind, i);
    }

    public Date getDate() {
        return date;
    }

    public String getDayOfWeek(Context context) {
        Date now = new Date();
        long ms = Math.abs(now.getTime() - date.getTime());
        long elapsedDays = ms / DAY_IN_MS;

        if (elapsedDays <= 0)
            return context.getString(R.string.today);
        else if (elapsedDays == 1)
            return context.getString(R.string.tomorrow);
        else return new SimpleDateFormat("EEEE").format(date);
    }

    public String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd");
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
