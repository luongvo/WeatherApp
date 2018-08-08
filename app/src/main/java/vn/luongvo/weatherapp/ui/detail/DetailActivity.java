package vn.luongvo.weatherapp.ui.detail;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import javax.inject.Inject;

import vn.luongvo.weatherapp.App;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.databinding.ActivityDetailBinding;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.ui.base.BaseActivity;

/**
 * Created by luongvo on 8/9/18.
 */
@DataBound
@EActivity(R.layout.activity_detail)
public class DetailActivity extends BaseActivity implements DetailContact.View {

    @BindingObject
    ActivityDetailBinding binding;
    @Inject
    DetailContact.Presenter presenter;

    @Extra
    WeatherInfo weatherInfo;

    @Override
    public void afterInject() {
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void afterView() {
        presenter.onCreate(this, weatherInfo);
    }

    @Override
    public void initUI(@NonNull WeatherInfo weatherInfo) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        binding.setWeatherInfo(weatherInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
