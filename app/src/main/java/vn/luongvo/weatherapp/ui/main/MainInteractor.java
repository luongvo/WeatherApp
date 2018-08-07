package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.BuildConfig;
import vn.luongvo.weatherapp.services.api.APIService;
import vn.luongvo.weatherapp.services.api.BaseCallback;
import vn.luongvo.weatherapp.services.api.OnAPIListener;

/**
 * Created by luongvo on 8/7/18.
 */
public class MainInteractor implements MainContact.Interactor {

    @Override
    public void getCurrentWeather(@NonNull String cityName, @NonNull OnAPIListener<String> listener) {
        BaseCallback<String> callback = new BaseCallback<>(listener);

        APIService.getInstance().getApi().getCurrentWeatherByCityName(cityName, BuildConfig.API_KEY)
                .enqueue(callback);
    }
}
