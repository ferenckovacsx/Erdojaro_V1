package com.example.ferenckovacsx.erdojaro_v1.javabeans;

/**
 * Created by ferenckovacsx on 2017-06-27.
 */

public class TripWaypoint {
    private double longitude;
    private double latitude;


    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return " longitude= " + longitude + "\n latitude= " + latitude;
    }
}