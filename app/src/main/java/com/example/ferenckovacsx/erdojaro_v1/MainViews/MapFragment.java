package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ferenckovacsx.erdojaro_v1.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.CopyrightOverlay;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapFragment extends Fragment {

    private MapView mapFragmentView;
    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay = null;
    private MinimapOverlay mMinimapOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    private CopyrightOverlay mCopyrightOverlay;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //get Application context
        Context ctx = getActivity();
        //setting user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        if (ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    11);
        }

        if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    123);
        }


        View mapFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        MapView map = (MapView) mapFragmentView.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        //set zoom controls
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        Bundle mapBundle = getArguments();

        if (mapBundle != null) {
            double latitude = mapBundle.getDouble("poi_lat");
            double longitude = mapBundle.getDouble("poi_long");

            //set map start point
            IMapController mapController = map.getController();
            mapController.setZoom(18);
            GeoPoint startPoint = new GeoPoint(latitude, longitude);
            mapController.setCenter(startPoint);
        } else {
            //set map start point
            IMapController mapController = map.getController();
            mapController.setZoom(12);
            GeoPoint startPoint = new GeoPoint(48.0485, 20.4937);
            mapController.setCenter(startPoint);
        }
        
        //allow rotation gestures
        mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
        mRotationGestureOverlay.setEnabled(true);
        map.setMultiTouchControls(true);
        map.getOverlays().add(this.mRotationGestureOverlay);

        //my location overlay
        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);

        //compass overlay
        mCompassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);

        return mapFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}