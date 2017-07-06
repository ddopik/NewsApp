package com.codecourt.ddopikmain.seedapplication.app.View.ViewIdenity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.NewsListFragmentAdapter;

import java.util.ArrayList;

/**
 * Created by ddopikMain on 3/5/2017.
 */

//public interface ViewPager_Fragment_ident  extends  ViewPager_Fragment{
public interface ViewPager_Fragment_ident   {


    public FragmentActivity getFragmentContext();
    public ArrayList<FeedItem> getFeedListItems();
    public NewsListFragmentAdapter getFeedListAdapter();
    public SwipeRefreshLayout getSwipeRefreshLayout();
}
