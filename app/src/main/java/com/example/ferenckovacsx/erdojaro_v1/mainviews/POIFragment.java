package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class POIFragment extends Fragment {

    ImageView poiImageView;
    TextView poiShowOnMapTextview;
    TextView poiAddToFavorites;
    TextView poiTitleTextview;
    TextView poiCoordinateTextview;
    TextView poiDescriptionTextview;
    Set<String> favoritedItems;
    SharedPreferences sharedpreferences;
    boolean isItFavorited;
    String poiIdentifierString;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        final View POIView = inflater.inflate(R.layout.fragment_poi, container, false);

        poiImageView = POIView.findViewById(R.id.poi_image);
        poiTitleTextview = POIView.findViewById(R.id.poi_title);
        poiCoordinateTextview = POIView.findViewById(R.id.poi_gps_coord);
        poiDescriptionTextview = POIView.findViewById(R.id.textview_poi_description);
        poiShowOnMapTextview = POIView.findViewById(R.id.poi_text_show_on_map);
        poiAddToFavorites = POIView.findViewById(R.id.poi_text_favorite);

        Bundle POIbundle = getArguments();
        final int poiID = POIbundle.getInt("poi_id");
        int poiImageID = POIbundle.getInt("poi_imageid");
        String poiImageUrl = POIbundle.getString("poi_imageurl");
        final String poiTitle = POIbundle.getString("poi_title");
        final double poiLat = POIbundle.getDouble("poi_lat");
        final double poiLong = POIbundle.getDouble("poi_long");
        String poiDescription = POIbundle.getString("poi_description");

        if (poiImageID != 0) {
            poiImageView.setImageResource(poiImageID);
        } else {

            Picasso picasso = Picasso.with(mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(mainContext)
                    .load(poiImageUrl)
                    .into(poiImageView);
        }

        poiTitleTextview.setText(poiTitle);
        poiCoordinateTextview.setText("É" + String.valueOf(poiLat) + "  " + "K" + String.valueOf(poiLong));
        //poiDescriptionTextview.setJustificationMode(1);
        poiDescriptionTextview.setText(poiDescription);


        //check if item is already favorited. If yes, set favorite icon active
        //get list of favorited items from sharedpreferences
        sharedpreferences = mainContext.getSharedPreferences("favoritePrefs", Context.MODE_PRIVATE);
        favoritedItems = new HashSet<>(sharedpreferences.getStringSet("favoritedPoi", new HashSet<String>()));
        //check if list of favorited items contains current item
        poiIdentifierString = "POI" + poiID;
        if (favoritedItems.contains(String.valueOf(poiID))) {
            poiAddToFavorites.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_poi_heart_full, 0, 0);
            poiAddToFavorites.setText("Kedvelve");
            isItFavorited = true;
        } else {
            isItFavorited = false;
        }


        //show on map
        poiShowOnMapTextview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("bundle_type", "poi");
                fragmentArgs.putString("poi_title", poiTitle);
                fragmentArgs.putDouble("poi_lat", poiLat);
                fragmentArgs.putDouble("poi_long", poiLong);

                MapFragment mapFragment = new MapFragment();
                mapFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mapFragment, "mapPoiFragment");
                //transaction.addToBackStack("poiFragment");
                transaction.commit();

                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            }
        });

        poiAddToFavorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("POIFragment", "isItFav: " + isItFavorited);

                Set<String> feedbackSet = favoritedItems;
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (isItFavorited) {
                    //if it's already favorited, change icon
                    poiAddToFavorites.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_poi_heart_empty, 0, 0);
                    poiAddToFavorites.setText("Kedvencek közé");
                    //remove item from favoritedlist
                    favoritedItems.remove(String.valueOf(poiID));
                    //update sharedpref with new list
                    editor.putStringSet("favoritedPoi", favoritedItems);
                    editor.apply();
                    isItFavorited = false;
                } else {
                    //if it's not favorited, change icon and add item to favorites set
                    poiAddToFavorites.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_poi_heart_full, 0, 0);
                    poiAddToFavorites.setText("Kedvelve");
                    //add new item to favoritedItems
                    favoritedItems.add(String.valueOf(poiID));
                    editor.putStringSet("favoritedPoi", favoritedItems);
                    editor.apply();
                    isItFavorited = true;
                }
            }
        });


        return POIView;
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

}
