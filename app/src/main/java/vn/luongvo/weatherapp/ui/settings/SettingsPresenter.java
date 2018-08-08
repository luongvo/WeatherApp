package vn.luongvo.weatherapp.ui.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import vn.luongvo.weatherapp.dto.City;
import vn.luongvo.weatherapp.dto.CityResult;
import vn.luongvo.weatherapp.services.api.GsonConverterBuilder;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;
import vn.luongvo.weatherapp.ui.base.BasePresenter;

/**
 * Created by luongvo on 8/9/18.
 */
public class SettingsPresenter extends BasePresenter implements SettingsContact.Presenter,
        SettingsContact.ActionListener {

    private SettingsContact.View view;
    private SettingsContact.Interactor interactor;

    private List<City> cities = new ArrayList<>();
    private SharedPreferences sp;

    public SettingsPresenter(SettingsContact.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onCreate(@NonNull SettingsContact.View view, @NonNull Context context) {
        this.view = view;

        sp = PreferenceManager.getDefaultSharedPreferences(context);
        // load saved city
        cities.clear();
        City city = GsonConverterBuilder.build().fromJson(sp.getString("city", ""), City.class);
        if (city != null) {
            cities.add(city);
        }

        view.initUI(cities);
    }

    @Override
    public void searchCity(String searchStr) {
        if (!TextUtils.isEmpty(searchStr)) {
            executeSearchCity(searchStr);
        }
    }

    @Override
    public void onItemClicked(@NonNull City city) {
        // save city
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("city", GsonConverterBuilder.build().toJson(city));
        editor.apply();

        view.finishScreen(Activity.RESULT_OK);
    }

    private void executeSearchCity(String searchStr) {
        view.showLoadingDialog();
        interactor.findCity(searchStr, new OnAPIListener<CityResult>() {
            @Override
            public void onSuccess(CityResult result) {
                cities.clear();
                cities.addAll(result.getList());
                view.refreshDataList();
            }

            @Override
            public void onFailure(int responseCode, @NonNull ErrorResponse errorResponse) {
                view.showToast(errorResponse.getMessage());
            }

            @Override
            public void onCallFinished() {
                view.dismissLoadingDialog();
            }
        });
    }
}
