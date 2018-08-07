package vn.luongvo.weatherapp.services.api;

/**
 * Created by luongvo on 8/7/18.
 */
public interface OnAPIListener<T> {

    void onSuccess(T result);

    default void onFailure(int responseCode) {
    }

    default void onServerFailure() {
    }
}
