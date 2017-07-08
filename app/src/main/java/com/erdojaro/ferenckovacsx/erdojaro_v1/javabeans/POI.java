package com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class POI implements Serializable {

    private static final long serialVersionUID = 1L;

    public int Id;
    public String Name;
    public int ImageInt;
    public String ImageUrl;
    public Bitmap ImageBitmap;
    public double Latitude;
    public double Longitude;
    public String Description;

    public POI(){

    }

    public POI(int id, String name, int ImageInt, double Latitude, double Longitude, String description) {
        this.Id = id;
        this.Name = name;
        this.ImageInt = ImageInt;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Description = description;
    }

    public POI(int id, String name, String ImageUrl, double Latitude, double Longitude,  String description) {
        this.Id = id;
        this.Name = name;
        this.ImageUrl = ImageUrl;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Description = description;
    }


    public POI(int id, String name, Bitmap ImageBitmap, double Latitude, double Longitude,  String description) {
        this.Id = id;
        this.Name = name;
        this.ImageBitmap = ImageBitmap;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Description = description;
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
        this.Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    public int getImageInt() {
        return ImageInt;
    }

    public void setImageInt(int imageInt) {
        this.ImageInt = imageInt;
    }

    public Bitmap getImageBitmap() {
        return ImageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.ImageBitmap = ImageBitmap;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    @Override
    public String toString() {
        return "POI{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", ImageInt=" + ImageInt +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", ImageBitmap=" + ImageBitmap +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Description='" + Description + '\'' +
                '}';
    }
}
