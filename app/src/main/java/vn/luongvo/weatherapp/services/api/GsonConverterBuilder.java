package vn.luongvo.weatherapp.services.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

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
                    .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) ->
                            new Date(json.getAsLong() * 1000))
                    .create();
        }
        return gson;
    }
}
