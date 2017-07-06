package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsNetWorkControllerScope;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.Util.GsonFeedParser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikMain on 2/28/2017.
 */
@Module
public class JsonFeedParserModule {

    @Provides
    @NewsNetWorkControllerScope
    public GsonFeedParser provideJsonFeedParser()
    {
        return new GsonFeedParser();
    }

}
