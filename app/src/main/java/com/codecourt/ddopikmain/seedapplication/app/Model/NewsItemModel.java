package com.codecourt.ddopikmain.seedapplication.app.Model;

import android.util.Log;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ddopikMain on 4/17/2017.
 */

public class NewsItemModel {


    public List<FeedItem> getFeedItem(int catID, int sourceCatID) {

        List<FeedItem> feedItems = new ArrayList<FeedItem>();
//        RealmResults<FeedItem> results = MainApp.realm.where(FeedItem.class).equalTo("fK_sectionsID", catID).equalTo("fK_SourceCatID", sourceCatID).findAll();
        try {
            RealmResults<FeedItem> results = MainApp.realm.where(FeedItem.class).equalTo("fK_sectionsID", catID).equalTo("fK_SourceCatID", sourceCatID).findAll();
            feedItems = MainApp.realm.copyFromRealm(results);
        } catch (Exception e) {
            Log.e("NewsItemModel", "---------->No Items Returned From getFeedItem() Method");
        }

        return feedItems;
    }

    public List<FeedItem> getFavFeedItem() {
        List<FeedItem> feedItems = new ArrayList<FeedItem>();

        try {
            RealmResults<FeedItem> results = MainApp.realm.where(FeedItem.class).equalTo("favourateItem", true).findAll();
            feedItems = MainApp.realm.copyFromRealm(results);
        } catch (Exception e) {
            Log.e("NewsItemModel", "---------->No Items Returned From getFeedItem() Method");
        }

        return feedItems;
    }

    public FeedItem getSingleNewsItem(int id) {
        return MainApp.realm.where(FeedItem.class).equalTo("id", id).findFirst();
    }

    public void saveFeedItemToRealm(FeedItem feedItem) {
        try {
            int id = getSingleNewsItem(feedItem.getId()).getId();
            if (id < 0) {

            }
        }catch (Exception e)
        {
            MainApp.realm.beginTransaction();
            MainApp.realm.copyToRealmOrUpdate(feedItem); // Persist unmanaged objects
            MainApp.realm.commitTransaction();

        }


    }

    public void setFavFeedItem(int id, Boolean state) {
        FeedItem feedItem = getSingleNewsItem(id);
        MainApp.realm.beginTransaction();
        feedItem.setAsFav(state);
        MainApp.realm.copyToRealmOrUpdate(feedItem);
        MainApp.realm.commitTransaction();
    }
}
