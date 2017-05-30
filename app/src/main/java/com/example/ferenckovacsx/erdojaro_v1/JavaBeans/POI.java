package com.example.ferenckovacsx.erdojaro_v1.JavaBeans;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class POI {

    public String name;
    public int imageID;
    public double gpsCoordLat;
    public double gpsCoordLong;
    public boolean favorited;
    public String description;

    public POI(String name, int imageID, double gpsCoordLat, double gpsCoordLong, boolean favorited, String description) {
        this.name = name;
        this.imageID = imageID;
        this.gpsCoordLat = gpsCoordLat;
        this.gpsCoordLong = gpsCoordLong;
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

    public double getGpsCoordLong() {
        return gpsCoordLong;
    }

    public void setGpsCoordLong(double gpsCoordLong) {
        this.gpsCoordLong = gpsCoordLong;
    }

    public double getGpsCoordLat() {
        return gpsCoordLat;
    }

    public void setGpsCoordLat(double gpsCoordLat) {
        this.gpsCoordLat = gpsCoordLat;
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
                ", gpsCoordLong=" + gpsCoordLong +
                ", gpsCoordLat=" + gpsCoordLat +
                ", favorited=" + favorited +
                ", description='" + description + '\'' +
                '}';
    }
}
