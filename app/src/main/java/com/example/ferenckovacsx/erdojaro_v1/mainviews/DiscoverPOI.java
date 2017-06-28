package com.example.ferenckovacsx.erdojaro_v1.mainviews;

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

import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.example.ferenckovacsx.erdojaro_v1.adapters.POIListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;


public class DiscoverPOI extends Fragment {

    ListView poiListView;
    ArrayList<POI> poiList;
    ArrayList<POI> poiFromFile;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        View poiView = inflater.inflate(R.layout.fragment_discover_poi, container, false);
        POIListAdapter adapter;

        poiFromFile = readPoiFromFile();

        poiListView = (ListView) poiView.findViewById(R.id.POI_listview);

        poiList = new ArrayList<>();
        poiList.add(new POI(1, getResources().getString(R.string.poi_1_name), R.drawable.poi_1,  Double.parseDouble(getResources().getString(R.string.poi_1_latitude)), Double.parseDouble(getResources().getString(R.string.poi_1_longitude)), false, getResources().getString(R.string.poi_1_description)));
        poiList.add(new POI(2, getResources().getString(R.string.poi_2_name), R.drawable.poi_2,  Double.parseDouble(getResources().getString(R.string.poi_2_latitude)), Double.parseDouble(getResources().getString(R.string.poi_2_longitude)), false, getResources().getString(R.string.poi_2_description)));
        poiList.add(new POI(3, getResources().getString(R.string.poi_3_name), R.drawable.poi_3,  Double.parseDouble(getResources().getString(R.string.poi_3_latitude)), Double.parseDouble(getResources().getString(R.string.poi_3_longitude)), false, getResources().getString(R.string.poi_3_description)));
        poiList.add(new POI(4, getResources().getString(R.string.poi_4_name), R.drawable.poi_4,  Double.parseDouble(getResources().getString(R.string.poi_4_latitude)), Double.parseDouble(getResources().getString(R.string.poi_4_longitude)), false, getResources().getString(R.string.poi_4_description)));
        poiList.add(new POI(5, getResources().getString(R.string.poi_5_name), R.drawable.poi_5,  Double.parseDouble(getResources().getString(R.string.poi_5_latitude)), Double.parseDouble(getResources().getString(R.string.poi_5_longitude)), false, getResources().getString(R.string.poi_5_description)));
        poiList.add(new POI(6, getResources().getString(R.string.poi_6_name), R.drawable.poi_6,  Double.parseDouble(getResources().getString(R.string.poi_6_latitude)), Double.parseDouble(getResources().getString(R.string.poi_6_longitude)), false, getResources().getString(R.string.poi_6_description)));
        poiList.add(new POI(7, getResources().getString(R.string.poi_7_name), R.drawable.poi_7,  Double.parseDouble(getResources().getString(R.string.poi_7_latitude)), Double.parseDouble(getResources().getString(R.string.poi_7_longitude)), false, getResources().getString(R.string.poi_7_description)));
        poiList.add(new POI(8, getResources().getString(R.string.poi_8_name), R.drawable.poi_8,  Double.parseDouble(getResources().getString(R.string.poi_8_latitude)), Double.parseDouble(getResources().getString(R.string.poi_8_longitude)), false, getResources().getString(R.string.poi_8_description)));
        poiList.add(new POI(9, getResources().getString(R.string.poi_9_name), R.drawable.poi_9,  Double.parseDouble(getResources().getString(R.string.poi_9_latitude)), Double.parseDouble(getResources().getString(R.string.poi_9_longitude)), false, getResources().getString(R.string.poi_9_description)));
        poiList.add(new POI(10, getResources().getString(R.string.poi_10_name), R.drawable.poi_10,  Double.parseDouble(getResources().getString(R.string.poi_10_latitude)), Double.parseDouble(getResources().getString(R.string.poi_10_longitude)), false, getResources().getString(R.string.poi_10_description)));
        poiList.add(new POI(11, getResources().getString(R.string.poi_11_name), R.drawable.poi_11,  Double.parseDouble(getResources().getString(R.string.poi_11_latitude)), Double.parseDouble(getResources().getString(R.string.poi_11_longitude)), false, getResources().getString(R.string.poi_11_description)));

        poiList.addAll(poiFromFile);
        Log.i("DiscoverPOI", "combined poiList" + poiList.toString());

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

                Log.i("POIclickListener", "title: " + poiTitle);
                Log.i("POIclickListener", "gps latitude: " + poiCoordLat);
                Log.i("POIclickListener", "gps longiuted: " + poiCoordLong);
                Log.i("POIclickListener", "Description: " + poiDescription);

                Bundle fragmentArgs = new Bundle();

//                try {
//                    int poiImageID = poiItem.getImageInt();
//                    fragmentArgs.putInt("poi_imageid", poiImageID);
//                } catch (NullPointerException np) {
//                    np.printStackTrace();
//                }

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
                transaction.replace(R.id.fragment_container, poiFragment);
                transaction.addToBackStack("discoverPoiFragment");
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
