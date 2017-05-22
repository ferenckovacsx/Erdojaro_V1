package com.example.ferenckovacsx.erdojaro_v1.JavaBeans;

import android.widget.ImageView;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class POI {

    public String name;
    public int imageID;
    //public ImageView image;
    public String gpsCoord;
    public boolean favorited;
    public String description;

    public POI(String name, int imageID,  String gpsCoord, boolean favorited, String description) {
        this.name = name;
        this.imageID = imageID;
        //this.image = image;
        this.gpsCoord = gpsCoord;
        this.favorited = favorited;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getGpsCoord() {
        return gpsCoord;
    }

    public void setGpsCoord(String gpsCoord) {
        this.gpsCoord = gpsCoord;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "POI{" +
                "name='" + name + '\'' +
                ", imageID=" + imageID +
                ", gpsCoord='" + gpsCoord + '\'' +
                ", favorited=" + favorited +
                ", description='" + description + '\'' +
                '}';
    }
}
