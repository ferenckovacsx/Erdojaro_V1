package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.Manifest;
import android.content.pm.PackageManager;
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
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngineListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;

import java.util.List;


public class MapFragment extends Fragment implements PermissionsListener {

    private MapView mapView = null;
    private MapboxMap map;

    private FloatingActionButton floatingActionButton;
    private LocationEngine locationEngine;
    private LocationEngineListener locationEngineListener;
    private PermissionsManager permissionsManager;

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

                Log.i("toggle GPS", "click");
//                if (map != null) {
                toggleGps(!map.isMyLocationEnabled());
                Log.i("locationenabled", "" + map.isMyLocationEnabled());
//                }
            }
        });


        return convertView;
    }

    private void toggleGps(boolean enableGps) {

        Log.i("location permissions", "toggleGPS");

        if (enableGps) {
            // Check if user has granted location permission
            permissionsManager = new PermissionsManager(this);
            if (!PermissionsManager.areLocationPermissionsGranted(getActivity())) {
                permissionsManager.requestLocationPermissions(getActivity());
            } else {
                Log.i("location permissions", "granted");
                enableLocation(true);
            }
        } else {
            enableLocation(false);
            Log.i("location permissions", "denied");

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


//public class MapFragment extends Fragment {
//
//    private MapView mapFragmentView;
//    Context ctx;
////    private MyLocationNewOverlay mLocationOverlay;
////    private ItemizedOverlay<OverlayItem> itemizedOverlay;
////    private CompassOverlay mCompassOverlay = null;
////    private MinimapOverlay mMinimapOverlay;
////    private ScaleBarOverlay mScaleBarOverlay;
////    private RotationGestureOverlay mRotationGestureOverlay;
////    private CopyrightOverlay mCopyrightOverlay;
//
////    public static MapFragment newInstance() {
////        MapFragment fragment = new MapFragment();
////        return fragment;
////    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ctx = getActivity();
//        Mapbox.getInstance(ctx, getString(R.string.access_token));
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//
//        //get Application context
//        Context ctx = getActivity();
//
////        Mapbox.getInstance(ctx, getString(R.string.access_token));
//
//
//        if (ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
//                    11);
//        }
//
//        if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    12);
//        }
//
//
//        View mapFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
//
//        mapFragmentView.onCreate(savedInstanceState);
//
//        MapView map = (MapView) mapFragmentView.findViewById(R.id.mapView);
////        map.setTileSource(TileSourceFactory.MAPNIK);
////
////        //set zoom controls
////        map.setBuiltInZoomControls(true);
////        map.setMultiTouchControls(true);
////
////        //get arguments from poi or trip fragment
////        Bundle mapBundle = getArguments();
////
////        //check if bundle has any elements
////        if (mapBundle != null) {
////
////            String poiName = mapBundle.getString("poi_title");
////            double latitude = mapBundle.getDouble("poi_lat");
////            double longitude = mapBundle.getDouble("poi_long");
////
////            //create marker
////            OverlayItem marker = new OverlayItem(poiName, "", new GeoPoint(latitude, longitude));
////
////            //add marker to overlay list (ItemizedIconOverlay constructor needs a list)
////            final ArrayList<OverlayItem> items = new ArrayList<>();
////            items.add(marker);
////
////            //create overlay
////            itemizedOverlay = new ItemizedIconOverlay<>(items,
////                    new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
////                        @Override
////                        public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
////                            Toast.makeText(
////                                    getContext(),
////                                    "Item '" + item.getTitle() + "' (index=" + index
////                                            + ") got single tapped up", Toast.LENGTH_LONG).show();
////                            return true;
////                        }
////
////                        @Override
////                        public boolean onItemLongPress(final int index, final OverlayItem item) {
////                            Toast.makeText(
////                                    getContext(),
////                                    "Item '" + item.getTitle() + "' (index=" + index
////                                            + ") got long pressed", Toast.LENGTH_LONG).show();
////                            return false;
////                        }
////                    }, getActivity().getApplicationContext());
////            map.getOverlays().add(itemizedOverlay);
////
////
////            //set map start point
////            IMapController mapController = map.getController();
////            mapController.setZoom(18);
////            GeoPoint startPoint = new GeoPoint(latitude, longitude);
////            mapController.setCenter(startPoint);
////        } else {
////
////            //if there is no bundle, set map start point
////            IMapController mapController = map.getController();
////            mapController.setZoom(12);
////            GeoPoint startPoint = new GeoPoint(48.0485, 20.4937);
////            mapController.setCenter(startPoint);
////        }
////
////
//////        Polyline polyline;
//////        List<List<GeoPoint>> polyLines = new ArrayList<>();
//////        for (int i = 0; i < polyLines.size(); i++) {
//////            polyline = new Polyline();
//////            polyline.setPoints(polyLines.get(i));
//////            polyline.setColor(Color.RED);
//////            polyline.setWidth(4);
//////            polyline.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, map));
//////            polyline.setTitle("Polyline tapped!");
//////            map.getOverlays().add(polyline);
//////        }
////
////
////        Polygon myNewPolygon = new Polygon();
////        ArrayList<GeoPoint> polyLines = new ArrayList<>();
////        polyLines.add(new GeoPoint(48.0577167, 20.6309000));
////        polyLines.add(new GeoPoint(48.0583833, 20.6319500));
////        polyLines.add(new GeoPoint(48.0587333, 20.6321333));
////        polyLines.add(new GeoPoint(48.0587167, 20.6326167));
//////        polyLines.add(new GeoPoint(48.0577167, 20.6309000));
////        myNewPolygon.setPoints(polyLines);
////        map.getOverlays().add(myNewPolygon);
////
////
//////        RoadManager roadManager = new OSRMRoadManager(ctx);
//////
//////        ArrayList<GeoPoint> track = new ArrayList<>();
//////        // TODO: Fill the list with your track points
//////
//////        track.add(new GeoPoint(40.796788, -73.949232));
//////        track.add(new GeoPoint(40.796788, -73.981762));
//////        track.add(new GeoPoint(40.768094, -73.981762));
//////        track.add(new GeoPoint(40.768094, -73.949232));
//////        track.add(new GeoPoint(40.796788, -73.949232));
//////
//////        Road road = roadManager.getRoad(track);
//////        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
//////        map.getOverlays().add(roadOverlay);
//////        map.invalidate();
////
////
//////        //mOsmPathOverlay = new OsmPathOverlay(context);
//////        //mMapView.getOverlayManager().add(mOsmPathOverlay);
//////        Polyline line = new Polyline();
//////        line.setTitle("Central Park, NYC");
//////        line.setWidth(20f);
//////        List<GeoPoint> pts = new ArrayList<>();
//////        //here, we create a polygon, note that you need 5 points in order to make a closed polygon (rectangle)
//////        pts.add(new GeoPoint(40.796788, -73.949232));
//////        pts.add(new GeoPoint(40.796788, -73.981762));
//////        pts.add(new GeoPoint(40.768094, -73.981762));
//////        pts.add(new GeoPoint(40.768094, -73.949232));
//////        pts.add(new GeoPoint(40.796788, -73.949232));
//////        line.setPoints(pts);
//////        line.setGeodesic(true);
//////        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, map));
//////        //Note, the info window will not show if you set the onclick listener
//////        //line can also attach click listeners to the line
//////    /*
//////        line.setOnClickListener(new Polyline.OnClickListener() {
//////            @Override
//////            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
//////                Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show();
//////                return false;
//////            }
//////        });*/
//////        map.getOverlayManager().add(line);
//////        Marker marker = new Marker(map);
//////        marker.setDraggable(false);
//////        marker.setTitle("Central Park");
//////        marker.setPosition(new GeoPoint(((40.796788 - 40.768094) / 2) + 40.768094, ((-73.949232 - -73.981762) / 2) + -73.981762));
//////        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//////        marker.setTitle("Start point");
//////        marker.setDraggable(true);
//////        map.getOverlays().add(marker);
//////        //here, we create a polygon using polygon class, note that you need 4 points in order to make a rectangle
//////        Polygon polygon = new Polygon();
//////        polygon.setTitle("This is a polygon");
//////        polygon.setSubDescription(Polygon.class.getCanonicalName());
//////        polygon.setFillColor(Color.RED);
//////        polygon.setVisible(true);
//////        polygon.setStrokeColor(Color.BLACK);
//////        polygon.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, map));
//////        pts = new ArrayList<>();
//////        pts.add(new GeoPoint(40.886788, -73.959232));
//////        pts.add(new GeoPoint(40.886788, -73.971762));
//////        pts.add(new GeoPoint(40.878094, -73.971762));
//////        pts.add(new GeoPoint(40.878094, -73.959232));
//////        polygon.setPoints(pts);
//////        map.getOverlays().add(polygon);
////
////
////        //allow rotation gestures
////        mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
////        mRotationGestureOverlay.setEnabled(true);
////        map.setMultiTouchControls(true);
////        map.getOverlays().add(this.mRotationGestureOverlay);
////
////        //my location overlay
////        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), map);
////        mLocationOverlay.enableMyLocation();
////        map.getOverlays().add(mLocationOverlay);
////
////        //compass overlay
////        mCompassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), map);
////        mCompassOverlay.enableCompass();
////        map.getOverlays().add(mCompassOverlay);
//
//        return mapFragmentView;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
////    @Override
////    public void onStart() {
////        super.onStart();
////        mapFragmentView.onStart();
////    }
//
////    @Override
////    public void onResume() {
////        super.onResume();
////        mapFragmentView.onResume();
////    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapFragmentView.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapFragmentView.onStop();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapFragmentView.onLowMemory();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapFragmentView.onDestroy();
//    }
//}