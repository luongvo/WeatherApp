package vn.luongvo.weatherapp.dto;

/**
 * Created by luongvo on 8/9/18.
 */
public class Weather {

    private long id;
    private String main;
    private String description;
    private String icon;

    public long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
