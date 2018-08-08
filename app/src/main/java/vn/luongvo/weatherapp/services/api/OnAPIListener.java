package vn.luongvo.weatherapp.services.api;

import android.support.annotation.NonNull;

import vn.luongvo.weatherapp.services.api.dto.ErrorResponse;

/**
 * Created by luongvo on 8/7/18.
 */
public interface OnAPIListener<T> {

    void onSuccess(T result);

    default void onFailure(int responseCode, @NonNull ErrorResponse errorResponse) {
    }

    default void onServerFailure() {
    }

    default void onCallFinished() {
    }
}
