package vn.luongvo.weatherapp.ui.main;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import vn.luongvo.weatherapp.App;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.databinding.ActivityMainBinding;
import vn.luongvo.weatherapp.ui.base.BaseActivity;

/**
 * Created by luongvo on 5/14/18.
 */
@DataBound
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainContact.View {

    @BindingObject
    ActivityMainBinding binding;

    @Inject
    MainContact.Presenter presenter;

    @Override
    public void afterInject() {
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void afterView() {
        presenter.onCreate(this);
    }

    @Override
    public void initUI() {

    }
}
