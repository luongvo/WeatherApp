package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.services.api.OnAPIListener;
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
        executeGetCurrentWeather("Ha Noi");
    }

    private void executeGetCurrentWeather(String cityName) {
        interactor.getCurrentWeather(cityName, new OnAPIListener<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.d(TAG, result);
            }
        });
    }
}
