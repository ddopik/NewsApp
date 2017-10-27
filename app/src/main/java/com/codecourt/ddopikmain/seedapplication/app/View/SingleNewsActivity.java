package com.codecourt.ddopikmain.seedapplication.app.View;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codecourt.ddopikmain.seedapplication.app.Model.NewsItemModel;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes.EventBusMessage;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.POJO_Classes.MessageView;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NewsNetWorkController;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Volly.View.SingleNewsImageView;
import com.codecourt.ddopikmain.seedapplication.R;
import com.like.LikeButton;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by ddopikMain on 3/6/2017.
 */

//public class SingleNewsActivity extends AppCompatActivity {
public class SingleNewsActivity extends android.support.v4.app.Fragment {


    private View mainView;
    private NewsItemModel newsItemModel;
    private FeedItem item;

    @BindView(R.id.singleNewsHeading)
    TextView singleNewsHeading;
    @BindView(R.id.feedImage2)
    SingleNewsImageView feedImageView;

    @BindView(R.id.textView2)
    public TextView description;
    @BindView(R.id.btn_share)
    public ImageButton btn_share;
    @BindView(R.id.btn_zoom_in)
    public ImageButton zoomIn;
    @BindView(R.id.btn_zoom_out)
    public ImageButton zoomOut;





    @Inject
    public MessageView messageView;
    @Inject
    NewsNetWorkController newsNetWorkController;
    private EventBusMessage eventBusMessage;
    private Unbinder unbinder;
    private String newsHeadLine;
    private String newsUrl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("SingleNewsActivity", "SingleNewsActivity onAttach");
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mainView == null) {
            this.mainView = inflater.inflate(R.layout.single_news_activity, container, false);
        }
        setHasOptionsMenu(true);
//        this.mainView = inflater.inflate(R.layout.single_news_activity, container, false);
        unbinder = ButterKnife.bind(this, this.mainView);
        setUpInjecuios();

        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbar_1);
//        setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set whether to include the application home affordance in the action bar.
        // (and put a back mark at icon in ActionBar for "up" navigation)
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Enable or disable the "home" button in the corner of the action bar.
        // (clickable or not)
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);



        // Feed image
        try {
            item = messageView.getFeedItem().get(0);
            if (item.getImge() != null) {
                feedImageView.setImageUrl(item.getImge(), MainApp.mImageLoader);
                feedImageView.setVisibility(View.VISIBLE);
                singleNewsHeading.setText(item.getName());
                description.setText(item.getStatus());
            } else {
                feedImageView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Log.e("SingleNewsActivity","Error gitting Single News View---->"+e.getMessage());

        }





        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setTextSize(TypedValue.COMPLEX_UNIT_PX, (description.getTextSize() + 1f));
            }
        });
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setTextSize(TypedValue.COMPLEX_UNIT_PX, (description.getTextSize() - 1f));
            }
        });


        //////////////////////////////////////////
        FrameLayout socialBarFragmentView = (FrameLayout) mainView.findViewById(R.id.swap_fragment_2);
        socialBarFragmentView.setVisibility(View.VISIBLE);

        newsItemModel = new NewsItemModel();

        eventBusMessage = new EventBusMessage();
        eventBusMessage.setItemID(item.getId());
        eventBusMessage.setShareUrl(item.getUrl());
        eventBusMessage.setNewsHeadLine(item.getName());
        eventBusMessage.setFavourateItem(item.isFav());

        EventBus.getDefault().postSticky(eventBusMessage);

        return mainView;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)  ///single new is subscibed when it's clicked in fragment adapter
    public void onEvent(EventBusMessage eventBusMessage) {
        newsHeadLine = eventBusMessage.getNewsHeadLine();
        newsUrl = eventBusMessage.getShareUrl();
        Log.e("SocialBarFragment", "getShareUrl triggered with " + newsHeadLine + "-----" + newsUrl);
    }
    @OnClick(R.id.btn_share)
    public void shareButtonAction(ImageButton imageButton)
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, newsUrl);
        startActivity(Intent.createChooser(share, newsHeadLine));
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                LinearLayout fragmentOne = (LinearLayout) ((MainActivity) getActivity()).findViewById(R.id.news_container);
                fragmentOne.setVisibility(View.VISIBLE);

                FrameLayout fragmentTwo = (FrameLayout) ((MainActivity) getActivity()).findViewById(R.id.single_view_news_fragment);
                fragmentTwo.setVisibility(View.INVISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setUpInjecuios() {
        MainActivity.newsControllerComponent.inject(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.e("SingleNewsActivity", "SingleNewsActivity------->onStop()");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        Log.e("SingleNewsActivity","SingleNewsActivity------->onDestroy");
    }
}
