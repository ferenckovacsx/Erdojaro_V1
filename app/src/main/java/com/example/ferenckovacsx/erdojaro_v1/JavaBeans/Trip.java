package com.example.ferenckovacsx.erdojaro_v1.JavaBeans;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class Trip {

    public String name;
    public int imageID;
    public String gpsCoord;
    public int distance;
    public int favoriteCount;
    public boolean isItHard;
    public boolean favorited;
    public String description;

    public Trip(String name, int imageID, String gpsCoord, int distance, int favoriteCount, boolean isItHard, boolean favorited, String description) {
        this.name = name;
        this.imageID = imageID;
        this.gpsCoord = gpsCoord;
        this.distance = distance;
        this.favoriteCount = favoriteCount;
        this.isItHard = isItHard;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isItHard() {
        return isItHard;
    }

    public void setItHard(boolean itHard) {
        isItHard = itHard;
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
        return "Trip{" +
                "name='" + name + '\'' +
                ", imageID=" + imageID +
                ", gpsCoord='" + gpsCoord + '\'' +
                ", distance=" + distance +
                ", favoriteCount=" + favoriteCount +
                ", isItHard=" + isItHard +
                ", favorited=" + favorited +
                ", description='" + description + '\'' +
                '}';
    }
}
