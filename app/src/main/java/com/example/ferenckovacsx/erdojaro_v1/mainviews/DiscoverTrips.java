package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.adapters.TripListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Trip;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.TripFilterDialogFragment.FILTERDIALOG_REQUEST_CODE;


public class DiscoverTrips extends Fragment {

    ListView tripListView;
    ArrayList<Trip> tripList;
    ArrayList<Trip> tripListFromFile;
    ArrayList<Trip> totalTripList;
    double[] latitudesPrimitiveArray;
    double[] longitudesPrimitiveArray;
    ImageView filterTripsButton;
    boolean hard;
    boolean easy;
    boolean tanosveny;
    boolean cycling;
    boolean hiking;
    boolean filteringOn;
    int distanceMin;
    int distanceMax;
    int durationMin;
    int durationMax;
    int filterCase;

    private OnFragmentInteractionListener mListener;
    private Bundle savedInstanceState;

    TripListAdapter adapter;

    DiscoverTrips discoverTripsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View tripView = inflater.inflate(R.layout.fragment_discover_trips, container, false);


        discoverTripsFragment = this;

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

        longitudesPrimitiveArray = convertDoubleToPrimitive(waypointsLong);
        latitudesPrimitiveArray = convertDoubleToPrimitive(waypointsLat);

        tripListFromFile = readTripFromFile();

        tripListView = (ListView) tripView.findViewById(R.id.TRIP_listview);
        filterTripsButton = (ImageView) tripView.findViewById(R.id.trip_filter_button);


        tripList = new ArrayList<>();
        totalTripList = new ArrayList<>();
        tripList.add(new Trip(1, "Bükkszentkereszt - \nHoldviola Tanösvény", R.drawable.holdviola, 3, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));        tripList.add(new Trip(1, "Bükkszentkereszt - \nHoldviola Tanösvény", R.drawable.holdviola, 3, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));
        tripList.add(new Trip(1, "Bükkszentkereszt - \nketto", R.drawable.holdviola, 14, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));
        tripList.add(new Trip(1, "Bükkszentkereszt - \nharom", R.drawable.holdviola, 3, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));
        tripList.add(new Trip(1, "Bükkszentkereszt - \nnegy", R.drawable.holdviola, 13, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));
        tripList.add(new Trip(1, "Bükkszentkereszt - \not", R.drawable.holdviola, 3, 26, false, true, true, false, true, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));
        tripList.add(new Trip(1, "Bükkszentkereszt - \nhat", R.drawable.holdviola, 1, 26, false, true, true, true, false, "Ez itt a nagymezo", latitudesPrimitiveArray, longitudesPrimitiveArray));

        tripList.addAll(tripListFromFile);

        Log.i("TripFragment", "lista: " + tripList.toString());

        adapter = new TripListAdapter(tripList, getActivity().getApplicationContext());


        filterTripsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFilterDialog();
            }
        });


        tripListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object tripRawObject = adapter.getAdapter().getItem(position);
                Trip tripItem = (Trip) tripRawObject;

                int tripId = tripItem.getId();
                int tripImageID = tripItem.getImageId();
                String tripTitle = tripItem.getName();
                String tripImageUrl = tripItem.getImageUrl();
                double tripDistance = tripItem.getDistance();
                int tripFavoriteCount = tripItem.getFavoriteCount();
                String tripDescription = tripItem.getDescription();
                double[] latitudes = tripItem.getLatitudes();
                double[] longitudes = tripItem.getLongitudes();

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putInt("trip_id", tripId);
                fragmentArgs.putInt("trip_imageid", tripImageID);
                fragmentArgs.putString("trip_imageurl", tripImageUrl);
                fragmentArgs.putString("trip_title", tripTitle);
                fragmentArgs.putDouble("trip_distance", tripDistance);
                fragmentArgs.putInt("trip_favorite_count", tripFavoriteCount);
                fragmentArgs.putString("trip_description", tripDescription);
                fragmentArgs.putDoubleArray("trip_latitudes", latitudes);
                fragmentArgs.putDoubleArray("trip_longitudes", longitudes);

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("DiscoverTrips", "onActivityCreated");
        //get bundle from filter fragment
        try {
            Bundle tripBundle = getArguments();
            hard = tripBundle.getBoolean("hard");
            easy = tripBundle.getBoolean("easy");
            tanosveny = tripBundle.getBoolean("tanosveny");
            cycling = tripBundle.getBoolean("cycling");
            hiking = tripBundle.getBoolean("hiking");
            filteringOn = tripBundle.getBoolean("filter");
            Log.d("DiscoverTrips", "hard: " + String.valueOf(hard));
            Log.d("DiscoverTrips", "easy: " + String.valueOf(easy));
            Log.d("DiscoverTrips", "tanosveny: " + String.valueOf(tanosveny));
            Log.d("DiscoverTrips", "cycling: " + String.valueOf(cycling));
            Log.d("DiscoverTrips", "hiking: " + String.valueOf(hiking));
        } catch (NullPointerException np) {
            np.printStackTrace();
        }

        Log.i("DiscoverTrips", "onDismissHard" + hard);
        Log.i("DiscoverTrips", "onDismissEasy" + easy);
        Log.i("DiscoverTrips", "onDismissTanosveny" + tanosveny);
        Log.i("DiscoverTrips", "onDismissCycling" + cycling);

        super.onActivityCreated(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and Name
        void messageFromChildFragment(Uri uri);
    }

    public static double[] convertDoubleToPrimitive(ArrayList<Double> doubles) {
        double[] ret = new double[doubles.size()];
        Iterator<Double> iterator = doubles.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ret[i] = iterator.next();
            i++;
        }
        return ret;
    }

    public ArrayList<Trip> readTripFromFile() {
        ArrayList<Trip> tripFromFile;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(getActivity().getFilesDir(), "/trip.dat")));
            tripFromFile = (ArrayList<Trip>) ois.readObject();
            Log.i("DiscoverPOI", "POI from file" + tripFromFile);
            return tripFromFile;
        } catch (
                Exception ex)

        {
            ex.printStackTrace();
        }
        return null;
    }

    private void showFilterDialog() {

        TripFilterDialogFragment dialogFragment = new TripFilterDialogFragment();
        dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialogFragment.setTargetFragment(discoverTripsFragment, FILTERDIALOG_REQUEST_CODE);
        dialogFragment.show(getChildFragmentManager(), "sometag");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILTERDIALOG_REQUEST_CODE) {
            easy = data.getBooleanExtra("easy", false);
            hard = data.getBooleanExtra("hard", false);
            tanosveny = data.getBooleanExtra("tanosveny", false);
            cycling = data.getBooleanExtra("cycling", false);
            hiking = data.getBooleanExtra("hiking", false);
            distanceMin = data.getIntExtra("distanceMin", 0);
            distanceMax = data.getIntExtra("distanceMax", 0);
            durationMin = data.getIntExtra("durationMin", 0);
            durationMax = data.getIntExtra("durationMax", 0);

            Log.i("DiscoverTrips", "OnActResult easy: " + easy);
            Log.i("DiscoverTrips", "OnActResult hard: " + hard);
            Log.i("DiscoverTrips", "OnActResult tanosveny: " + tanosveny);
            Log.i("DiscoverTrips", "OnActResult cycling: " + cycling);
            Log.i("DiscoverTrips", "OnActResult minD: " + distanceMin);
            Log.i("DiscoverTrips", "OnActResult maxD: " + distanceMax);
            Log.i("DiscoverTrips", "OnActResult durationMin: " + durationMin);
            Log.i("DiscoverTrips", "OnActResult durationMax: " + durationMax);

            ArrayList<Trip> filteredTripList = new ArrayList<>();

            for (Trip currentInstance : tripList) {

                if (easy && hard && tanosveny && cycling && hiking &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 1");
                }

                if (easy && hard && !tanosveny && cycling && hiking &&
                        !currentInstance.isTanosveny() && currentInstance.isCycling() && currentInstance.isCycling() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 2");
                }

                if (easy && hard && !tanosveny && !cycling && hiking &&
                        !currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isCycling() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 3");
                }

                if (easy && hard && !tanosveny && cycling && !hiking &&
                        !currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 4");
                }

                if (easy && hard && tanosveny && !cycling && hiking &&
                        currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 5");
                }

                if (easy && hard && tanosveny && cycling && !hiking &&
                        currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 6");
                }

                if (easy && !hard && tanosveny && cycling && hiking &&
                        !currentInstance.isItHard() && currentInstance.isTanosveny() && currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 7");
                }

                if (easy && !hard && !tanosveny && cycling && hiking &&
                        !currentInstance.isItHard() && !currentInstance.isTanosveny() && currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 8");
                }

                if (easy && !hard && tanosveny && !cycling && hiking &&
                        !currentInstance.isItHard() && currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 9");
                }

                if (easy && !hard && tanosveny && cycling && !hiking &&
                        !currentInstance.isItHard() && currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 10");
                }


                if (easy && !hard && !tanosveny && !cycling && hiking &&
                        !currentInstance.isItHard() && !currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 11");
                }

                if (easy && !hard && !tanosveny && cycling && !hiking &&
                        !currentInstance.isItHard() && !currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 12");
                }

                if (!easy && hard && tanosveny && cycling && hiking &&
                        currentInstance.isItHard() && currentInstance.isTanosveny() && currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 13");
                }

                if (!easy && hard && !tanosveny && cycling && hiking &&
                        currentInstance.isItHard() && !currentInstance.isTanosveny() && currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 14");
                }

                if (!easy && hard && !tanosveny && !cycling && hiking &&
                        currentInstance.isItHard() && !currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 15");
                }

                if (!easy && hard && !tanosveny && cycling && !hiking &&
                        currentInstance.isItHard() && !currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 16");
                }

                if (!easy && hard && tanosveny && cycling && !hiking &&
                        currentInstance.isItHard() && currentInstance.isTanosveny() && currentInstance.isCycling() && !currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 17");
                }

                if (!easy && hard && tanosveny && !cycling && hiking &&
                        currentInstance.isItHard() && currentInstance.isTanosveny() && !currentInstance.isCycling() && currentInstance.isHiking() &&
                        isBetween(currentInstance.getDistance(), distanceMin, distanceMax)) {
                    filteredTripList.add(currentInstance);
                    Log.i("DiscoverTrips", "filter case 18");
                }
            }

            adapter = new TripListAdapter(filteredTripList, getActivity().getApplicationContext());

            Log.i("DiscoverTrips", "ITERATOR list: " + filteredTripList.toString());
            Log.i("DiscoverTrips", "ITERATOR list size: " + filteredTripList.size());

            tripListView.setAdapter(adapter);

        }
    }

    public int getFilterCase(boolean isItHard,
                             boolean isItEasy,
                             boolean isItTanosveny,
                             boolean isItCycling,
                             boolean isItHiking,
                             int distanceMin,
                             int distanceMax,
                             int durationMin,
                             int durationMax) {

        if (isItHard && isItEasy && isItTanosveny && isItCycling && isItHiking) {

        }


        return 0;
    }

    public static boolean isBetween(double value, int min, int max) {
        return ((value >= min) && (value <= max));
    }
}
