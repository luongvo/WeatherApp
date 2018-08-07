package vn.luongvo.weatherapp;

import org.androidannotations.annotations.BindingObject;
import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EActivity;

import vn.luongvo.weatherapp.databinding.ActivityMainBinding;
import vn.luongvo.weatherapp.ui.base.BaseActivity;

/**
 * Created by luongvo on 8/7/18.
 */
@DataBound
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindingObject
    ActivityMainBinding binding;

    @Override
    public void afterInject() {

    }

    @Override
    public void afterView() {

    }
}
