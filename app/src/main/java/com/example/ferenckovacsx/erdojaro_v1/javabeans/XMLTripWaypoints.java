package com.example.ferenckovacsx.erdojaro_v1.javabeans;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-06-27.
 */

public class XMLTripWaypoints {
    private ArrayList<Double> latitudesList;
    private ArrayList<Double> longitudesList;

    public XMLTripWaypoints(ArrayList<Double> latitudesList, ArrayList<Double> longitudesList) {
        this.latitudesList = latitudesList;
        this.longitudesList = longitudesList;
    }

    public ArrayList<Double> getLatitudesList() {
        return latitudesList;
    }

    public void setLatitudesList(ArrayList<Double> latitudesList) {
        this.latitudesList = latitudesList;
    }

    public ArrayList<Double> getLongitudesList() {
        return longitudesList;
    }

    public void setLongitudesList(ArrayList<Double> longitudesList) {
        this.longitudesList = longitudesList;
    }

    @Override
    public String toString() {
        return "XMLWayPointsPOJO{" +
                "latitudesList=" + latitudesList +
                ", longitudesList=" + longitudesList +
                '}';
    }
}
