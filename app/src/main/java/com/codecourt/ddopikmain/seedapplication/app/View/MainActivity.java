package com.codecourt.ddopikmain.seedapplication.app.View;


import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.AppComponent;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.DaggerNewsControllerComponent;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.NewsControllerComponent;
import com.codecourt.ddopikmain.seedapplication.app.Model.SourceListModel;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.FeedItem;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.ViewPagerAdapter;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.NotificationConfig;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Handler.Util.NotificationUtils;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.NavMenuPresenter;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codecourt.ddopikmain.seedapplication.app.Presenter.NavigationDrawerPresenter;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.EventBusMessage.ProgressState;
import com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu.SittingActivity;
import com.codecourt.ddopikmain.seedapplication.R;
import com.codecourt.ddopikmain.seedapplication.app.View.ViewIdenity.ViewPager_Fragment_ident;
import com.google.firebase.messaging.FirebaseMessaging;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private SourceListModel sourceListModel ;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinator_layout_container;
    @BindView(R.id.news_container)
    public LinearLayout news_container;
    @BindView(R.id.single_view_news_fragment)
    public FrameLayout singleNewsFragment;



    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    public static String CURRENT_TAG = TAG_HOME;

    public static NewsControllerComponent newsControllerComponent;

    @Inject
    NavMenuPresenter slidePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setupInjection(); ///intiazlizing Dagger

        Toast.makeText(this,"MainActivity---->onCreate",Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        pagerAdapter = slidePresenter.getNavigationFragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager); //applying scroll bar with adapter

        new NavigationDrawerPresenter(this);    //Side Menu Activity
        setNotificationBroducastReciver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(NotificationConfig.REGISTRATION_COMPLETE));
        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(NotificationConfig.PUSH_NOTIFICATION));
        // clear the notification area when the app is opened

        NotificationUtils.clearNotifications(getApplicationContext());
        Log.e("MainActivity", "---->On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActitivy ", "---->OnPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);

    }

    @OnClick(R.id.source_btn)
    public void viewsSources(ImageButton imageButton) {

        Intent intent = new Intent(this, SourceList.class);
        startActivity(intent);


    }

    @OnClick(R.id.notification_btn)
    public void setNotification(ImageButton imageButton) {

        news_container.setVisibility(View.INVISIBLE);
        singleNewsFragment.setVisibility(View.VISIBLE);
        SittingActivity sittingActivity = new SittingActivity();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.single_view_news_fragment, sittingActivity, null)
                .addToBackStack("MainActivity")
                .commit();

    }


    public void setAdapter(int position) {
        Log.i("MainActivity","--->MainActivity setAdapter Called");
        viewPager.setCurrentItem(position);
        pagerAdapter.notifyDataSetChanged();
    }


    public void launchSingleNewsFragment()
    {
        news_container.setVisibility(View.GONE);
        singleNewsFragment.setVisibility(View.VISIBLE);
        SingleNewsActivity singleNewsActivity = new SingleNewsActivity();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.single_view_news_fragment, singleNewsActivity)
                .addToBackStack("MainActivity")
                .commit();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(singleNewsFragment.getVisibility()==View.VISIBLE)
            {
                news_container.setVisibility(View.VISIBLE);
                singleNewsFragment.setVisibility(View.INVISIBLE);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit" +getResources().getString(R.string.app_name))
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                        moveTaskToBack(true);
                    }
                }).create().show();
    }


    public void setNotificationBroducastReciver()
    {
        ///Creation of BroducastReciver.
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("MainActivity ", "---->BroadcastReceiver onReceive Called"+"------>"+intent.getAction().toString());
                SharedPreferences pref = getApplicationContext().getSharedPreferences(NotificationConfig.SHARED_PREF, 0);
                Log.e("MainActivity", "token---->"+ pref.getString("regId","null"));

                // checking for type intent filter
                if (intent.getAction().equals(NotificationConfig.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(NotificationConfig.TOPIC_GLOBAL);
                    Log.e("SourceListActivity ", "---->BroadcastReceiver subscribe to `global` topic ");
                } else if (intent.getAction().equals(NotificationConfig.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                }
            }
        };


    }
    public  void connectionNotify()
    {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if(!MainApp.isInternetAvailable())
        {
            Snackbar snackbar = Snackbar
                    .make(coordinator_layout_container, "No internet Connection",40000)
                    .setAction("Refresh", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(coordinator_layout_container, "Refreshing", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                            ProgressState progressState=new ProgressState();
                            progressState.setSetRefreash(true);
                            EventBus.getDefault().post(progressState);
                        }
                    });
            snackbar.show();
        }
    }




    protected void setupInjection() {

        AppComponent appComponent = ((MainApp) getApplication()).getAppComponent();
        newsControllerComponent = DaggerNewsControllerComponent.builder()    ///intializing our componanet
                .appComponent(appComponent)   ///adiding ApplicationComponent instance to NewsControllerComponent Grapgh
                /// we Have to istantiate this instance befor adding it to .appComponent()
                /// cause if we addaed with 'new' how we will get the Dagger components objects grapgh
                .build();
        newsControllerComponent.inject(this);
        ButterKnife.bind(this);


    }


}

