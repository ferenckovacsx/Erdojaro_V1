package com.example.ferenckovacsx.erdojaro_v1;

/**
 * Created by ferenckovacsx on 2017-06-27.
 */

import android.util.Log;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.TripWaypoint;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.XMLTripWaypoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class XMLParser {

    private List<TripWaypoint> tripWaypoints = new ArrayList<TripWaypoint>();
    private XMLTripWaypoints XMLwaypoints;
    private ArrayList<Double> longitudes = new ArrayList<>();
    private ArrayList<Double> latitudes = new ArrayList<>();
    private TripWaypoint tripWaypoint;
    private String text;

    public List<TripWaypoint> getTripWaypoints() {
        return tripWaypoints;
    }

    public XMLTripWaypoints parseXML(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("tripwaypoints")) {
                            tripWaypoint = new TripWaypoint();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if (tagname.equalsIgnoreCase("Longitude")) {
                            tripWaypoint.setLongitude(Double.parseDouble(text));
                            longitudes.add(tripWaypoint.getLongitude());

                        } else if (tagname.equalsIgnoreCase("Latitude")) {
                            tripWaypoint.setLatitude(Double.parseDouble(text));
                            latitudes.add(tripWaypoint.getLatitude());

                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("latitudes", "list" + latitudes);
        Log.i("longitudes", "list" + longitudes);
        Log.i("Tripwaypoints", "list" + tripWaypoints);

        XMLwaypoints = new XMLTripWaypoints(latitudes, longitudes);
        Log.i("TripwaypointsPOJO", "list" + XMLwaypoints);


        return XMLwaypoints;
    }
}
