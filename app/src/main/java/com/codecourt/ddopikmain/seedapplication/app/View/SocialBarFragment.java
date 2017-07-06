package com.codecourt.ddopikmain.seedapplication.app.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.codecourt.ddopikmain.seedapplication.app.Model.NewsItemModel;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes.EventBusMessage;
import com.codecourt.ddopikmain.seedapplication.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.like.LikeButton;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by ddopikMain on 3/23/2017.
 */

public class SocialBarFragment extends android.support.v4.app.Fragment {


    @BindView(R.id.face_book_btn)
    public ImageButton face_book_btn;
    @BindView(R.id.star_fav_button)
    public LikeButton star_fav_button;
    private View mainView;
    private Unbinder unbinder;
    private NewsItemModel newsItemModel= new NewsItemModel();
    private String newsHeadLine;
    private String newsUrl;
    private int newsID;
    boolean newsFavState=false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("SocialBarFragment", "SocialBarFragment onAttach");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("SocialBarFragment", "SocialBarFragment onStart");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mainView == null) {
            this.mainView = inflater.inflate(R.layout.social_bar_fragment, container, false);
        }
//        mainView = inflater.inflate(R.layout.social_bar_fragment, container, false);
        unbinder = ButterKnife.bind(this, this.mainView);




        return mainView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusMessage eventBusMessage) {
        newsHeadLine = eventBusMessage.getNewsHeadLine();
        newsUrl = eventBusMessage.getShareUrl();
        newsFavState=eventBusMessage.eventBusMessage();
        newsID=eventBusMessage.getItemID();
        if (eventBusMessage.eventBusMessage()) {
            star_fav_button.setLiked(true);
        } else {
            star_fav_button.setLiked(false);
        }

        Log.e("SocialBarFragment", "getShareUrl triggered with " + newsHeadLine + "-----" + newsUrl);
    }

    @OnClick(R.id.face_book_btn)
    public void faceBook_shareNews(ImageView imageView) {
        Log.e("SocialBarFragment", "getShareUrl --->" + newsUrl);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(newsUrl))
                .build();
        ShareDialog.show(SocialBarFragment.this, content);
    }
    @OnClick(R.id.whatsapp_btn2)
    public void whatsApp_shareNews(ImageView imageView) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, newsUrl);
        try {
         startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(),"Whatsapp have not been installed.",Toast.LENGTH_SHORT);
        }
    }

    @OnClick(R.id.share_btn)
    public void PublicShare(ImageView imageView) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, newsUrl);
        startActivity(Intent.createChooser(share, newsHeadLine));
    }
    @OnClick(R.id.star_fav_button)
    public void setFavNewsItem(LikeButton likeButton) {
        if (!newsFavState) {
            Log.e("Value Fav Button ", "-----(If)-----> " + newsID + "----" + newsFavState);
            newsItemModel.setFavFeedItem(newsHeadLine.length(), true);
            newsFavState=true;
            likeButton.setLiked(true);


        } else {
            Log.e("Value Fav Button ", "----(False)------> " + newsID + "----" +newsFavState);
            newsItemModel.setFavFeedItem(newsHeadLine.length(), false);
            newsFavState=false;
            likeButton.setLiked(false);
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        Log.e("SocialBarFragment", "SocialBarFragment------->onPause()");

    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.e("SocialBarFragment", "SocialBarFragment------->onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SocialBarFragment", "SocialBarFragment------->onDestroy()");


    }
}