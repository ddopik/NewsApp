package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsNetWorkControllerScope;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes.MessageView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikMain on 3/6/2017.
 */
@Module
public class FragmentsSectionsModule {



    @Provides
    @NewsNetWorkControllerScope
    public MessageView ProvideessageView()
    {
        return new MessageView();
    }



}
