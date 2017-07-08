package com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans;

import java.util.List;

/**
 * Created by ferenckovacsx on 2017-06-09.
 */

public class AsyncTaskResponseContent {

    public List<POI> poiList;
    public List<Trip> tripList;
    public List<Program> programList;
    public List<Flora> floraList;
    public List<Fauna> faunaList;
    public List<Funghi> funghiList;

    public AsyncTaskResponseContent(List<POI> poiList, List<Trip> tripList, List<Program> programList, List<Flora> floraList, List<Fauna> faunaList, List<Funghi> funghiList) {
        this.poiList = poiList;
        this.tripList = tripList;
        this.programList = programList;
        this.floraList = floraList;
        this.faunaList = faunaList;
        this.funghiList = funghiList;
    }

    public List<POI> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<POI> poiList) {
        this.poiList = poiList;
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public List<Flora> getFloraList() {
        return floraList;
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
    }

    public List<Fauna> getFaunaList() {
        return faunaList;
    }

    public void setFaunaList(List<Fauna> faunaList) {
        this.faunaList = faunaList;
    }

    public List<Funghi> getFunghiList() {
        return funghiList;
    }

    public void setFunghiList(List<Funghi> funghiList) {
        this.funghiList = funghiList;
    }

    @Override
    public String toString() {
        return "AsyncTaskResponseContent{" +
                "poiList=" + poiList +
                ", tripList=" + tripList +
                ", programList=" + programList +
                ", floraList=" + floraList +
                ", faunaList=" + faunaList +
                ", funghiList=" + funghiList +
                '}';
    }
}
