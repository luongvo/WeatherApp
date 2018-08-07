package vn.luongvo.weatherapp.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;


/**
 * Created by luongvo on 8/7/18.
 */
public interface BaseContract {

    interface View {

        void afterInject();

        void afterView();

        void showLoadingDialog();

        void showLoadingDialog(@StringRes int message);

        void dismissLoadingDialog();

        void showFailureDialog(int message);

        void showFailureDialog(@NonNull String message);

        void showServerFailureDialog();

        void showToast(int message);

        void showToast(@NonNull String message);
    }

    interface Presenter {
    }

    interface Interactor {
    }
}
