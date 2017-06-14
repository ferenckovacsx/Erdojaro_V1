package com.example.ferenckovacsx.erdojaro_v1;

import android.util.Log;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Trip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ferenckovacsx on 2017-06-08.
 */

class JSONParser {

    static List parsePoiJson(String responseJSON) {

        List<POI> poiList;

        Gson gson = new Gson();
        Type listType = new TypeToken<List<POI>>() {}.getType();
        poiList = gson.fromJson(responseJSON, listType);
        Log.d("JSONParser", "POI: " + poiList);
        Log.d("JSONParser", "POI list length: " + poiList.size());
        return poiList;
    }

    static List parseTripJson(String responseJSON) {

        List<Trip> tripList;

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Trip>>() {}.getType();
        tripList = gson.fromJson(responseJSON, listType);
        Log.d("JSONParser", "Trip: " + tripList);
        Log.d("JSONParser", "Trip list length: " + tripList.size());
        return tripList;
    }
}
