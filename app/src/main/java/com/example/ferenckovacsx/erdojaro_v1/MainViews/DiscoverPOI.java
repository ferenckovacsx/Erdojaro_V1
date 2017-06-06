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
import com.example.ferenckovacsx.erdojaro_v1.POIListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;


public class DiscoverPOI extends Fragment {

    ListView poiListView;
    private static POIListAdapter adapter;
    ArrayList<POI> POIList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View poiView = inflater.inflate(R.layout.fragment_discover_poi, container, false);

        poiListView = (ListView) poiView.findViewById(R.id.POI_listview);

        POIList = new ArrayList<>();
        POIList.add(new POI("Nagymező", R.drawable.poi_nagymezo, 48.0791, 20.4981, false, "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
        POIList.add(new POI("blabla", R.drawable.fauna, 48.0791, 20.4981, true, "Pelda kep"));
        POIList.add(new POI("blabla", R.drawable.trek, 48.0791, 20.4981, true, "Pelda kep"));


        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + POIList.toString());

        adapter = new POIListAdapter(POIList, getActivity().getApplicationContext());

        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object POIrawObject = adapter.getAdapter().getItem(position);
                POI poiItem = (POI) POIrawObject;

                int POIimageID = poiItem.getImageID();
                String POItitle = poiItem.getName();
                double POIcoordLat = poiItem.getGpsCoordLat();
                double POIcoordLong = poiItem.getGpsCoordLong();
                String POIdescription = poiItem.getDescription();
                LatLng latlong = new LatLng(POIcoordLat, POIcoordLong);

                Log.i("POIclickListener", "imageID: " + POIimageID);
                Log.i("POIclickListener", "title: " + POItitle);
                Log.i("POIclickListener", "gps latitude: " + POIcoordLat);
                Log.i("POIclickListener", "gps longiuted: " + POIcoordLong);
                Log.i("POIclickListener", "description: " + POIdescription);

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putInt("poi_imageid", POIimageID);
                fragmentArgs.putString("poi_title", POItitle);
                fragmentArgs.putDouble("poi_lat", POIcoordLat);
                fragmentArgs.putDouble("poi_long", POIcoordLong);
                fragmentArgs.putString("poi_description", POIdescription);

                POIFragment poiFragment = new POIFragment();
                poiFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, poiFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }
}
