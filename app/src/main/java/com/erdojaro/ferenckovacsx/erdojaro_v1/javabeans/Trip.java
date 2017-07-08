package com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */


public class Trip implements Serializable {

    private static final long serialVersionUID = 2L;

    public int Id;
    public String Name;
    public int ImageId;
    public String ImageUrl;
    public double Distance;
    public int FavoriteCount;
    public boolean IsItHard;
    public boolean Favorited;
    public boolean Hiking;
    public boolean Cycling;
    public boolean Tanosveny;
    public String Description;
    double[] Latitudes;
    double[] Longitudes;

    public Trip(){

    }


    public Trip(int id, String name, int imageId, double distance, int favoriteCount, boolean isItHard, boolean favorited, boolean hiking, boolean cycling, boolean tanosveny, String description, double[] latitudes, double[] longitudes) {
        Id = id;
        Name = name;
        ImageId = imageId;
        Distance = distance;
        FavoriteCount = favoriteCount;
        IsItHard = isItHard;
        Favorited = favorited;
        Hiking = hiking;
        Cycling = cycling;
        Tanosveny = tanosveny;
        Description = description;
        Latitudes = latitudes;
        Longitudes = longitudes;
    }

    public Trip(int id, String name, String imageUrl, double distance, int favoriteCount, boolean isItHard, boolean favorited, boolean hiking, boolean cycling, boolean tanosveny, String description, double[] latitudes, double[] longitudes) {
        Id = id;
        Name = name;
        ImageUrl = imageUrl;
        Distance = distance;
        FavoriteCount = favoriteCount;
        IsItHard = isItHard;
        Favorited = favorited;
        Hiking = hiking;
        Cycling = cycling;
        Tanosveny = tanosveny;
        Description = description;
        Latitudes = latitudes;
        Longitudes = longitudes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
        return FavoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.FavoriteCount = favoriteCount;
    }

    public boolean isItHard() {
        return IsItHard;
    }

    public void setItHard(boolean itHard) {
        IsItHard = itHard;
    }

    public boolean isFavorited() {
        return Favorited;
    }

    public void setFavorited(boolean favorited) {
        this.Favorited = favorited;
    }

    public boolean isHiking() {
        return Hiking;
    }

    public void setHiking(boolean hiking) {
        this.Hiking = hiking;
    }

    public boolean isCycling() {
        return Cycling;
    }

    public void setCycling(boolean cycling) {
        this.Cycling = cycling;
    }

    public boolean isTanosveny() {
        return Tanosveny;
    }

    public void setTanosveny(boolean tanosveny) {
        this.Tanosveny = tanosveny;
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
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", ImageId=" + ImageId +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", Distance=" + Distance +
                ", FavoriteCount=" + FavoriteCount +
                ", IsItHard=" + IsItHard +
                ", Favorited=" + Favorited +
                ", Hiking=" + Hiking +
                ", Cycling=" + Cycling +
                ", Tanosveny=" + Tanosveny +
                ", Description='" + Description + '\'' +
                ", Latitudes=" + Arrays.toString(Latitudes) +
                ", Longitudes=" + Arrays.toString(Longitudes) +
                '}';
    }
}
