package vn.luongvo.weatherapp.ui.settings;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import vn.luongvo.weatherapp.App;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.databinding.ActivitySettingsBinding;
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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
