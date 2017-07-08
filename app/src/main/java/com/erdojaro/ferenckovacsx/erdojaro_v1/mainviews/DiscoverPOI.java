package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.erdojaro.ferenckovacsx.erdojaro_v1.XMLParser;
import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.POIListAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.TripWaypoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


public class DiscoverPOI extends Fragment {

    ListView poiListView;
    public static ArrayList<POI> poiList;
    ArrayList<POI> poiFromFile;
    SharedPreferences sharedPreferences;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = MainActivity.mainContext.getSharedPreferences("favoritePrefs", MODE_PRIVATE);
        Set<String> favoritedItems = sharedPreferences.getStringSet("favoritedPoi", new HashSet<String>());
        Log.i("favoritedPoi", "id: " + favoritedItems);

        MainActivity.bottomNavigationView.getMenu().getItem(0).setChecked(true);

        View poiView = inflater.inflate(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.fragment_discover_poi, container, false);
        POIListAdapter adapter;

        //poiFromFile = readPoiFromFile();

        poiListView = poiView.findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.POI_listview);



        poiList = new ArrayList<>();

        //get list of poi from XML file (poi.xml)
        XMLParser xmlParser = new XMLParser();
        poiList = xmlParser.getPoiListFromXml();
        Log.i("poi from XML", "" + poiList);


        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object POIrawObject = adapter.getAdapter().getItem(position);
                POI poiItem = (POI) POIrawObject;

                int poiID = poiItem.getId();
                int poiImageID = poiItem.getImageInt();
                String poiTitle = poiItem.getName();
                String poiImageUrl = poiItem.getImageUrl();
                double poiCoordLat = poiItem.getLatitude();
                double poiCoordLong = poiItem.getLongitude();
                String poiDescription = poiItem.getDescription();

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("poi_imageurl", poiImageUrl);
                fragmentArgs.putInt("poi_imageid", poiImageID);
                fragmentArgs.putInt("poi_id", poiID);
                fragmentArgs.putString("poi_title", poiTitle);
                fragmentArgs.putDouble("poi_lat", poiCoordLat);
                fragmentArgs.putDouble("poi_long", poiCoordLong);
                fragmentArgs.putString("poi_description", poiDescription);

                POIFragment poiFragment = new POIFragment();
                poiFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.fragment_container, poiFragment, "poiFragment");
                transaction.addToBackStack("poiFragment");
                transaction.commit();
            }
        });

        adapter = new POIListAdapter(poiList, getActivity().getApplicationContext());
        poiListView.setFastScrollEnabled(true);
        poiListView.setScrollingCacheEnabled(false);
        poiListView.setAdapter(adapter);

        return poiView;

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
        void messageFromChildFragment(Uri uri);
    }

    public ArrayList<POI> readPoiFromFile() {
        ArrayList<POI> poiFromFile;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(getActivity().getFilesDir(), "/poi.dat")));
            poiFromFile = (ArrayList<POI>) ois.readObject();
            Log.i("DiscoverPOI", "POI from file" + poiFromFile);
            return poiFromFile;
        } catch (
                Exception ex)

        {
            ex.printStackTrace();
        }
        return null;
    }
}
