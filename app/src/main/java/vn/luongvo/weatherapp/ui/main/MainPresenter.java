package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;
import vn.luongvo.weatherapp.ui.base.BasePresenter;
import vn.luongvo.weatherapp.utils.CollectionUtils;
import vn.luongvo.weatherapp.utils.LogUtil;

/**
 * Created by luongvo on 8/7/18.
 */
public class MainPresenter extends BasePresenter implements MainContact.Presenter,
        MainContact.ActionListener {

    private MainContact.View view;
    private MainContact.Interactor interactor;

    private List<WeatherInfo> forecasts = new ArrayList<>();

    public MainPresenter(MainContact.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onCreate(@NonNull MainContact.View view) {
        this.view = view;
        view.initUI(forecasts);

        // TODO
        executeGetCurrentWeather(1581130);
        executeGetForecasts(1581130);
    }

    @Override
    public void onItemClicked(@NonNull WeatherInfo weatherInfo) {
        view.showToast("TODO");
    }

    private void executeGetCurrentWeather(long cityId) {
        interactor.getCurrentWeather(cityId, new OnAPIListener<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo result) {
                LogUtil.d(TAG, result.toString());
            }
        });
    }

    private void executeGetForecasts(long cityId) {
        view.showLoadingDialog();
        interactor.getForecasts(cityId, FORECAST_DAY_NUM, new OnAPIListener<Forecast>() {
            @Override
            public void onSuccess(Forecast result) {
                forecasts.clear();
                if (!CollectionUtils.isEmpty(result.getList())) {
                    forecasts.addAll(result.getList());
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
