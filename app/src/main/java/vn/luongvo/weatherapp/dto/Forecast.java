package vn.luongvo.weatherapp.dto;

import java.util.List;

import vn.luongvo.weatherapp.services.api.dto.BaseResponse;

/**
 * Created by luongvo on 8/9/18.
 */
public class Forecast extends BaseResponse {

    private int cnt;
    private List<WeatherInfo> list;

    public int getCnt() {
        return cnt;
    }

    public List<WeatherInfo> getList() {
        return list;
    }
}
