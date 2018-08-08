package vn.luongvo.weatherapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luongvo on 8/9/18.
 */
public class Clouds implements Parcelable {

    private int all;

    protected Clouds(Parcel in) {
        all = in.readInt();
    }

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(all);
    }

    public int getAll() {
        return all;
    }
}
