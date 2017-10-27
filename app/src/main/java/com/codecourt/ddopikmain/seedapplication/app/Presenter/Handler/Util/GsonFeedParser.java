package com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.Util;

import android.text.Html;
import android.util.Log;

import com.codecourt.ddopikmain.seedapplication.app.Model.NewsItemModel;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.realm.Realm;

/**
 * Created by ddopikMain on 2/26/2017.
 */

public class GsonFeedParser {


    String sourceImg;
    private JSONObject response;
    private List<FeedItem> feedItems;
    private Realm relmInstance;
    private NewsItemModel newsItemModel;

    // @Inject use Module for grapgh inted of contructor for better illustration and underStand
    public GsonFeedParser() {
    }


    public void saveObgToRealm(int itemsCat, int sourceCatId) {

        newsItemModel = new NewsItemModel();
        try {
            JSONObject mainFeedObj = this.response.getJSONObject("rss").getJSONObject("channel");
            JSONArray itemsArray = mainFeedObj.getJSONArray("item");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemObj = itemsArray.getJSONObject(i);
                FeedItem item = new FeedItem();

//                Log.e("GsonFeedParser","---------->saveObgToRealm Trace"+itemObj.getString("pubDate"));

                item.setId(itemObj.getString("title").length());
                item.setName(itemObj.getString("title"));
                ////// Image might be null sometimes//
                String image = itemObj.isNull("enclosure") ? null : itemObj.getJSONObject("enclosure").getString("url");
                item.setImge(image);
                ///////////////
                if (! itemObj.isNull("pubDate")) {
                    item.setTimeStamp(itemObj.getString("pubDate").trim());
                }
//                item.setTimeStamp(itemObj.isNull("pubDate") ? null : itemObj.getString("pubDate").trim());
                item.setProfilePic(this.sourceImg);///<-------
                String c = Html.fromHtml(itemObj.getString("description")).toString();
                item.setStatus(c);
                item.setUrl(itemObj.getString("link"));
                item.setfK_sectionsID(itemsCat);
                item.setfK_SourceCatID(sourceCatId);
                Log.e("GsonFeedParser", "itemsCat =" + itemsCat + " itemsSource Cat =" + sourceCatId);
                newsItemModel.saveFeedItemToRealm(item);
            }


        } catch (JSONException e) {
            Log.e("JsonFeedPareser", "Cached--->Error Parsing And Saving URL", e.fillInStackTrace());
            e.printStackTrace();
            ///TODO must Display Event message NOtify that there is No data
        }
    }


    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response, String profileImg) {
        this.response = response;
        this.sourceImg = profileImg;
    }


}
