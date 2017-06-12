package com.example.ferenckovacsx.erdojaro_v1.JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */


public class Trip implements Serializable{

    private static final long serialVersionUID = 2L;

    public int Id;
    public String Name;
    public int ImageId;
    public String ImageUrl;
    public double Distance;
    public int favoriteCount;
    public boolean IsItHard;
    public boolean favorited;
    public String Description;
    double[] Latitudes;
    double[] Longitudes;

    public Trip(int id, String name, int imageId, double distance, int favoriteCount, boolean isItHard, boolean favorited, String description, double[] latitudes, double[] longitudes) {
        Id = id;
        Name = name;
        ImageId = imageId;
        Distance = distance;
        this.favoriteCount = favoriteCount;
        IsItHard = isItHard;
        this.favorited = favorited;
        Description = description;
        Latitudes = latitudes;
        Longitudes = longitudes;
    }

    public Trip(int id, String name, String imageUrl, double distance, int favoriteCount, boolean isItHard, boolean favorited, String description, double[] latitudes, double[] longitudes) {
        Id = id;
        Name = name;
        ImageUrl = imageUrl;
        Distance = distance;
        this.favoriteCount = favoriteCount;
        IsItHard = isItHard;
        this.favorited = favorited;
        Description = description;
        Latitudes = latitudes;
        Longitudes = longitudes;
    }

    public int getId() {
        return Id;
    }

    public void setName(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isItHard() {
        return IsItHard;
    }

    public void setItHard(boolean itHard) {
        IsItHard = itHard;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double[] getLatitudes() {
        return Latitudes;
    }

    public void setLatitudes(double[] latitudes) {
        Latitudes = latitudes;
    }

    public double[] getLongitudes() {
        return Longitudes;
    }

    public void setLongitudes(double[] longitudes) {
        Longitudes = longitudes;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "Name='" + Name + '\'' +
                ", ImageId=" + ImageId +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", Distance=" + Distance +
                ", favoriteCount=" + favoriteCount +
                ", IsItHard=" + IsItHard +
                ", favorited=" + favorited +
                ", Description='" + Description + '\'' +
                ", Latitudes=" + Latitudes +
                ", Longitudes=" + Longitudes +
                '}';
    }
}
