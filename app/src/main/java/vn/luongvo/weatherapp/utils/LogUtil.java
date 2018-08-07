package vn.luongvo.weatherapp.utils;

import android.util.Log;

import vn.luongvo.weatherapp.BuildConfig;

/**
 * Created by luongvo on 8/7/18.
 */
public class LogUtil {

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.w(tag, msg);
        }
    }
}
