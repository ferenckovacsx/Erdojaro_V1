package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.POI;
import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.Trip;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.TripListAdapter;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;


public class DiscoverTrips extends Fragment {

    ListView tripListView;
    private static TripListAdapter adapter;
    ArrayList<Trip> TripList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View tripView = inflater.inflate(R.layout.fragment_discover_trips, container, false);

        tripListView = (ListView) tripView.findViewById(R.id.TRIP_listview);

        ArrayList<Double> waypointsLong = new ArrayList<>();
        waypointsLong.add(20.630893093);
        waypointsLong.add(20.631957762);
        waypointsLong.add(20.632141661);
        waypointsLong.add(20.632624207);
        waypointsLong.add(20.632034624);
        waypointsLong.add(20.632068990);
        waypointsLong.add(20.631936388);
        waypointsLong.add(20.631772103);
        waypointsLong.add(20.632852782);
        waypointsLong.add(20.632847585);


        ArrayList<Double> waypointsLat = new ArrayList<>();
        waypointsLat.add(48.057708638);
        waypointsLat.add(48.058386482);
        waypointsLat.add(48.058727039);
        waypointsLat.add(48.058723267);
        waypointsLat.add(48.058644729);
        waypointsLat.add(48.058961732);
        waypointsLat.add(48.059180500);
        waypointsLat.add(48.059986252);
        waypointsLat.add(48.059534635);
        waypointsLat.add(48.059557518);


        ArrayList<LatLng> wayPointList = new ArrayList<>();

        for (int i = 0; i < waypointsLat.size(); i++) {
                wayPointList.add(new LatLng(waypointsLat.get(i), waypointsLong.get(i)));
            }

        Log.i("waypoint", "list: " + wayPointList);

        TripList = new ArrayList<>();
        TripList.add(new Trip("Bükkszentkereszt - \nHoldviola Tanösvény", R.drawable.holdviola, "123456", 3, 26, false, true, "Ez itt a nagymezo", wayPointList));

        Log.i("TripFragment", "launched");
        Log.i("TripFragment", "lista: " + TripList.toString());

        adapter = new TripListAdapter(TripList, getActivity().getApplicationContext());

        tripListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object tripRawObject = adapter.getAdapter().getItem(position);
                Trip tripItem = (Trip) tripRawObject;

                int tripImageID = tripItem.getImageID();
                String tripTitle = tripItem.getName();
                int tripDistance = tripItem.getDistance();
                int tripFavoriteCount = tripItem.getFavoriteCount();
                String tripDescription = tripItem.getDescription();
                ArrayList<LatLng> tripWaypoints = tripItem.getTripWaypoints();

                Log.i("TripClickListener", "imageID: " + tripImageID);
                Log.i("TripClickListener", "title: " + tripTitle);
                Log.i("TripClickListener", "distance: " + tripDistance);
                Log.i("TripClickListener", "description: " + tripDescription);

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putInt("trip_imageid", tripImageID);
                fragmentArgs.putString("trip_title", tripTitle);
                fragmentArgs.putInt("trip_distance", tripDistance);
                fragmentArgs.putInt("trip_favorite_count", tripFavoriteCount);
                fragmentArgs.putString("trip_description", tripDescription);
                fragmentArgs.putParcelableArrayList("trip_waypoints", tripWaypoints);

                TripFragment tripFragment = new TripFragment();
                tripFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, tripFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tripListView.setAdapter(adapter);

        return tripView;

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
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }
}
