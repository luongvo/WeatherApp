package vn.luongvo.weatherapp.dto;

/**
 * Created by luongvo on 8/9/18.
 */
public class City {

    private long id;
    private String name;
    private Sys sys;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }
}
