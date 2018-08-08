package vn.luongvo.weatherapp.ui.settings;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.ui.base.BasePresenter;

/**
 * Created by luongvo on 8/9/18.
 */
public class SettingsPresenter extends BasePresenter implements SettingsContact.Presenter {

    private SettingsContact.View view;

    @Override
    public void onCreate(@NonNull SettingsContact.View view) {
        this.view = view;

        view.initUI();
    }
}
