package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.android.volley.toolbox.ImageLoader;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsNetWorkControllerScope;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.Util.GsonFeedParser;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NewsNetWorkController;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikMain on 2/28/2017.
 */


@Module
public class NewsNetWorkControllerModule {

    @Provides
    @NewsNetWorkControllerScope
    public NewsNetWorkController ProvideNewsNetWorkController(MainApp mApplication,GsonFeedParser jsonObj) ///Application are Provided by MainAppModule
    {
        return new NewsNetWorkController(mApplication.getMainAppApplication(),jsonObj);
    }


    @NewsNetWorkControllerScope ///Provide For Adapeter Not for User
    @Provides
    public ImageLoader ProvideImageLoader(NewsNetWorkController newsNetWorkController)
    {
        return MainApp.mImageLoader;
    }




}
