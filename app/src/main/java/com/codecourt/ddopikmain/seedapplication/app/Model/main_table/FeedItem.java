package com.codecourt.ddopikmain.seedapplication.app.Model.main_table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FeedItem  extends RealmObject {
    @PrimaryKey
    private int id;
    private int fK_sectionsID;
    private int fK_SourceCatID;
    private String name, status, image, profilePic, timeStamp, url;
    private Boolean favourateItem=false;

    public FeedItem() {
    }

    public FeedItem(int id, String name, String image, String status,
                    String profilePic, String timeStamp, String url) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.profilePic = profilePic;
        this.timeStamp = timeStamp;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImge() {
        return image;
    }

    public void setImge(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public int getfK_sectionsID() {
        return fK_sectionsID;
    }

    public void setfK_sectionsID(int fK_sectionsID) {
        this.fK_sectionsID = fK_sectionsID;
    }

    public void setAsFav(boolean state)
    {
        this.favourateItem=state;
    }
    public Boolean isFav()
    {
        return this.favourateItem;
    }

    public int getfK_SourceCatID() {
        return fK_SourceCatID;
    }

    public void setfK_SourceCatID(int fK_SourceCatID) {
        this.fK_SourceCatID = fK_SourceCatID;
    }
}