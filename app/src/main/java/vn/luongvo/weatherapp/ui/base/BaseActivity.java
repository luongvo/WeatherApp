package vn.luongvo.weatherapp.ui.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.utils.DialogUtils;

/**
 * Created by luongvo on 8/7/18.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements BaseActivityContract.View {

    protected final String TAG = this.getClass().getSimpleName();

    @AfterInject
    protected void initInjectBaseActivity() {
        this.afterInject();
    }

    @AfterViews
    protected void initViewBaseActivity() {
        this.afterView();
    }

    @Override
    public void setActivityResult(int resultCode) {
        setResult(resultCode);
    }

    @Override
    public void finishScreen() {
        finish();
    }

    @Override
    public void finishScreen(int resultCode) {
        finishScreen(resultCode, null);
    }

    @Override
    public void finishScreen(int resultCode, Intent intent) {
        setResult(resultCode, intent);
        finishScreen();
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(R.string.loading);
    }

    @Override
    public void showLoadingDialog(@StringRes int message) {
        DialogUtils.getInstance().showLoadingDialog(this, message);
    }

    @Override
    public void dismissLoadingDialog() {
        DialogUtils.getInstance().dismissLoadingDialog();
    }

    @Override
    public void showFailureDialog(int message) {
        showFailureDialog(getString(message));
    }

    @Override
    public void showFailureDialog(@NonNull String message) {
        DialogUtils.buildAlert(this, null, message);
    }

    @Override
    public void showServerFailureDialog() {
        showFailureDialog(getString(R.string.something_went_wrong_could_not_get_response));
    }

    @Override
    public void showToast(int id) {
        showToast(getString(id));
    }

    @Override
    public void showToast(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
