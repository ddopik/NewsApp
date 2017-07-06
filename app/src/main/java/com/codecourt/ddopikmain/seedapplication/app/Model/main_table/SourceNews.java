package com.codecourt.ddopikmain.seedapplication.app.Model.main_table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ddopikMain on 3/26/2017.
 */

public class SourceNews extends RealmObject {

    @PrimaryKey
    private int sourceID;
    private String sourceName;
    private String sourceUrl;
    private String lastModified;
    private String origin;
    private boolean defaultSource;

    public boolean isDefaultSource() {
        return defaultSource;
    }

    public void setDefaultSource(boolean defaultSource) {
        this.defaultSource = defaultSource;
    }

    public SourceNews() {


    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


}
