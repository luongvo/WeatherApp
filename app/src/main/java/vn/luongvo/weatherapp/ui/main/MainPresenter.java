package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;

/**
 * Created by luongvo on 5/15/18.
 */
public class MainPresenter implements MainContact.Presenter {

    private MainContact.View view;

    @Override
    public void onCreate(@NonNull MainContact.View view) {
        this.view = view;
        view.initUI();
    }
}
