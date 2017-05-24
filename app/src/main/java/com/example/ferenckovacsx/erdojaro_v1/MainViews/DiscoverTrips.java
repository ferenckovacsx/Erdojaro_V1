package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        TripList.add(new Trip("Bükkszentkereszt - Holdviola Tanösvény", R.drawable.holdviola, "123456", 3, false, true, "Ez itt a nagymezo"));
        TripList.add(new Trip("blabla", R.drawable.fauna, "123456", 5, true, false, "Pelda kep"));

        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + TripList.toString());

        adapter = new TripListAdapter(TripList, getActivity().getApplicationContext());

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
