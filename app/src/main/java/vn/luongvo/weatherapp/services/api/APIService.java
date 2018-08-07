package vn.luongvo.weatherapp.services.api;

/**
 * Created by luongvo on 8/7/18.
 */
public class APIService extends BaseAPIService<APIService, AppAPI> {

    private static APIService instance;

    public static APIService getInstance() {
        if (instance == null) {
            instance = new APIService();
        }
        return instance;
    }

    private APIService() {
        super(AppAPI.class);
    }
}
