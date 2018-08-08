package vn.luongvo.weatherapp.utils;

import android.os.Parcel;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * @author luongvo
 */
public class DateUtils {

    public static boolean equals(@Nullable Date d1, @Nullable Date d2) {
        return d1 == null && d2 == null || d1 != null && d2 != null && d1.getTime() == d2.getTime();
    }

    @Nullable
    public static Date readDate(Parcel parcel) {
        long ms = parcel.readLong();
        if (ms == 0) return null;
        return new Date(ms);
    }

    public static void writeDate(Parcel parcel, @Nullable Date date) {
        parcel.writeLong(date == null ? 0 : date.getTime());
    }
}
