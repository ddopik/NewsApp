package com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes;

import android.view.View;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddopikMain on 3/6/2017.
 */

public class MessageView {

    private View msgView;
    private String mMessage;
    private List<FeedItem> feedItems = new ArrayList<FeedItem>();


    public void setFeedItem(FeedItem feedItem) {

        if (this.feedItems.size() == 0) {
            this.feedItems.add(0, feedItem);
        } else {
            this.feedItems.set(0, feedItem);
        }
    }

    public List<FeedItem> getFeedItem() {
        return feedItems;
    }


    public void setMsgView(View msgView) {
        this.msgView = msgView;
    }

    public View getMsgView() {
        return msgView;
    }


    public void setTag(String ms) {
        this.mMessage = ms;
    }

    public String getmMessage() {
        return mMessage;
    }
}
