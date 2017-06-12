package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
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


public class MapFragment extends Fragment implements PermissionsListener {

    private MapView mapView = null;
    private MapboxMap map;

    private FloatingActionButton floatingActionButton;
    private LocationEngine locationEngine;
    private LocationEngineListener locationEngineListener;
    private PermissionsManager permissionsManager;

    private List<Position> routeCoordinates;

    View convertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //mapbox access token
        Mapbox.getInstance(getActivity(), getString(R.string.access_token));

        convertView = inflater.inflate(R.layout.fragment_map, container, false);

        // Get the location engine object for later use.
        locationEngine = LocationSource.getLocationEngine(getActivity());
        locationEngine.activate();

        mapView = (MapView) convertView.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);


        //get arguments from poi or trip fragment
        Bundle mapBundle = getArguments();

        //check if bundle has any elements
        if (mapBundle != null) {

            final String bundleType = mapBundle.getString("bundle_type");

            if (bundleType != null && bundleType.equals("poi")) {

                Log.i("bundle type", "poi");
                final String poiName = mapBundle.getString("poi_title");
                final double latitude = mapBundle.getDouble("poi_lat");
                final double longitude = mapBundle.getDouble("poi_long");

                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(MapboxMap mapboxMap) {
                        map = mapboxMap;

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latitude, longitude))
                                .title(poiName)
                                .snippet("Welcome to my marker."));

                        mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(latitude, longitude))  // set the camera's center position
                                        .zoom(15)  // set the camera's zoom level
                                        .tilt(20)  // set the camera's tilt
                                        .build()));
                    }
                });

            } else if (bundleType != null && bundleType.equals("trip")){

                final ArrayList<LatLng> tripWaypoints = mapBundle.getParcelableArrayList("trip_waypoints");
                final double[] latitudes = mapBundle.getDoubleArray("trip_latitudes");
                final double[] longitudes = mapBundle.getDoubleArray("trip_longitudes");

                routeCoordinates = new ArrayList<Position>();

                for (int i = 0; i < latitudes.length; i++) {
                    //routeCoordinates.add(Position.fromCoordinates(tripWaypoints.get(i).getLongitude(), tripWaypoints.get(i).getLatitude()));
                    routeCoordinates.add(Position.fromCoordinates(longitudes[i], latitudes[i]));
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
                                        .target(new LatLng(latitudes[1], longitudes[1]))  // set the camera's center position
                                        .zoom(15)  // set the camera's zoom level
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

        floatingActionButton = (FloatingActionButton) convertView.findViewById(R.id.location_toggle_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (map != null) {
                toggleGps(!map.isMyLocationEnabled());
                }
            }
        });


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
            floatingActionButton.setImageResource(R.drawable.ic_location_disabled);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_show_location);
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
        Toast.makeText(getContext(), "This app needs location permissions in order to show its functionality.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation(true);
        } else {
            Toast.makeText(getContext(), "You didn't grant location permissions.",
                    Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }
}