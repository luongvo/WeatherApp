package vn.luongvo.weatherapp.ui.settings;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.BuildConfig;
import vn.luongvo.weatherapp.dto.CityResult;
import vn.luongvo.weatherapp.services.api.APIService;
import vn.luongvo.weatherapp.services.api.BaseCallback;
import vn.luongvo.weatherapp.services.api.OnAPIListener;

/**
 * Created by luongvo on 8/7/18.
 */
public class SettingsInteractor implements SettingsContact.Interactor {

    @Override
    public void findCity(String cityName, @NonNull OnAPIListener<CityResult> listener) {
        BaseCallback<CityResult> callback = new BaseCallback<>(listener);

        APIService.getInstance().getApi().findCity(cityName, TYPE, SORT, COUNT, BuildConfig.API_KEY)
                .enqueue(callback);
    }
}
