package com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsNetWorkControllerScope;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.NewsListFragmentAdapter;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NewsNetWorkController;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddopikLaptop on 3/2/2017.
 */
@Module
public class NewsListAdapterModule {

    @NewsNetWorkControllerScope ///Provide For Adapeter Not for User
    @Provides
    public List<FeedItem> provideJasonFeedItems(NewsNetWorkController newsNetWorkController)
    {

        return newsNetWorkController.getJasonFeedItems();
    }


    @NewsNetWorkControllerScope
    @Provides
    public NewsListFragmentAdapter providefeedListAdapter(NewsNetWorkController newsNetWorkController)
    {
        NewsListFragmentAdapter listAdapter = new NewsListFragmentAdapter(newsNetWorkController);
        return listAdapter;
    }


}
