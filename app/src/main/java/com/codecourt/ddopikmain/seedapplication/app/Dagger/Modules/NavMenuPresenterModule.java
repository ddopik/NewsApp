package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsNetWorkControllerScope;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NavMenuPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikLaptop on 3/2/2017.
 */
@Module
public class NavMenuPresenterModule {


    @Provides
    @NewsNetWorkControllerScope
    public NavMenuPresenter provideNavMenuPresenter()
    {
        return  new NavMenuPresenter();
    }


}
