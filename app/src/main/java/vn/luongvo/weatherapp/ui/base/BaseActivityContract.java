package vn.luongvo.weatherapp.ui.base;

import android.content.Intent;

/**
 * Created by luongvo on 8/7/18.
 */
public interface BaseActivityContract {

    interface View extends BaseContract.View {

        void setActivityResult(int resultCode);

        void finishScreen();

        void finishScreen(int resultCode);

        void finishScreen(int resultCode, Intent intent);
    }

    interface Presenter extends BaseContract.Presenter {
    }

    interface Interactor extends BaseContract.Interactor {
    }
}
