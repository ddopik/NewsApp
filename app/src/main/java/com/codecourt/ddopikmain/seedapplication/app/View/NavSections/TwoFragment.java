package com.codecourt.ddopikmain.seedapplication.app.View.NavSections;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codecourt.ddopikmain.seedapplication.app.Model.SourceListModel;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.NewsListFragmentAdapter;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.EventBusMessage.ProgressState;
import com.codecourt.ddopikmain.seedapplication.app.View.MainActivity;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NewsNetWorkController;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes.MessageView;
import com.codecourt.ddopikmain.seedapplication.app.View.ViewIdenity.ViewPager_Fragment_ident;
import com.codecourt.ddopikmain.seedapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ddopikMain on 2/20/2017.
 */


public class TwoFragment extends android.support.v4.app.Fragment implements ViewPager_Fragment_ident,SwipeRefreshLayout.OnRefreshListener {



    ////Feed News Start here
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private ArrayList<FeedItem> feedItems;
    private SourceListModel sourceListModel;
    private List<HashMap> defaultSourcesCat;
    private View mainView;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Inject
    public NewsNetWorkController newsNetWorkController;

    public NewsListFragmentAdapter listAdapter;

    @Inject
    public MessageView messageView;


    @Override
    public FragmentActivity getFragmentContext() {
        return getActivity();
    }

    @Override
    public ArrayList<FeedItem> getFeedListItems() {
        return feedItems;
    }

    @Override
    public NewsListFragmentAdapter getFeedListAdapter() {
        return listAdapter;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setupInjection(); ///intiazlizing Dagger
        Log.e("FragmentTwo", "---->FragmentTwo onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("FragmentTwo", "---->FragmentTwo onCreateView");
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_one, container, false);
        this.swipeRefreshLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) this.mainView.findViewById(R.id.list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        listAdapter = new NewsListFragmentAdapter(newsNetWorkController);
        listAdapter.setHomeActivity(getActivity());
        feedItems = new ArrayList<FeedItem>();
        listAdapter.setFeedItems(feedItems);
        listView.setAdapter(listAdapter); /// I Know the Adapter is Empty now/// but the idea is to notify adapter when we have call back From Cash or From Volly


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);


        return mainView;
    }
    AdapterView.OnItemClickListener myListViewClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            messageView.setFeedItem(feedItems.get(arg2));
            ((MainActivity) getActivity()).launchSingleNewsFragment();
        }


    };

    @Override
    public void onResume() {
        Log.e("FragmentTwo", "---->FragmentTwo onResumed");
        super.onResume();
        this.loadData();
    }
    @Override
    public void onRefresh() {

//        Toast.makeText(getActivity(),"Fragment Two Refreashed",Toast.LENGTH_SHORT).show();
        this.loadData();
    }

    public void loadData() {
        ((MainActivity) getActivity()).connectionNotify();
        this.sourceListModel = new SourceListModel();
        getFeedListItems().clear(); ///clear all data before calling adapter Again
        defaultSourcesCat = sourceListModel.getSourceCat(2);
        if(sourceListModel.isDefaultSources() )
        {
            Log.e("FragmentTwo", "Selected Cat----->" + defaultSourcesCat.size());
            newsNetWorkController.StreamJSON(this, defaultSourcesCat, 2);
            listView.setOnItemClickListener(myListViewClicked);
            if( defaultSourcesCat.size()<= 0) {

                getFeedListAdapter().notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
        else
        {

            Toast.makeText(getActivity(),"Please Select at least on News Resource",Toast.LENGTH_LONG).show();
            getFeedListAdapter().notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);

        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN )
    public void glopalRefreash(ProgressState progressState)
    {
        if(progressState.isSetRefreash() && MainApp.isInternetAvailable())
        {
            this.loadData();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.e("FragmentTwo", "---->FragmentOne OnStop()");
    }
    protected void setupInjection() {

        try {
            ((MainActivity) getActivity()).newsControllerComponent.inject(this);
        }catch (Exception e)
        {
            Log.e("TwoFragment","------>Dagger injetion Errror");
        }

    }

}
