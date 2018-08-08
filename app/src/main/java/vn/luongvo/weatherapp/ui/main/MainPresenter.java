package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;
import vn.luongvo.weatherapp.ui.base.BasePresenter;
import vn.luongvo.weatherapp.utils.LogUtil;

/**
 * Created by luongvo on 8/7/18.
 */
public class MainPresenter extends BasePresenter implements MainContact.Presenter {

    private MainContact.View view;
    private MainContact.Interactor interactor;

    public MainPresenter(MainContact.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onCreate(@NonNull MainContact.View view) {
        this.view = view;
        view.initUI();

        // TODO
        executeGetCurrentWeather(1581130);
        executeForecasts(1581130);
    }

    private void executeGetCurrentWeather(long cityId) {
        interactor.getCurrentWeather(cityId, new OnAPIListener<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo result) {
                LogUtil.d(TAG, result.toString());
            }
        });
    }

    private void executeForecasts(long cityId) {
        interactor.getForecasts(cityId, FORECAST_DAY_NUM, new OnAPIListener<Forecast>() {
            @Override
            public void onSuccess(Forecast result) {
                LogUtil.d(TAG, result.toString());
            }

            @Override
            public void onFailure(int responseCode, ErrorResponse errorResponse) {
                LogUtil.d(TAG, errorResponse.toString());
            }
        });
    }
}
