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
import com.example.ferenckovacsx.erdojaro_v1.POIListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DiscoverPOI extends Fragment
//        implements AsyncResponse
{

    ListView poiListView;
    private static POIListAdapter adapter;
    ArrayList<POI> poiList;
    ArrayList<POI> poiFromFile;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View poiView = inflater.inflate(R.layout.fragment_discover_poi, container, false);

        //new GetPoiList(this).execute();

        poiFromFile = readPoiFromFile();

        poiListView = (ListView) poiView.findViewById(R.id.POI_listview);

        poiList = new ArrayList<>();
        poiList.add(new POI(1, "Nagymező", R.drawable.poi_nagymezo, 48.0791, 20.4981, false, "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        poiList.add(new POI(2, "blabla", R.drawable.fauna, 48.0791, 20.4981, true, "Pelda kep"));
        poiList.add(new POI(3, "blabla", R.drawable.trek, 48.0791, 20.4981, true, "Pelda kep"));


        poiList.addAll(poiFromFile);
        Log.i("DiscoverPOI", "check if bitmap exists: " + fileExistance());
        Log.i("DiscoverPOI", "POI from root" + poiList.toString());
        Log.i("DiscoverPOI", "combined poiList" + poiList.toString());

        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object POIrawObject = adapter.getAdapter().getItem(position);
                POI poiItem = (POI) POIrawObject;

                int poiID = poiItem.getId();
                String poiTitle = poiItem.getName();
                String poiImageUrl = poiItem.getImageUrl();
                double poiCoordLat = poiItem.getLatitude();
                double poiCoordLong = poiItem.getLongitude();
                String poiDescription = poiItem.getDescription();
                LatLng latlong = new LatLng(poiCoordLat, poiCoordLong);

                Log.i("POIclickListener", "title: " + poiTitle);
                Log.i("POIclickListener", "gps latitude: " + poiCoordLat);
                Log.i("POIclickListener", "gps longiuted: " + poiCoordLong);
                Log.i("POIclickListener", "Description: " + poiDescription);

                Bundle fragmentArgs = new Bundle();

                try {
                    int POIimageID = poiItem.getImageInt();
                    fragmentArgs.putInt("poi_imageid", POIimageID);
                } catch (NullPointerException np) {
                    np.printStackTrace();
                }

                fragmentArgs.putString("poi_imageurl", poiImageUrl);
                fragmentArgs.putInt("poi_id", poiID);
                fragmentArgs.putString("poi_title", poiTitle);
                fragmentArgs.putDouble("poi_lat", poiCoordLat);
                fragmentArgs.putDouble("poi_long", poiCoordLong);
                fragmentArgs.putString("poi_description", poiDescription);

                POIFragment poiFragment = new POIFragment();
                poiFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, poiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        adapter = new POIListAdapter(poiList, getActivity().getApplicationContext());
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
        // TODO: Update argument type and Name
        void messageFromChildFragment(Uri uri);
    }

    public ArrayList<POI> readPoiFromFile() {
        ArrayList<POI> poiFromFile = null;

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

    public boolean fileExistance(){
        File file = new File(getContext().getFilesDir() + "/erdojaroIMG/" + "Trip_3.jpg");
        return file.exists();
    }

//    @Override
//    public void onBackPressed() {
//
//        // If the fragment exists and has some back-stack entry
//        if ( != null && mActivityDirectFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
//            // Get the fragment fragment manager - and pop the backstack
//            mActivityDirectFragment.getChildFragmentManager().popBackStack();
//        }
//        // Else, nothing in the direct fragment back stack
//        else{
//            // Let super handle the back press
//            super.onBackPressed();
//        }
//    }
}
