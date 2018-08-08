package vn.luongvo.weatherapp.dto;

import java.util.List;

import vn.luongvo.weatherapp.services.api.dto.BaseResponse;

/**
 * Created by luongvo on 8/9/18.
 */
public class CityResult extends BaseResponse {

    private List<City> list;

    public List<City> getList() {
        return list;
    }
}
