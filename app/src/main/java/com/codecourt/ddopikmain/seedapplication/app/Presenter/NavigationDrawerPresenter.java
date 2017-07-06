package com.codecourt.ddopikmain.seedapplication.app.Presenter;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.View.MainActivity;
import com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu.AboutUsActivity;
import com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu.CircleTransform;
import com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu.PrivacyPolicyActivity;
import com.codecourt.ddopikmain.seedapplication.R;
import com.codecourt.ddopikmain.seedapplication.app.View.SettingMenu.SittingActivity;
import com.codecourt.ddopikmain.seedapplication.app.View.SourceList;

import static com.codecourt.ddopikmain.seedapplication.app.View.MainActivity.CURRENT_TAG;

/**
 * Created by ddopikMain on 3/29/2017.
 */

public class NavigationDrawerPresenter  {

    private MainActivity mainActivity;

    public NavigationDrawerPresenter(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
        initializeDrawer();
    }

    private View mainView;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;

    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;


    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "http://www.code-court.com/design/front/img/logo.png";

    public static int navItemIndex = 0;
    private static final String fragment_one = MainApp.app.getResources().getString(R.string.fragment_one);
    private static final String fragment_two = MainApp.app.getResources().getString(R.string.fragment_two);
    private static final String fragment_three = MainApp.app.getResources().getString(R.string.fragment_three);
    private static final String fragment_four = MainApp.app.getResources().getString(R.string.fragment_four);
    private static final String sourceListActivity =MainApp.app.getResources().getString(R.string.sources);
    private static final String settingActivity =MainApp.app.getResources().getString(R.string.setting_menu);



    public void initializeDrawer() {
        // Navigation view header
        navigationView = (NavigationView) this.mainActivity.findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
        drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);  ///Drawer For side Menu
        //TextView toolBarTitle=(TextView) toolbar.findViewById(R.id.toolbar_title) ;
        loadNavHeader();
        setUpNavigationEvents();
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        ///setting values For Header Side Menu
        // name, website
        txtName.setText("Code Court");
        txtWebsite.setText("www.Code-Court.com");

        // loading header background image
        Glide.with(mainActivity).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(mainActivity).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(mainActivity))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);


    }
    private void setUpNavigationEvents() {

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                Log.e("NavigationDrawer","------->OptionNoticed");
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_last_news:
                        navItemIndex = 0;
                        CURRENT_TAG = fragment_one;
                        (mainActivity).viewPager.setCurrentItem(0);
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_mix_news:
                        navItemIndex = 1;
                        (mainActivity).viewPager.setCurrentItem(1);
                        CURRENT_TAG = fragment_two;
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_sport_news:
                        navItemIndex = 2;
                        (mainActivity).viewPager.setCurrentItem(2);
                        CURRENT_TAG = fragment_three;
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_fav_news:
                        navItemIndex = 3;
                        (mainActivity).viewPager.setCurrentItem(3);
                        CURRENT_TAG = fragment_four;
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_news_source:
                        // launch new intent instead of loading fragment
                        mainActivity.startActivity(new Intent(mainActivity, SourceList.class));
                        CURRENT_TAG = sourceListActivity;
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_news_setting:
                        // launch new intent instead of loading fragment
                        mainActivity.news_container.setVisibility(View.INVISIBLE);
                        mainActivity. singleNewsFragment.setVisibility(View.VISIBLE);
                        SittingActivity sittingActivity = new SittingActivity();
                        mainActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.single_view_news_fragment, sittingActivity, null)
                                .addToBackStack("MainActivity")
                                .commit();
                        CURRENT_TAG = settingActivity;
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true); //// ??? Seems to be Duplicate
                loadHomeFragment(); //close drawer and load what user chossed and mark his item as choosen.
                return true;
            }
        });

        Toolbar toolbar =(Toolbar) mainActivity.findViewById(R.id.main_toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(mainActivity,drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // Drawer Toggle Object Made
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                } else {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

    }
    private void loadHomeFragment() {
        // selecting appropriate nav menu item ///layout Checked
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (mainActivity.getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            // show or hide the fab button
//            toggleFab();
            return;
        }
    }



}
