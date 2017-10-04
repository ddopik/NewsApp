package com.codecourt.ddopikmain.seedapplication.app.Presenter;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codecourt.ddopikmain.seedapplication.app.Model.NewsItemModel;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.EventBusMessage.JsonMessageCallBack;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.Util.GsonFeedParser;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Volly.LruBitmapCache;
import com.codecourt.ddopikmain.seedapplication.app.View.ViewIdenity.ViewPager_Fragment_ident;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;


/**
 * Created by ddopikMain on 2/23/2017.
 */

//this is the main Activty  Presenter controller
//this class only exsit for loading Cashe Data as Cashe will be intialized in First time
public class NewsNetWorkController {

    public static final String TAG = NewsNetWorkController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private NewsItemModel newsItemModel = new NewsItemModel();
    LruBitmapCache mLruBitmapCache;
    List<FeedItem> jasonFeedItems = new ArrayList<FeedItem>();
    private MainApp mApplication;
    private JSONObject urlObjResponse;
    private GsonFeedParser parsJason;


    public NewsNetWorkController(MainApp mApplication, GsonFeedParser jsonObj) {
        this.mApplication = mApplication;
        this.parsJason = jsonObj;
    }

    public void StreamJSON(ViewPager_Fragment_ident container, final List<HashMap> defaultSources, final int itemsCat) {

        final ViewPager_Fragment_ident activityContainer = container;
        urlObjResponse = new JSONObject();
        Cache cache = MainApp.mRequestQueue.getCache();

        for (int sourcePosition = 0; sourcePosition < defaultSources.size(); sourcePosition++) {
            Log.e("NewsNetWorkController", "--->Passed Url--->" + defaultSources.get(sourcePosition).get("catUrl").toString());
            Cache.Entry entry = cache.get(defaultSources.get(sourcePosition).get("catUrl").toString());
            if (entry != null) {
                try {
                    String data = new String(entry.data, "UTF-8");
                    try {
                        XmlToJson xmlToJson = new XmlToJson.Builder(data).build();
                        this.urlObjResponse = new JSONObject(xmlToJson.toString());
                        this.saveCallBack(urlObjResponse, activityContainer, defaultSources, itemsCat, sourcePosition);
                    } catch (Exception e) {
                        Log.e("NewsNetWorkController", "No Cashe Found" + e.getMessage());

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else {
                // making fresh volley request and getting json
                try {

                    StartVollyRequest(activityContainer, defaultSources, sourcePosition, itemsCat);

                } catch (Exception e) {
                    Log.e("NewNetWorkController", "----->Volly Added Request error---->" + e.getMessage());
                }
            }


        }



    }


    public List<FeedItem> getJasonFeedItems() {
        return jasonFeedItems;
    }


    public void StartVollyRequest(final ViewPager_Fragment_ident activityContainer, final List<HashMap> defaultSources, final int sourcePosition, final int itemsCat) {
        StringRequest jsonReq = new StringRequest(Request.Method.GET, defaultSources.get(sourcePosition).get("catUrl").toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
                    xmlToJson.toString();
                    urlObjResponse = xmlToJson.toJson();
                    NewsNetWorkController.this.saveCallBack(urlObjResponse, activityContainer, defaultSources, itemsCat, sourcePosition);
                    Log.e("NewsNetWorkController", "Life---Parsed Xml------>" + new JSONObject());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                activityContainer.getFeedListAdapter().notifyDataSetChanged();
                activityContainer.getSwipeRefreshLayout().setRefreshing(false);
            }
        });
        // Adding request to volley request queue
        MainApp.mRequestQueue.add(jsonReq);
    }////


    private void saveCallBack(JSONObject urlObjResponse, ViewPager_Fragment_ident activityContainer, List<HashMap> defaultSources, int itemsCat, int sourcePostions)

    {
        if (urlObjResponse != null) {
            parsJason.setResponse(urlObjResponse, defaultSources.get(sourcePostions).get("catProfileImg").toString());
            parsJason.saveObgToRealm(itemsCat, Integer.parseInt(defaultSources.get(sourcePostions).get("catID").toString()));
            this.updateFragmentAdapter(activityContainer, itemsCat, defaultSources, sourcePostions);
        } else {
            Log.e("NewsNetWorkController", "----> No Object Returned from Url");
        }
    }

    private void updateFragmentAdapter(ViewPager_Fragment_ident activityContainer, int itemsCat, List<HashMap> defaultSources, int sourcePostions) {
        activityContainer.getFeedListItems().addAll(newsItemModel.getFeedItem(itemsCat, Integer.parseInt(defaultSources.get(sourcePostions).get("catID").toString())));
        Log.e("NewsNetWorkController", "---->Adapter NotifyListner Called with " + newsItemModel.getFeedItem(itemsCat, Integer.parseInt(defaultSources.get(sourcePostions).get("catID").toString())).size() + " item");
        activityContainer.getFeedListAdapter().notifyDataSetChanged();
        activityContainer.getSwipeRefreshLayout().setRefreshing(false);
    }
}

