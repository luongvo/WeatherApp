package vn.luongvo.weatherapp.ui.settings;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import javax.inject.Inject;

import vn.luongvo.weatherapp.App;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.databinding.ActivitySettingsBinding;
import vn.luongvo.weatherapp.dto.City;
import vn.luongvo.weatherapp.ui.base.BaseActivity;

/**
 * Created by luongvo on 8/9/18.
 */
@DataBound
@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity implements SettingsContact.View {

    @BindingObject
    ActivitySettingsBinding binding;
    @Inject
    SettingsContact.Presenter presenter;

    private CityAdapter adapter;

    @Override
    public void afterInject() {
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void afterView() {
        presenter.onCreate(this, this);
    }

    @Override
    public void initUI(@NonNull List<City> cities) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new CityAdapter(cities, (SettingsContact.ActionListener) presenter);
        binding.rvCity.setAdapter(adapter);
        binding.rvCity.setHasFixedSize(true);
        binding.rvCity.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void refreshDataList() {
        adapter.notifyDataSetChanged();
    }

    @Click(R.id.bt_search)
    void onChangeAvatarClick() {
        presenter.searchCity(binding.etSearch.getText().toString());
    }
}
