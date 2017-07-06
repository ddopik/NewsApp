package com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.MainAppModule;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ddopikMain on 3/1/2017.
 */

@Singleton
@Component(modules = {MainAppModule.class})
public interface AppComponent {
    void inject(MainApp mainApp);

    MainApp myApp(); ///Now MainApp instance will be Shared to other Models Sharing this Componanets
                    ///as Model Method won't   seen by other Components
                   ///The questions is how this method will injected From it's  Model as there is no @Inject
                  ///and how External Model got it as it has not @provide
}
