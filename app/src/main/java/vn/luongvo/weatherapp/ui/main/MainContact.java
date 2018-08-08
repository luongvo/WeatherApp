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

        void initUI(@NonNull List<WeatherInfo> forecasts);

        void refreshForecastList();

        void openDetailScreen(@NonNull WeatherInfo weatherInfo);
    }

    interface Presenter extends BaseActivityContract.Presenter {

        void onCreate(@NonNull View view);
    }

    interface Interactor extends BaseActivityContract.Interactor {

        String UNITS = "metric";

        void getCurrentWeather(long cityId, @NonNull OnAPIListener<WeatherInfo> listener);

        void getForecasts3hrs(long cityId, @NonNull OnAPIListener<Forecast> listener);
    }

    interface ActionListener {

        void onItemClicked(@NonNull WeatherInfo weatherInfo);
    }
}
