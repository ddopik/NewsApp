package com.codecourt.ddopikmain.seedapplication.app.Model.main_table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ddopikMain on 3/26/2017.
 */


public class NewsSections extends RealmObject {

    @PrimaryKey
    private int sectionsID;
    private String sectiosnName;
    private String sectionsLastModified;
//    private RealmList<FeedItem> sectionItems;



    public int getSectionsID() {
        return sectionsID;
    }

    public void setSectionsID(int sectionsID) {
        this.sectionsID = sectionsID;
    }


    public String getSectiosnName() {
        return sectiosnName;
    }

    public void setSectiosnName(String sectiosnName) {
        this.sectiosnName = sectiosnName;
    }

    public String getSectionsLastModified() {
        return sectionsLastModified;
    }

    public void setSectionsLastModified(String sectionsLastModified) {
        this.sectionsLastModified = sectionsLastModified;
    }
//
//    public List<FeedItem> getSectionItems() {
//        return sectionItems;
//    }
//
//    public void setSectionItems(RealmList<FeedItem> sectionItems) {
//        this.sectionItems = sectionItems;
//    }


}
