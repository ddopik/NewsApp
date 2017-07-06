package com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes;

/**
 * Created by ddopi on 6/24/2017.
 */

public class EventBusMessage {



    private int itemID;
    private String shareUrl;
    private String newsHeadLine;
    private Boolean favourateItem=false;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Boolean eventBusMessage() {
        return favourateItem;
    }

    public void setFavourateItem(Boolean favourateItem) {
        this.favourateItem = favourateItem;
    }





    public String getNewsHeadLine() {
        return newsHeadLine;
    }

    public void setNewsHeadLine(String newsHeadLine) {
        this.newsHeadLine = newsHeadLine;
    }
    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }




}
