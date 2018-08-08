package vn.luongvo.weatherapp.ui.main;

import android.support.v7.widget.LinearLayoutManager;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import javax.inject.Inject;

import vn.luongvo.weatherapp.App;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.databinding.ActivityMainBinding;
import vn.luongvo.weatherapp.dto.WeatherInfo;
import vn.luongvo.weatherapp.ui.base.BaseActivity;

/**
 * Created by luongvo on 8/7/18.
 */
@DataBound
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainContact.View {

    @BindingObject
    ActivityMainBinding binding;
    @Inject
    MainContact.Presenter presenter;

    private ForecastAdapter adapter;

    @Override
    public void afterInject() {
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void afterView() {
        presenter.onCreate(this);
    }

    @Override
    public void initUI(List<WeatherInfo> forecasts) {
        adapter = new ForecastAdapter(forecasts, (MainContact.ActionListener) presenter);
        binding.rvForecast.setAdapter(adapter);
        binding.rvForecast.setHasFixedSize(true);
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void refreshForecastList() {
        adapter.notifyDataSetChanged();
    }
}
