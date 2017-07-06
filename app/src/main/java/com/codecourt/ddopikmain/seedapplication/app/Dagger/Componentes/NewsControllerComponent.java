package com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes;

/**
 * Created by ddopikMain on 2/28/2017.
 */

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.NewsListAdapterModule;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.FragmentsSectionsModule;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.JsonFeedParserModule;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.NavMenuPresenterModule;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.NewsNetWorkControllerModule;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.SourcesListAdapterModule;
import com.codecourt.ddopikmain.seedapplication.app.View.MainActivity;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.FourFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.OneFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.ThreeFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.TwoFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.SourceList;
import com.codecourt.ddopikmain.seedapplication.app.View.SingleNewsActivity;

import dagger.Component;

@NewsNetWorkControllerScope
@Component(dependencies={AppComponent.class},
        modules={JsonFeedParserModule.class,NewsNetWorkControllerModule.class, NewsListAdapterModule.class,NavMenuPresenterModule.class,FragmentsSectionsModule.class})
public interface NewsControllerComponent  {
    void inject(MainActivity mainActivity);
    void inject(OneFragment oneFragment);
    void inject(TwoFragment twoFragment);
    void inject(ThreeFragment ThreeFragment);
    void inject(FourFragment fourFragment);
    void inject(SingleNewsActivity singleNewsActivity);
    void inject (SourceList newsList);

}
