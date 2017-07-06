package com.codecourt.ddopikmain.seedapplication.app.Presenter;

import android.support.v4.app.FragmentManager;

import com.codecourt.ddopikmain.seedapplication.R;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.FourFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.OneFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.ThreeFragment;
import com.codecourt.ddopikmain.seedapplication.app.View.NavSections.TwoFragment;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.Adapter.ViewPagerAdapter;

/**
 * Created by ddopikMain on 2/26/2017.
 */

public class NavMenuPresenter {


    public ViewPagerAdapter getNavigationFragmentAdapter(FragmentManager frgManger) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(frgManger);
        adapter.addFragment(new OneFragment(), MainApp.app.getResources().getString(R.string.fragment_one));
        adapter.addFragment(new TwoFragment(),  MainApp.app.getResources().getString(R.string.fragment_two));
        adapter.addFragment(new ThreeFragment(), MainApp.app.getResources().getString(R.string.fragment_three));
        adapter.addFragment(new FourFragment(), MainApp.app.getResources().getString(R.string.fragment_five));
//        adapter.addFragment(new FiveFragment(), "Five");


//
//        Calendar c = Calendar.getInstance();
//        int seconds = c.get(Calendar.SECOND);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        String currentDateandTime = sdf.format(new Date());
//
//        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
//        String date = df.format(Calendar.getInstance().getTime());
//
//        NewsSections nS = new NewsSections();
//        nS.setSectiosnName("أخر الاخبار");
//        nS.setSectionsID(0);
//        nS.setSectionsLastModified(date);
//
//        NewsSections nS1 = new NewsSections();
//        nS1.setSectiosnName("أخبار عاجله");
//        nS1.setSectionsID(1);
//        nS1.setSectionsLastModified(date);
//
//        NewsSections nS2 = new NewsSections();
//        nS2.setSectiosnName("الطقس");
//        nS2.setSectionsID(2);
//        nS2.setSectionsLastModified(date);
//
//        NewsSections nS3 = new NewsSections();
//        nS3.setSectiosnName("المفضله");
//        nS3.setSectionsID(3);
//        nS3.setSectionsLastModified(date);
//
//        NewsSections nS4 = new NewsSections();
//        nS4.setSectiosnName("فيديو");
//        nS4.setSectionsID(4);
//        nS4.setSectionsLastModified(date);
//
//
//
//        MainApp.realm.beginTransaction();
//        MainApp.realm.copyToRealmOrUpdate(nS);
//        MainApp.realm.copyToRealmOrUpdate(nS1);
//        MainApp.realm.copyToRealmOrUpdate(nS2);
//        MainApp.realm.copyToRealmOrUpdate(nS3);
//        MainApp.realm.copyToRealmOrUpdate(nS4);
//        MainApp.realm.commitTransaction();


        return adapter;
    }

}
