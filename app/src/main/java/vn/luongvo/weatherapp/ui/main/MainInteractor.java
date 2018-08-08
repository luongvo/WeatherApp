package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.BuildConfig;
import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.services.api.APIService;
import vn.luongvo.weatherapp.services.api.BaseCallback;
import vn.luongvo.weatherapp.services.api.OnAPIListener;

/**
 * Created by luongvo on 8/7/18.
 */
public class MainInteractor implements MainContact.Interactor {

    @Override
    public void getCurrentWeather(long cityId, @NonNull OnAPIListener<WeatherInfo> listener) {
        BaseCallback<WeatherInfo> callback = new BaseCallback<>(listener);

        APIService.getInstance().getApi().getCurrentWeather(cityId, UNITS, BuildConfig.API_KEY)
                .enqueue(callback);
    }

    @Override
    public void getForecasts3hrs(long cityId, @NonNull OnAPIListener<Forecast> listener) {
        BaseCallback<Forecast> callback = new BaseCallback<>(listener);

        APIService.getInstance().getApi().getForecast3hrs(cityId, UNITS, BuildConfig.API_KEY)
                .enqueue(callback);
    }
}
