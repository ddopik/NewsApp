package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.AppComponent;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikMain on 2/28/2017.
 */
@Module
public class MainAppModule {
   private MainApp mainApp;

    public MainAppModule(MainApp mainApp) {

        this.mainApp = mainApp;
    }

    @Provides
    @Singleton
    public MainApp provideApplication() {

        return this.mainApp;
    }
    @Provides
    @Singleton
    public AppComponent provideAppCompomamets()
    {
        return mainApp.getAppComponent();
    }



}
/////we are providing Application instance through all Application