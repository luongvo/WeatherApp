package vn.luongvo.weatherapp.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import vn.luongvo.weatherapp.dto.City;
import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.services.api.GsonConverterBuilder;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;
import vn.luongvo.weatherapp.ui.base.BasePresenter;
import vn.luongvo.weatherapp.utils.CollectionUtils;

/**
 * Created by luongvo on 8/7/18.
 */
public class MainPresenter extends BasePresenter implements MainContact.Presenter,
        MainContact.ActionListener {

    private Context context;
    private MainContact.View view;
    private MainContact.Interactor interactor;

    private WeatherInfo todayWeatherInfo;
    private List<WeatherInfo> forecasts = new ArrayList<>();

    public MainPresenter(MainContact.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onCreate(@NonNull MainContact.View view, @NonNull Context context) {
        this.context = context;
        this.view = view;
        view.initUI(forecasts);

        loadSelectedCity();
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainContact.View.REQUEST_SETTINGS && resultCode == Activity.RESULT_OK) {
            loadSelectedCity();
        }
    }

    @Override
    public void onItemClicked(@NonNull WeatherInfo weatherInfo) {
        view.openDetailScreen(weatherInfo);
    }

    private void loadSelectedCity() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        City city = GsonConverterBuilder.build().fromJson(sp.getString("city", ""), City.class);
        if (city == null) {
            view.openSettingsScreen();
        } else {
            executeGetCurrentWeather(city.getId());
        }
    }

    private void executeGetCurrentWeather(long cityId) {
        view.showLoadingDialog();
        interactor.getCurrentWeather(cityId, new OnAPIListener<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo result) {
                todayWeatherInfo = result;
                view.updateCurrentWeather(todayWeatherInfo);
            }

            @Override
            public void onCallFinished() {
                view.dismissLoadingDialog();

                executeGetForecasts(cityId);
            }
        });
    }

    private void executeGetForecasts(long cityId) {
        view.showLoadingDialog();
        interactor.getForecasts3hrs(cityId, new OnAPIListener<Forecast>() {
            @Override
            public void onSuccess(Forecast result) {
                forecasts.clear();
                if (!CollectionUtils.isEmpty(result.getList())) {
                    int i = 0;
                    while (i < result.getList().size()) {
                        // sometimes this api returns today at first
                        if (!todayWeatherInfo.getDayOfWeek(context).equals(result.getList().get(i).getDayOfWeek(context)))
                            forecasts.add(result.getList().get(i));
                        /*
                         FIXME
                         we can not use https://openweathermap.org/forecast16 as free plan
                         use https://openweathermap.org/forecast5 instead
                         just get first forecast and ignores the rest of the day
                          */
                        i += 8;
                    }
                }
                view.refreshForecastList();
            }

            @Override
            public void onFailure(int responseCode, @NonNull ErrorResponse errorResponse) {
                view.showFailureDialog(errorResponse.getMessage());
            }

            @Override
            public void onServerFailure() {
                view.showServerFailureDialog();
            }

            @Override
            public void onCallFinished() {
                view.dismissLoadingDialog();
            }
        });
    }
}
