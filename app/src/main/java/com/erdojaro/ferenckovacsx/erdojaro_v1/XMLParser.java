package com.erdojaro.ferenckovacsx.erdojaro_v1;

/**
 * Created by ferenckovacsx on 2017-06-27.
 */

import android.util.Log;
import android.util.Xml;

import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Trip;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.TripWaypoint;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.TripWaypointsArray;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.XMLTripWaypoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;


public class XMLParser {

    private ArrayList<TripWaypoint> tripWaypoints = new ArrayList<>();
    private TripWaypointsArray tripWaypointsArray;
    private double[] tripWaypointLatitudes;
    private double[] tripWaypointLongitudes;
    private XMLTripWaypoints XMLwaypoints;
    private ArrayList<Double> longitudes = new ArrayList<>();
    private ArrayList<Double> latitudes = new ArrayList<>();
    private TripWaypoint tripWaypoint;
    private ArrayList<POI> poiArrayList = new ArrayList<>();
    private ArrayList<Trip> tripArrayList = new ArrayList<>();
    private POI poi;
    private Trip trip;
    private String text;


//    public TripWaypointsArray getTripWaypointsArray() {
//
//        tripWaypointsArray = new TripWaypointsArray();
//
//
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser parser = factory.newPullParser();
//
//            InputStream is = mainContext.getAssets().open("trip_6_waypoints.xml");
//            parser.setInput(is, null);
//
//            int eventType = parser.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                String tagname = parser.getName();
//                switch (eventType) {
//
//                    case XmlPullParser.TEXT:
//                        text = parser.getText();
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if (tagname.equalsIgnoreCase("waypoint")) {
//                            tripWaypoints.add(tripWaypoint);
//                        } else if (tagname.equalsIgnoreCase("latitude")) {
//                            Double.parseDouble(text)
//                        } else if (tagname.equalsIgnoreCase("longitude")) {
//                            tripWaypoint.setLongitude(Double.parseDouble(text));
//                        }
//                        break;
//
//                    default:
//                        break;
//                }
//                eventType = parser.next();
//            }
//
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.i("latitudes", "list" + latitudes);
//        Log.i("longitudes", "list" + longitudes);
//        Log.i("Tripwaypoints", "list" + tripWaypoints);
//
//        return tripWaypointsArray;
//    }
//
//
//
//
//
//

    public ArrayList<TripWaypoint> getTripWaypoints(int tripId) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            String tripWaypointsXmlSource = "trip_" + tripId + "_waypoints.xml";
            InputStream is = mainContext.getAssets().open(tripWaypointsXmlSource);
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("waypoint")) {
                            // create a new instance of poi
                            tripWaypoint = new TripWaypoint();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("waypoint")) {
                            tripWaypoints.add(tripWaypoint);
                        } else if (tagname.equalsIgnoreCase("latitude")) {
                            tripWaypoint.setLatitude(Double.parseDouble(text));
                        } else if (tagname.equalsIgnoreCase("longitude")) {
                            tripWaypoint.setLongitude(Double.parseDouble(text));
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

    public ArrayList<POI> getPoiListFromXml() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            InputStream is = mainContext.getAssets().open("poi.xml");
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("poi")) {
                            // create a new instance of poi
                            poi = new POI();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();

                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("poi")) {
                            // add employee object to list
                            poiArrayList.add(poi);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            poi.setId(Integer.parseInt(text));

                            //generate drawable int and description string res based on id
                            int drawableId = mainContext.getResources().getIdentifier("poi_" + text, "drawable", mainContext.getPackageName());
                            //int descriptionResId = mainContext.getResources().getIdentifier("poi_" + text + "_description", "string", mainContext.getPackageName());
                            Log.i("xmlParser", "drawableId: " + drawableId);
                            poi.setImageInt(drawableId);
                            // poi.setDescription(mainContext.getResources().getString(descriptionResId));

                        } else if (tagname.equalsIgnoreCase("name")) {
                            poi.setName(text);
                        } else if (tagname.equalsIgnoreCase("latitude")) {
                            poi.setLatitude(Double.parseDouble(text));
                        } else if (tagname.equalsIgnoreCase("longitude")) {
                            poi.setLongitude(Double.parseDouble(text));
                        } else if (tagname.equalsIgnoreCase("description")) {
                            Log.d("tagname", "descrtiptiom: " + text);
                            int descriptionResId = mainContext.getResources().getIdentifier(text, "string", mainContext.getPackageName());
                            poi.setDescription(mainContext.getResources().getString(descriptionResId));

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
        return poiArrayList;
    }

    public ArrayList<Trip> getTripListFromXml() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            InputStream is = mainContext.getAssets().open("trip.xml");
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("trip")) {
                            // create a new instance of poi
                            trip = new Trip();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();

                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("trip")) {
                            // add employee object to list
                            tripArrayList.add(trip);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            trip.setId(Integer.parseInt(text));

                            //generate drawable int, name and description string res based on id
                            int drawableId = mainContext.getResources().getIdentifier("trip_" + text, "drawable", mainContext.getPackageName());
                            int descriptionResId = mainContext.getResources().getIdentifier("trip_" + text + "_description", "string", mainContext.getPackageName());
                            int nameResId = mainContext.getResources().getIdentifier("trip_" + text + "_name", "string", mainContext.getPackageName());
                            trip.setImageId(drawableId);
                            trip.setName(mainContext.getResources().getString(nameResId));
                            trip.setDescription(mainContext.getResources().getString(descriptionResId));

                        } else if (tagname.equalsIgnoreCase("distance")) {
                            trip.setDistance(Double.parseDouble(text));
                        } else if (tagname.equalsIgnoreCase("tanosveny")) {
                            trip.setTanosveny(Boolean.parseBoolean(text));
                        } else if (tagname.equalsIgnoreCase("cycling")) {
                            trip.setCycling(Boolean.parseBoolean(text));
                        } else if (tagname.equalsIgnoreCase("hiking")) {
                            trip.setHiking(Boolean.parseBoolean(text));
                        } else if (tagname.equalsIgnoreCase("difficulty")) {
                            if (text.equals("easy")){
                                trip.setItHard(false);
                            } else if (text.equals("hard")) {
                                trip.setItHard(true);
                            }
                        }
//                        else if (tagname.equalsIgnoreCase("description")) {
//                            Log.d("tagname", "descrtiptiom: " + text);
//                            String tripNameEncoded;
//                            tripNameEncoded = URLDecoder.decode(text, "unicode");
//                            trip.setDescription(text);
//
//                        }
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
        return tripArrayList;
    }
}
