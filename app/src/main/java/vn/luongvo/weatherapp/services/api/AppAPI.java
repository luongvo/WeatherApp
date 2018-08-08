package vn.luongvo.weatherapp.services.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;

/**
 * Created by luongvo on 8/7/18.
 */
public interface AppAPI {

    @GET("weather")
    Call<WeatherInfo> getCurrentWeather(@Query("id") long cityId,
                                        @Query("units") String units,
                                        @Query("appid") String appId);

    @GET("forecast")
    Call<Forecast> getForecast3hrs(@Query("id") long cityId,
                                   @Query("units") String units,
                                   @Query("appid") String appId);
}

