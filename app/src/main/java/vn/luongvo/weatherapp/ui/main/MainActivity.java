package vn.luongvo.weatherapp.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

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
import vn.luongvo.weatherapp.ui.detail.DetailActivity_;
import vn.luongvo.weatherapp.ui.settings.SettingsActivity_;

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
        presenter.onCreate(this, this);
    }

    @Override
    public void initUI(@NonNull List<WeatherInfo> forecasts) {
        // setup transparent toolbar for setting menu
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        adapter = new ForecastAdapter(forecasts, (MainContact.ActionListener) presenter);
        binding.rvForecast.setAdapter(adapter);
        binding.rvForecast.setHasFixedSize(true);
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            openSettingsScreen();
        }
        return true;
    }

    @Override
    public void updateCurrentWeather(@NonNull WeatherInfo weatherInfo) {
        binding.setWeatherInfo(weatherInfo);
        binding.setListener((MainContact.ActionListener) presenter);
    }

    @Override
    public void refreshForecastList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openDetailScreen(@NonNull WeatherInfo weatherInfo) {
        DetailActivity_.intent(this).weatherInfo(weatherInfo).start();
    }

    @Override
    public void openSettingsScreen() {
        SettingsActivity_.intent(this).start();
    }
}
