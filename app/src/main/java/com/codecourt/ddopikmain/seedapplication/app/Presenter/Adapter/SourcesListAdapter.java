package com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.SourceNews;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.View.SourceList;
import com.codecourt.ddopikmain.seedapplication.R;

import UiComponanets.MultiSelectList.MultiSelectListAdapter.MultiSelectListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by ddopikMain on 3/29/2017.
 */

public class SourcesListAdapter extends MultiSelectListAdapter {

    private SourceList homeActivity;
    private RealmResults<SourceNews> sourcesList;
    public boolean isSelected[];


    @Override
    public boolean[] getDefaultList(RealmResults sourceList) {
        RealmResults<SourceNews> list=sourceList;
        this.isSelected = new boolean[sourceList.size()];
        for (int i = 0; i < sourceList.size(); i++) {
           this.isSelected[i] = list.get(i).isDefaultSource();

        }
        return this.isSelected;
    }

    @Override
    public String getSingSourceTextName(int sourceID) {
        return  sourcesList.get(sourceID).getSourceName();
    }

    @Override
    public boolean getSingleItemDefaultState(int itemId) {
        boolean defaultSourceState;
        MainApp.realm.beginTransaction();
        SourceNews sourceNews = MainApp.realm.where(SourceNews.class).equalTo("sourceID", itemId).findFirst();
        defaultSourceState = sourceNews.isDefaultSource();
        MainApp.realm.commitTransaction();
        return defaultSourceState;

    }

    public SourcesListAdapter(SourceList newsListActivity, RealmResults<SourceNews> sourceList) {
        super(newsListActivity,sourceList);
        this.homeActivity = newsListActivity;
        this.sourcesList = sourceList;
    }
    public boolean[] getSelectedFlags() {
        return isSelected;
    } ///should be ubstract





}
