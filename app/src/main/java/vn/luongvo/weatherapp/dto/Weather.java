package vn.luongvo.weatherapp.dto;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by luongvo on 8/9/18.
 */
public class Weather implements Parcelable {

    private long id;
    private String main;
    private String description;
    private String icon;

    protected Weather(Parcel in) {
        id = in.readLong();
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(main);
        parcel.writeString(description);
        parcel.writeString(icon);
    }

    public long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @BindingAdapter("bind:weather_icon")
    public static void bindWeatherIcon(ImageView imageView, Weather weather) {
        Context context = imageView.getContext();
        String resName = "ic_" + weather.getMain().toLowerCase().replace(" ", "_");
        int resId = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
        imageView.setImageResource(resId);
    }

    @BindingAdapter("bind:weather_art")
    public static void bindWeatherArt(ImageView imageView, Weather weather) {
        Context context = imageView.getContext();
        String resName = "art_" + weather.getMain().toLowerCase().replace(" ", "_");
        int resId = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
        imageView.setImageResource(resId);
    }
}
