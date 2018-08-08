package vn.luongvo.weatherapp.ui.detail;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.ui.base.BaseActivityContract;

/**
 * Created by luongvo on 8/9/18.
 */
public interface DetailContact {

    interface View extends BaseActivityContract.View {

        void initUI(@NonNull WeatherInfo weatherInfo);
    }

    interface Presenter extends BaseActivityContract.Presenter {

        void onCreate(@NonNull View view, @NonNull WeatherInfo weatherInfo);
    }

    interface Interactor extends BaseActivityContract.Interactor {
    }
}
