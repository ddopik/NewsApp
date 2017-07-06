package com.codecourt.ddopikmain.seedapplication.app.Model.main_table;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ddopikMain on 3/29/2017.
 */

public class SourceCategores extends RealmObject {
@PrimaryKey
    private int catID;
    private int source_fk_id;
    private String catUrl;
    private int catType;
    private Date lastModified;
    private String sourceImg;

    public String getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public int getCatType() {
        return catType;
    }

    public void setCatType(int catType) {
        this.catType = catType;
    }


    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatUrl() {
        return catUrl;
    }

    public void setCatUrl(String catUrl) {
        this.catUrl = catUrl;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getSource_fk_id() {
        return source_fk_id;
    }

    public void setSource_fk_id(int source_fk_id) {
        this.source_fk_id = source_fk_id;
    }
}
