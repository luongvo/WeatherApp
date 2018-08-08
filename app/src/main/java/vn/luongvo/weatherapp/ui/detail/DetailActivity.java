package vn.luongvo.weatherapp.ui.detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
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

    private ShareActionProvider shareActionProvider;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntent(weatherInfo);

        return true;
    }

    private void setShareIntent(@NonNull WeatherInfo weatherInfo) {
        if (shareActionProvider != null) {
            String content = weatherInfo.getDayOfWeek(this) + "\n" +
                    weatherInfo.getDateStr() + "\n" +
                    getString(R.string._celsius, Math.round(weatherInfo.getMain().getTempMax())) + "\n" +
                    getString(R.string._celsius, Math.round(weatherInfo.getMain().getTempMin())) + "\n" +
                    weatherInfo.getWeathers().get(0).getMain() + "\n" +
                    getString(R.string.humidity_, Math.round(weatherInfo.getMain().getHumidity())) + "\n" +
                    getString(R.string.pressure_, Math.round(weatherInfo.getMain().getPressure())) + "\n" +
                    getString(R.string.wind_, Math.round(weatherInfo.getWind().getSpeedKmh())) + "\n";

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, content);

            shareActionProvider.setShareIntent(intent);
        }
    }
}
