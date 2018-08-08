package vn.luongvo.weatherapp.dto;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by luongvo on 8/9/18.
 */
public class Weather {

    private long id;
    private String main;
    private String description;
    private String icon;

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
}
