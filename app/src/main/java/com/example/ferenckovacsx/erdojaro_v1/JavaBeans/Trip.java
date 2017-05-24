package com.example.ferenckovacsx.erdojaro_v1.JavaBeans;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class Trip {

    public String name;
    public int imageID;
    public String gpsCoord;
    public int distance;
    public boolean isItHard;
    public boolean favorited;
    public String description;

    public Trip(String name, int imageID, String gpsCoord, int distance, boolean isItHard, boolean favorited, String description) {
        this.name = name;
        this.imageID = imageID;
        this.gpsCoord = gpsCoord;
        this.distance = distance;
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
                ", isItHard=" + isItHard +
                ", favorited=" + favorited +
                ", description='" + description + '\'' +
                '}';
    }
}
