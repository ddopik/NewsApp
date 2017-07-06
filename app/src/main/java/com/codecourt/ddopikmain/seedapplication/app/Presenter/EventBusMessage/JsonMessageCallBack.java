package com.codecourt.ddopikmain.seedapplication.app.Presenter.EventBusMessage;

import com.codecourt.ddopikmain.seedapplication.app.View.ViewIdenity.ViewPager_Fragment_ident;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ddopi on 6/28/2017.
 */

public class JsonMessageCallBack {


    private JSONObject urlObjResponse;
    private ViewPager_Fragment_ident activityContainer;
    private List<HashMap> defaultSources;
    private int itemsCat;
    private int itemPosition;

    public JSONObject getUrlObjResponse() {
        return urlObjResponse;
    }

    public void setUrlObjResponse(JSONObject urlObjResponse) {
        this.urlObjResponse = urlObjResponse;
    }

    public ViewPager_Fragment_ident getActivityContainer() {
        return activityContainer;
    }

    public void setActivityContainer(ViewPager_Fragment_ident activityContainer) {
        this.activityContainer = activityContainer;
    }

    public List<HashMap> getDefaultSources() {
        return defaultSources;
    }

    public void setDefaultSources(List<HashMap> defaultSources) {
        this.defaultSources = defaultSources;
    }

    public int getItemsCat() {
        return itemsCat;
    }

    public void setItemsCat(int itemsCat) {
        this.itemsCat = itemsCat;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }


}
