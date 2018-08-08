package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import java.util.List;

import vn.luongvo.weatherapp.dto.Forecast;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.ui.base.BaseActivityContract;

/**
 * Created by luongvo on 8/7/18.
 */
public interface MainContact {

    interface View extends BaseActivityContract.View {

        void initUI(List<WeatherInfo> forecasts);

        void refreshForecastList();
    }

    interface Presenter extends BaseActivityContract.Presenter {

        int FORECAST_DAY_NUM = 14;

        void onCreate(@NonNull View view);
    }

    interface Interactor extends BaseActivityContract.Interactor {

        void getCurrentWeather(long cityId, @NonNull OnAPIListener<WeatherInfo> listener);

        void getForecasts(long cityId, int forecastDays, @NonNull OnAPIListener<Forecast> listener);
    }

    interface ActionListener {

        void onItemClicked(@NonNull WeatherInfo weatherInfo);
    }
}
