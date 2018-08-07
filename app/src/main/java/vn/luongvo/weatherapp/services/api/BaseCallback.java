package vn.luongvo.weatherapp.services.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.luongvo.weatherapp.utils.LogUtil;

/**
 * Created by luongvo on 8/7/18.
 */
public class BaseCallback<T> implements Callback<T> {

    private final String TAG = this.getClass().getName();

    private OnAPIListener<T> listener;
    private boolean ignored = false;

    public BaseCallback() {
    }

    public BaseCallback(OnAPIListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (ignored) {
            LogUtil.i(TAG, "Ignored: " + call.toString());
        } else if (listener != null) {
            if (response.isSuccessful()) {
                listener.onSuccess(response.body());
            } else {
                // try to parse ErrorResponse...

                listener.onFailure(response.code());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (ignored) {
            LogUtil.i(TAG, "Ignored: " + call.toString());
        } else {
            LogUtil.e(TAG, t.getMessage());
            if (listener != null) {
                listener.onServerFailure();
            }
        }
    }

    /**
     * Mark this callback will be ignored when finished.
     * {@link OnAPIListener} calling will be ignored
     */
    public void setIgnored() {
        this.ignored = true;
    }
}
