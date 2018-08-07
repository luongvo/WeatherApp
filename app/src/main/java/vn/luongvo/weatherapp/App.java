package vn.luongvo.weatherapp;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

import javax.inject.Singleton;

import dagger.Component;
import vn.luongvo.weatherapp.modules.InteractorModule;
import vn.luongvo.weatherapp.modules.PresenterModule;
import vn.luongvo.weatherapp.ui.main.MainActivity;

/**
 * Created by luongvo on 8/7/18.
 */
@EApplication
public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApp_ApplicationComponent.builder().build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    /**
     * Newly added modules must be added to the @Component annotation here. You must also provide
     * further inject() methods for new classes that want to perform injection.
     */
    @Singleton
    @Component(modules = {InteractorModule.class, PresenterModule.class})
    public interface ApplicationComponent {

        void inject(MainActivity activity);
    }
}
