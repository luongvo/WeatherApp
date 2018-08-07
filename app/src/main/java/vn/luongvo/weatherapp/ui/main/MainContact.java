package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.ui.base.BaseActivityContract;

/**
 * Created by luongvo on 8/7/18.
 */
public interface MainContact {

    interface View extends BaseActivityContract.View {

        void initUI();
    }

    interface Presenter extends BaseActivityContract.Presenter {

        void onCreate(@NonNull View view);
    }

    interface Interactor extends BaseActivityContract.Interactor {

        void getCurrentWeather(@NonNull String cityName, @NonNull OnAPIListener<String> listener);
    }
}
