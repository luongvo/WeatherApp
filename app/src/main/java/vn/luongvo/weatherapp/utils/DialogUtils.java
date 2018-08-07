package vn.luongvo.weatherapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;


/**
 * @author luongvo
 */
public class DialogUtils {

    private static DialogUtils instance;

    private ProgressDialog progressDialog;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }

    public void showLoadingDialog(@NonNull Context context, int message) {
        if (!canShowDialog(context)) return;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(message));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissLoadingDialog() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (IllegalArgumentException ex) {
                Log.e(getClass().getSimpleName(), "Activity is already finished, no need dismissing dialog");
            }
        }
    }

    public static AlertDialog.Builder buildAlert(@NonNull Context context, String title, @NonNull String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        return builder;
    }

    /**
     * Check whether can show the dialog or not by checking the activity is alive
     */
    private static boolean canShowDialog(@NonNull Context context) {
        return context instanceof Activity && !((Activity) context).isFinishing();
    }
}
