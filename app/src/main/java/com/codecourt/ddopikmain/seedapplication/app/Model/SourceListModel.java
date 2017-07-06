package com.codecourt.ddopikmain.seedapplication.app.Model;

import android.util.Log;

import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.SourceCategores;
import com.codecourt.ddopikmain.seedapplication.app.Model.main_table.SourceNews;
import com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig.MainApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by ddopikMain on 4/5/2017.
 */

public class SourceListModel {

    private RealmResults<SourceNews> sourcesList;
    private int sourcesLists_ID[];
    private String DefaultSourcesList[];
    private List<HashMap> DefaultCatList;

    public SourceListModel() {

    }

    public RealmResults getSourcesList() { ///Gitting all sources
        try {
            this.sourcesList = MainApp.realm.where(SourceNews.class).findAll();
        } catch (Exception e) {
            Log.e("Error_here", "SourceListClass----->Null Source callBack");
        }
        return this.sourcesList;
    }

    public boolean isDefaultSources() {
        RealmResults realmResults = MainApp.realm.where(SourceNews.class).equalTo("defaultSource", true).findAll();

        if (realmResults.size() > 0)
            return true;

        return false;
    }

    public List<HashMap> getSourceCat(int catID) {
        this.sourcesList = MainApp.realm.where(SourceNews.class).equalTo("defaultSource", true).findAll();
        sourcesLists_ID = new int[sourcesList.size()];
        for (int i = 0; i < this.sourcesList.size(); i++) {
            this.sourcesLists_ID[i] = sourcesList.get(i).getSourceID();

        }
        DefaultCatList = new ArrayList<HashMap>();
        for (int i = 0; i < sourcesLists_ID.length; i++) {

            RealmResults<SourceCategores> sourceCat = MainApp.realm.where(SourceCategores.class).equalTo("source_fk_id", sourcesLists_ID[i]).equalTo("catType", catID).findAll();
            for (int x = 0; x < sourceCat.size(); x++) {
                HashMap sourceCatMap = new HashMap<String, String>();
                sourceCatMap.put("catUrl", sourceCat.get(x).getCatUrl());
                sourceCatMap.put("catID", sourceCat.get(x).getCatID());
                sourceCatMap.put("catProfileImg", sourceCat.get(x).getSourceImg());
                DefaultCatList.add(sourceCatMap);
            }
        }
        return DefaultCatList;
    }


    public void setDefaultSources(boolean selectedSorces[]) {
        for (int i = 0; i < selectedSorces.length; i++) {
            MainApp.realm.beginTransaction();
            SourceNews sourceNews = MainApp.realm.where(SourceNews.class).equalTo("sourceID", i).findFirst();
            sourceNews.setDefaultSource(selectedSorces[i]);
            if (selectedSorces[i])
                sourceNews.setDefaultSource(true); // the field needs to be true
            else
                sourceNews.setDefaultSource(false); // the field needs to be false

            MainApp.realm.copyToRealmOrUpdate(sourceNews);
            MainApp.realm.commitTransaction();
        }
    }


    public void intializeCatList() {
        SourceCategores sourceCategores1 = new SourceCategores();
        sourceCategores1.setCatUrl("http://www.almasryalyoum.com/rss/rssfeeds");  /// Latest News  ///false
        sourceCategores1.setCatID(0);
        sourceCategores1.setSource_fk_id(0);
        sourceCategores1.setCatType(1);
        sourceCategores1.setSourceImg("http://www.sharkia.gov.eg/DocLib5/%D8%A7%D9%84%D9%85%D8%B5%D8%B1%D9%89%20%D8%A7%D9%84%D9%8A%D9%88%D9%85.png");

        SourceCategores sourceCategores2 = new SourceCategores();
        sourceCategores2.setCatUrl("http://www.almasryalyoum.com/rss/rssfeeds?sectionId=3"); ///Egypt News
        sourceCategores2.setCatID(1);
        sourceCategores2.setSource_fk_id(0);
        sourceCategores2.setCatType(2);
        sourceCategores2.setSourceImg("http://www.sharkia.gov.eg/DocLib5/%D8%A7%D9%84%D9%85%D8%B5%D8%B1%D9%89%20%D8%A7%D9%84%D9%8A%D9%88%D9%85.png");

        SourceCategores sourceCategores3 = new SourceCategores();
        sourceCategores3.setCatUrl("http://www.youm7.com/rss/SectionRss?SectionID=203");  ///Latest News
        sourceCategores3.setCatID(2);
        sourceCategores3.setSource_fk_id(1);
        sourceCategores3.setCatType(1);
        sourceCategores3.setSourceImg("http://www.youm7.com/images/footer/Logo-footer-2.png");

        SourceCategores sourceCategores4 = new SourceCategores();
        sourceCategores4.setCatUrl("http://www.youm7.com/rss/SectionRss?SectionID=298"); /// Sport
        sourceCategores4.setCatID(3);
        sourceCategores4.setSource_fk_id(1);
        sourceCategores4.setCatType(3);
        sourceCategores4.setSourceImg("http://www.youm7.com/images/footer/Logo-footer-2.png");

        SourceCategores sourceCategores5 = new SourceCategores();
        sourceCategores5.setCatUrl("http://www.youm7.com/rss/SectionRss?SectionID=332");   ///Global FootBall
        sourceCategores5.setCatID(4);
        sourceCategores5.setSource_fk_id(1);
        sourceCategores5.setCatType(3);
        sourceCategores5.setSourceImg("http://www.youm7.com/images/footer/Logo-footer-2.png");

        SourceCategores sourceCategores6 = new SourceCategores();
        sourceCategores6.setCatUrl("http://www.youm7.com/rss/SectionRss?SectionID=97"); //Egypt Reports
        sourceCategores6.setCatID(5);
        sourceCategores6.setSource_fk_id(1);
        sourceCategores6.setCatType(2);
        sourceCategores6.setSourceImg("http://www.youm7.com/images/footer/Logo-footer-2.png");

        SourceCategores sourceCategores7 = new SourceCategores();   ///Jazera glopal
        sourceCategores7.setCatUrl("http://www.aljazeera.net/aljazeerarss/be46a341-fe26-41f1-acab-b6ed9c198b19/e6aef64d-084c-42f0-8269-abe48e0cd154"); //Egypt Reports
        sourceCategores7.setCatID(6);
        sourceCategores7.setSource_fk_id(3);
        sourceCategores7.setCatType(2);
        sourceCategores7.setSourceImg("http://www.aljazeera.net/Content/images/headerlogo.png");

        SourceCategores sourceCategores8 = new SourceCategores();  ///Jazera Egypt Only
        sourceCategores8.setCatUrl("http://www.aljazeera.net/aljazeerarss/1ad2d9ce-e1d8-4623-85b4-662055191e42/a445e3fb-9f08-4f78-ac53-b22642756ddc"); //Egypt Reports
        sourceCategores8.setCatID(7);
        sourceCategores8.setSource_fk_id(3);
        sourceCategores8.setCatType(1);
        sourceCategores8.setSourceImg("http://www.aljazeera.net/Content/images/headerlogo.png");

        SourceCategores sourceCategores9 = new SourceCategores();  ///skyNews Egypt Only
        sourceCategores9.setCatUrl("http://www.skynewsarabia.com/web/rss.xml"); //skyNews glopal
        sourceCategores9.setCatID(8);
        sourceCategores9.setSource_fk_id(2);
        sourceCategores9.setCatType(2);
        sourceCategores9.setSourceImg("https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-1/553925_421969167853415_181934826_n.jpg?oh=0c8874806dd63d089d27be445abc7922&oe=59511109");


        final RealmList<SourceCategores> sourceCategoresList = new RealmList<SourceCategores>();
        sourceCategoresList.add(sourceCategores1);
        sourceCategoresList.add(sourceCategores2);
        sourceCategoresList.add(sourceCategores3);
        sourceCategoresList.add(sourceCategores4);
        sourceCategoresList.add(sourceCategores5);
        sourceCategoresList.add(sourceCategores6);
        sourceCategoresList.add(sourceCategores7);
        sourceCategoresList.add(sourceCategores8);
        sourceCategoresList.add(sourceCategores9);

        try {
            MainApp.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    MainApp.realm.copyToRealm(sourceCategoresList);
//                    MainApp.realm.copyToRealmOrUpdate(sourceCategoresList);
                }
            });

        } catch (Exception e) {
            Log.e("SourceListModel", "-------------->" + e.getMessage());
        }


    }

    public void intializeList() {
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        SourceNews sourceNews = new SourceNews();
        SourceNews sourceNews1 = new SourceNews();
        SourceNews sourceNews2 = new SourceNews();
        SourceNews sourceNews3 = new SourceNews();

        sourceNews.setSourceID(0);
        sourceNews.setSourceName("المصرى اليوم");
        sourceNews.setSourceUrl("http://www.almasryalyoum.com");
        sourceNews.setLastModified(date);
        sourceNews.setOrigin("Egypt");

        sourceNews1.setSourceID(1);
        sourceNews1.setSourceName("اليوم السابع");
        sourceNews1.setSourceUrl("http://www.youm7.com/");
        sourceNews1.setLastModified(date);
        sourceNews1.setOrigin("Egypt");

        sourceNews2.setSourceID(2);
        sourceNews2.setSourceName("أسكاى نيوز عربيه");
        sourceNews2.setSourceUrl("http://www.skynewsarabia.com/web/home");
        sourceNews2.setLastModified(date);
        sourceNews2.setOrigin("Egypt");

        sourceNews3.setSourceID(3);
        sourceNews3.setSourceName("الجزيره");
        sourceNews3.setSourceUrl("http://www.aljazeera.net/portal");
        sourceNews3.setLastModified(date);
        sourceNews3.setOrigin("Egypt");


        final RealmList<SourceNews> sourceNewsesList = new RealmList<>();
        sourceNewsesList.add(sourceNews);
        sourceNewsesList.add(sourceNews1);
        sourceNewsesList.add(sourceNews2);
        sourceNewsesList.add(sourceNews3);

        try {
            MainApp.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    MainApp.realm.copyToRealm(sourceNewsesList);
                }
            });

        } catch (Exception e) {
            Log.e("SourceListModel", "-------------->" + e.getMessage());
        }


    }
}
