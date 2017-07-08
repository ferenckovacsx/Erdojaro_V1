package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.XMLParser;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.TripWaypoint;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationSource;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.Source;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngineListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;

import java.util.ArrayList;
import java.util.List;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;


public class MapFragment extends Fragment implements PermissionsListener {

    private MapView mapView = null;
    private MapboxMap map;

    // private FloatingActionButton showMyLocationFAB;
    private com.github.clans.fab.FloatingActionButton showMyLocationFAB;
    private com.github.clans.fab.FloatingActionButton showPoiFAB;
    private com.github.clans.fab.FloatingActionButton showFireplacesFAB;
    private com.github.clans.fab.FloatingActionMenu floatingActionMenu;
    private FrameLayout floatingButtonIntereptor;

    private LocationEngine locationEngine;
    private LocationEngineListener locationEngineListener;
    private PermissionsManager permissionsManager;

    private List<Position> routeCoordinates;
    private String currentPoiName;
    private LatLng currentPoiLatLng;
    private MarkerView currentMarker;
    private MarkerOptions currentMarkerOptions;

    private boolean hasCurrentPoi;
    private boolean arePoisShown;
    private boolean areFireplacesShown;
    private boolean isLocationEnabled;

    SharedPreferences sharedpreferences;
    SharedPreferences mapFilterPreferences;
    boolean firstRun;

    View convertView;

    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //mapbox access token
        //Mapbox.getInstance(getActivity(), getString(R.string.access_token));

        convertView = inflater.inflate(R.layout.fragment_map, container, false);

        // Get the location engine object for later use.
        locationEngine = LocationSource.getLocationEngine(getActivity());
        locationEngine.activate();

        mapView = convertView.findViewById(R.id.mapView);
        floatingActionMenu = convertView.findViewById(R.id.floating_action_menu);
        showPoiFAB = convertView.findViewById(R.id.floating_menu_show_poi);
        showFireplacesFAB = convertView.findViewById(R.id.floating_menu_fireplaces);
        showMyLocationFAB = convertView.findViewById(R.id.location_toggle_fab);
        //floatingButtonIntereptor = convertView.findViewById(R.id.fl_interceptor);

        mapView.onCreate(savedInstanceState);

        floatingActionMenu.setClosedOnTouchOutside(true);

        showPoiFAB.setImageResource(R.drawable.ic_mapfilter_poi_off);
        showFireplacesFAB.setImageResource(R.drawable.ic_mapfilter_fire_off);
        showMyLocationFAB.setImageResource(R.drawable.ic_mapfilter_mylocation_disabled);


        sharedpreferences = mainContext.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("firstRun", true);
        editor.apply();

        mapFilterPreferences = mainContext.getSharedPreferences("mapFilterPreferences", Context.MODE_PRIVATE);
        areFireplacesShown = mapFilterPreferences.getBoolean("areFireplacesShown", true);
        isLocationEnabled = mapFilterPreferences.getBoolean("isLocationEnabled", true);

        //firstRun = true;
        if (sharedpreferences.getBoolean("firstrun", true)) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
            alertDialog.setTitle("Offline térképek");
            alertDialog.setMessage("Böngészd a térképet az erdő közepén, internet kapcsolat nélkül! \nTölts le térképeket a \"Továbbiak/Offline Térképek\" menüből!");
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.setNegativeButton("Rendben", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();
            sharedpreferences.edit().putBoolean("firstrun", false).apply();
        }


        //get arguments from poi or trip fragment
        Bundle mapBundle = getArguments();

        //check if bundle has any elements
        if (mapBundle != null) {

            final String bundleType = mapBundle.getString("bundle_type");

            if (bundleType != null && bundleType.equals("poi")) {

                hasCurrentPoi = true;

                Log.i("bundle type", "poi");
                final String poiName = mapBundle.getString("poi_title");
                final double latitude = mapBundle.getDouble("poi_lat");
                final double longitude = mapBundle.getDouble("poi_long");

                currentPoiName = poiName;
                currentPoiLatLng = new LatLng(latitude, longitude);

                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(MapboxMap mapboxMap) {
                        map = mapboxMap;

                        IconFactory iconFactory = IconFactory.getInstance(mainContext);
                        Icon icon = iconFactory.fromResource(R.drawable.ic_poi_marker_green);

                        mapboxMap.addMarker(new MarkerViewOptions()
                                .position(new LatLng(latitude, longitude))
                                .title(poiName)
                                .icon(icon)
                        );

                        mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(latitude, longitude))  // set the camera's center position
                                        .zoom(15)  // set the camera's zoom level
                                        .tilt(20)  // set the camera's tilt
                                        .build()));
                    }
                });

            } else if (bundleType != null && bundleType.equals("trip")) {

                final int tripId = mapBundle.getInt("trip_id");
                Log.i("mapfragment", "tripID" + tripId);

                XMLParser demoXML = new XMLParser();
                final ArrayList<TripWaypoint> tripWaypoints = demoXML.getTripWaypoints(tripId);
                Log.i("tripwayoints", "debug: " + tripWaypoints);

//                final double[] latitudes = mapBundle.getDoubleArray("trip_latitudes");
//                final double[] longitudes = mapBundle.getDoubleArray("trip_longitudes");

                routeCoordinates = new ArrayList<>();

                for (int i = 0; i < tripWaypoints.size(); i++) {
                    routeCoordinates.add(Position.fromCoordinates(tripWaypoints.get(i).getLongitude(), tripWaypoints.get(i).getLatitude()));
                }

                Log.i("MapFragment", "route coordinates: " + routeCoordinates.toString());


                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(MapboxMap mapboxMap) {

                        map = mapboxMap;

                        // Create the LineString from the list of coordinates and then make a GeoJSON
                        // FeatureCollection so we can add the line to our map as a layer.
                        LineString lineString = LineString.fromCoordinates(routeCoordinates);

                        FeatureCollection featureCollection =
                                FeatureCollection.fromFeatures(new Feature[]{Feature.fromGeometry(lineString)});

                        Source geoJsonSource = new GeoJsonSource("line-source", featureCollection);

                        mapboxMap.addSource(geoJsonSource);

                        LineLayer lineLayer = new LineLayer("linelayer", "line-source");

                        // The layer properties for our line. This is where we make the line dotted, set the
                        // color, etc.
                        lineLayer.setProperties(
                                PropertyFactory.lineDasharray(new Float[]{0.01f, 2f}),
                                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                                PropertyFactory.lineWidth(5f),
                                PropertyFactory.lineColor(Color.parseColor("#e55e5e"))
                        );

                        mapboxMap.addLayer(lineLayer);

                        //move camera to trip start point
                        mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(tripWaypoints.get(1).getLatitude(), tripWaypoints.get(1).getLongitude()))  // set the camera's center position
                                        .zoom(14)  // set the camera's zoom level
                                        .tilt(20)  // set the camera's tilt
                                        .build()));
                    }
                });
            }

        } else {

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(MapboxMap mapboxMap) {
                    map = mapboxMap;
                }
            });
        }


        showMyLocationFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMyLocationFAB.setImageResource(R.drawable.ic_mapfilter_mylocation_disabled);

                if (map != null) {
                    toggleGps(!map.isMyLocationEnabled());
                }
            }
        });


        showFireplacesFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showFireplacesFAB.setSelected(true);

                showFireplacesFAB.setImageResource(R.drawable.ic_mapfilter_fire_off);

                //showPoiFAB.setColorNormal(R.color.colorPrimary);
                Toast.makeText(getContext(), "Show poi", Toast.LENGTH_SHORT).show();


                floatingActionMenu.close(true);

            }
        });


        showPoiFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arePoisShown = mapFilterPreferences.getBoolean("arePoisShown", true);

                if (!arePoisShown) {

                    showPoiFAB.setImageResource(R.drawable.ic_mapfilter_poi_on);

                    SharedPreferences.Editor mapFilterPreferencesEditor = mapFilterPreferences.edit();
                    mapFilterPreferencesEditor.putBoolean("arePoisShown", true);
                    mapFilterPreferencesEditor.apply();

                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(MapboxMap mapboxMap) {
                            map = mapboxMap;

                            //get list of poi from XML file (poi.xml)
                            ArrayList<POI> poiList;
                            XMLParser xmlParser = new XMLParser();
                            poiList = xmlParser.getPoiListFromXml();
                            Log.i("poi from XML", "" + poiList);

                            IconFactory iconFactory = IconFactory.getInstance(mainContext);
                            Icon icon = iconFactory.fromResource(R.drawable.ic_poi_marker_red);

                            for (int i = 0; i < poiList.size(); i++) {

                                if (hasCurrentPoi) {
                                    if (!poiList.get(i).getName().equals(currentPoiName)) {
                                        mapboxMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(poiList.get(i).getLatitude(), poiList.get(i).getLongitude()))
                                                .title(poiList.get(i).getName())
                                                .icon(icon));
                                    }
                                } else {
                                    mapboxMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(poiList.get(i).getLatitude(), poiList.get(i).getLongitude()))
                                            .title(poiList.get(i).getName())
                                            .icon(icon));
                                }
                            }
                        }
                    });
                } else {

                    showPoiFAB.setImageResource(R.drawable.ic_mapfilter_poi_off);

                    SharedPreferences.Editor mapFilterPreferencesEditor = mapFilterPreferences.edit();
                    mapFilterPreferencesEditor.putBoolean("arePoisShown", false);
                    mapFilterPreferencesEditor.apply();

                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(MapboxMap mapboxMap) {
                            map = mapboxMap;

                            mapboxMap.clear();

                            if (hasCurrentPoi) {
                                IconFactory iconFactory = IconFactory.getInstance(mainContext);
                                Icon icon = iconFactory.fromResource(R.drawable.ic_poi_marker_green);

                                mapboxMap.addMarker(new MarkerViewOptions()
                                        .position(new LatLng(currentPoiLatLng))
                                        .title(currentPoiName)
                                        .icon(icon)
                                );
                            }
                        }
                    });
                }
            }
        });

//        mapView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!floatingActionMenu.isMenuHidden()) {
//                    floatingActionMenu.close(true);
//                    return true;
//                }
//                return false;
//            }
//        });


        return convertView;
    }

    private void toggleGps(boolean enableGps) {

        if (enableGps) {
            // Check if user has granted location permission
            permissionsManager = new PermissionsManager(this);
            if (!PermissionsManager.areLocationPermissionsGranted(getActivity())) {
                permissionsManager.requestLocationPermissions(getActivity());
            } else {
                enableLocation(true);
            }
        } else {
            enableLocation(false);
        }
    }

    private void enableLocation(boolean enabled) {
        if (enabled) {

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        11);
            }

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        12);
            }

            // If we have the last location of the user, we can move the camera to that position.
            Location lastLocation = locationEngine.getLastLocation();
            if (lastLocation != null) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 12));
            }

            locationEngineListener = new LocationEngineListener() {
                @Override
                public void onConnected() {
                    // No action needed here.
                }

                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        // Move the map camera to where the user location is and then remove the
                        // listener so the camera isn't constantly updating when the user location
                        // changes. When the user disables and then enables the location again, this
                        // listener is registered again and will adjust the camera once again.
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location), 16));
                        locationEngine.removeLocationEngineListener(this);
                    }
                }
            };
            locationEngine.addLocationEngineListener(locationEngineListener);
            showMyLocationFAB.setImageResource(R.drawable.ic_location_disabled);
        } else {
            showMyLocationFAB.setImageResource(R.drawable.ic_show_location);
        }
        // Enable or disable the location layer on the map
        map.setMyLocationEnabled(enabled);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getContext(), "Az alkalmazás jogosultságot kér a helymeghatározáshoz.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation(true);
        } else {
            Toast.makeText(getContext(), "Megtagadtad a hozzáférést.",
                    Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and Name
        void messageFromChildFragment(Uri uri);
    }
}