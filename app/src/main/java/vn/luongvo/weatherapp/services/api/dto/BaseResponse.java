package vn.luongvo.weatherapp.services.api.dto;

/**
 * Created by luongvo on 8/9/18.
 */
public abstract class BaseResponse {

    private int cod;
    private String message;

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }
}
