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

import java.util.ArrayList;


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

        TripList = new ArrayList<>();
        TripList.add(new Trip("Bükkszentkereszt - \nHoldviola Tanösvény", R.drawable.holdviola, "123456", 3, 26, false, true, "Ez itt a nagymezo"));
        TripList.add(new Trip("blabla", R.drawable.fauna, "123456", 5, 42, true, false, "Pelda kep"));

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
