package vn.luongvo.weatherapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luongvo on 8/9/18.
 */
public class Wind implements Parcelable {

    private float speed;
    private float deg;

    protected Wind(Parcel in) {
        speed = in.readFloat();
        deg = in.readFloat();
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(speed);
        parcel.writeFloat(deg);
    }

    public float getSpeed() {
        return speed;
    }

    public float getSpeedKmh() {
        return speed * 3.6f;
    }

    public float getDeg() {
        return deg;
    }
}
