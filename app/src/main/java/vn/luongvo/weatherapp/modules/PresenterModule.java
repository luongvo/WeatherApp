package vn.luongvo.weatherapp.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.luongvo.weatherapp.ui.main.MainContact;
import vn.luongvo.weatherapp.ui.main.MainPresenter;

/**
 * Modules aren't used by you directly, they're used by Dagger. @Provides annotated methods are
 * used to lookup classes during injection. @Singleton indicates that only one instance of the object
 * is used, and used everywhere when injected.
 * <p>
 * Created by luongvo on 8/7/17.
 */

@Module
public class PresenterModule {

    @Provides
    @Singleton
    static MainContact.Presenter providesMain(MainContact.Interactor interactor) {
        return new MainPresenter(interactor);
    }
}
