package vn.luongvo.weatherapp.ui.settings;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.ui.base.BaseActivityContract;

/**
 * Created by luongvo on 8/9/18.
 */
public interface SettingsContact {

    interface View extends BaseActivityContract.View {

        void initUI();
    }

    interface Presenter extends BaseActivityContract.Presenter {

        void onCreate(@NonNull View view);
    }

    interface Interactor extends BaseActivityContract.Interactor {
    }
}
