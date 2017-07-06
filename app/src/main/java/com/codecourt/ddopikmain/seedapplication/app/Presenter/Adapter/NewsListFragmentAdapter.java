package com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter;

/**
 * Created by ddopikLaptop on 2/24/2017.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.codecourt.ddopikmain.seedapplication.app.Model.NewsItemModel;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Volly.View.FeedImageView;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NewsNetWorkController;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.R;
import com.like.LikeButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp.SittingActivity_sharedPreferance;


public class NewsListFragmentAdapter extends BaseAdapter {
    private FragmentActivity mFragment;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    public ImageLoader imageLoader;
    NewsListViewHolder newsListViewHolder;
    NewsNetWorkController newsNetWorkController;


    public NewsListFragmentAdapter(NewsNetWorkController newsNetWorkController) {

        this.newsNetWorkController = newsNetWorkController;
        this.imageLoader = MainApp.mImageLoader;
    }

    public void setHomeActivity(FragmentActivity fragment) {
        this.mFragment = fragment;
    }

    public FragmentActivity getHomeActivity() {
        return this.mFragment;
    }

    public void setFeedItems(List<FeedItem> feedItems)

    {
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) getHomeActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.feed_item, null);
            newsListViewHolder = new NewsListViewHolder(convertView);
            convertView.setTag(newsListViewHolder);
        } else {
            newsListViewHolder = (NewsListViewHolder) convertView.getTag();
        }

        if (imageLoader == null) {
            this.imageLoader = MainApp.mImageLoader;
        }

        FeedItem item = feedItems.get(position);
        newsListViewHolder.name.setText(item.getName());
        newsListViewHolder.timestamp.setText(item.getTimeStamp());


//         Chcek for empty News--->( MainText )
        if (!TextUtils.isEmpty(item.getStatus())) {
            newsListViewHolder.statusMsg.setText(item.getStatus());
//            statusMsg.setVisibility(View.VISIBLE);
        }


        if (item.isFav()) {
            newsListViewHolder.favBtn.setLiked(true);
        } else {
            newsListViewHolder.favBtn.setLiked(false);
        }




        // user profile pic
        //Sets URL of the image that should be loaded into this view.
        // Note that calling this will immediately either set the cached image (if available) or the default
        newsListViewHolder.profilePic.setImageUrl(item.getProfilePic(), imageLoader);
        SharedPreferences prefs = MainApp.app.getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE);
        String restoredText = prefs.getString("source_switch","false");
        // Feed Main image
        if (item.getImge() != null && restoredText.equals("true")) {
            newsListViewHolder.feedImageView.setImageUrl(item.getImge(), imageLoader);
            newsListViewHolder.feedImageView.setVisibility(View.VISIBLE);
            newsListViewHolder.feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                @Override
                public void onError() {
                }

                @Override
                public void onSuccess() {
                }
            });
        } else {
            newsListViewHolder.feedImageView.setVisibility(View.GONE);
        }

        return convertView;
    }


    public class NewsListViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.timestamp)
        TextView timestamp;
        @BindView(R.id.txtStatusMsg)
        TextView statusMsg;
        @BindView(R.id.txtUrl)
        TextView url;
        @BindView(R.id.profilePic)
        NetworkImageView profilePic;
        @BindView(R.id.main_Image)
        FeedImageView feedImageView;
        @BindView(R.id.favItemButton)
        LikeButton favBtn;
        private NewsItemModel newsItemModel;
        private FeedItem feedItem;

        public NewsListViewHolder(View view)

        {
            ButterKnife.bind(this, view);
        }


        @OnClick(R.id.favItemButton)
        public void setFavNewsItem(LikeButton likeButton) {
            newsItemModel = new NewsItemModel();
            feedItem = newsItemModel.getSingleNewsItem(name.length());
            if (!this.feedItem.isFav()) {
                Log.e("Value Fav Button ", "-----(If)-----> " + feedItem.getId() + "----" + feedItem.isFav());
                newsItemModel.setFavFeedItem(name.length(), true);
                likeButton.setLiked(true);


            } else {
                Log.e("Value Fav Button ", "----(False)------> " + feedItem.getId() + "----" + feedItem.isFav());
                newsItemModel.setFavFeedItem(name.length(), false);
                likeButton.setLiked(false);
            }

        }

    }
}