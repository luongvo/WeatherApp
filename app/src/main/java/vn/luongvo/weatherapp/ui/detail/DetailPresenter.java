package vn.luongvo.weatherapp.ui.detail;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.ui.base.BasePresenter;

/**
 * Created by luongvo on 8/9/18.
 */
public class DetailPresenter extends BasePresenter implements DetailContact.Presenter {

    private DetailContact.View view;

    private WeatherInfo weatherInfo;

    @Override
    public void onCreate(@NonNull DetailContact.View view, @NonNull WeatherInfo weatherInfo) {
        this.view = view;
        this.weatherInfo = weatherInfo;

        view.initUI(weatherInfo);
    }
}
