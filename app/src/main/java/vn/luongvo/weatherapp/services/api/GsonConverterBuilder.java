package vn.luongvo.weatherapp.services.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by luongvo on 8/7/18.
 */
public class GsonConverterBuilder {

    private static Gson gson;

    private GsonConverterBuilder() {
    }

    public static Gson build() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .create();
        }
        return gson;
    }
}
