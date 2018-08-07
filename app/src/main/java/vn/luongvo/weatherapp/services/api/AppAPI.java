package vn.luongvo.weatherapp.services.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luongvo on 8/7/18.
 */
public interface AppAPI {

    @GET("weather")
    Call<String> getCurrentWeatherByCityName(@Query("q") String q,
                                             @Query("appid") String appId);
}

