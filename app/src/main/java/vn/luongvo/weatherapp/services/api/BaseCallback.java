package vn.luongvo.weatherapp.services.api;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;
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
                listener.onCallFinished();
            } else {
                // try to parse ErrorResponse
                ErrorResponse errorResponse = null;
                if (response.errorBody() != null) {
                    Converter<ResponseBody, ErrorResponse> errorConverter =
                            APIService.getInstance().getRetrofit()
                                    .responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                    try {
                        errorResponse = errorConverter.convert(response.errorBody());
                    } catch (Exception e) {
                        LogUtil.e(TAG, e.getMessage());
                    }
                }
                if (errorResponse == null) {
                    errorResponse = new ErrorResponse();
                }

                listener.onFailure(response.code(), errorResponse);
                listener.onCallFinished();
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
                listener.onCallFinished();
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
