package vn.luongvo.weatherapp.ui.settings;

import android.support.annotation.NonNull;

import java.util.List;

import vn.luongvo.weatherapp.dto.City;
import vn.luongvo.weatherapp.dto.CityResult;
import vn.luongvo.weatherapp.services.api.OnAPIListener;
import vn.luongvo.weatherapp.ui.base.BaseActivityContract;

/**
 * Created by luongvo on 8/9/18.
 */
public interface SettingsContact {

    interface View extends BaseActivityContract.View {

        void initUI(@NonNull List<City> cities);

        void refreshDataList();
    }

    interface Presenter extends BaseActivityContract.Presenter {

        void onCreate(@NonNull View view);

        void searchCity(String searchStr);
    }

    interface Interactor extends BaseActivityContract.Interactor {

        String TYPE = "like";
        String SORT = "population";
        int COUNT = 30;

        void findCity(String cityName, @NonNull OnAPIListener<CityResult> listener);
    }

    interface ActionListener {

        void onItemClicked(@NonNull City city);
    }
}
