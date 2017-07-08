package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class POIFragment extends Fragment {

    ImageView poiImageView;
    TextView poiShowOnMapTextview;
    TextView poiNavigateTextview;
    TextView poiAddToFavorites;
    TextView poiTitleTextview;
    TextView poiCoordinateTextview;
    TextView poiDescriptionTextview;

    TextView toolbarTextView;
    ImageView toolbarBackImage;
    private AppBarLayout appBarLayout;

    Set<String> favoritedItems;
    SharedPreferences sharedpreferences;
    boolean isItFavorited;
    String poiIdentifierString;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MainActivity.bottomNavigationView.getMenu().getItem(0).setChecked(true);

        final View POIView = inflater.inflate(R.layout.fragment_poi, container, false);

        poiImageView = POIView.findViewById(R.id.poi_image);
        poiTitleTextview = POIView.findViewById(R.id.poi_title);
        poiCoordinateTextview = POIView.findViewById(R.id.poi_gps_coord);
        poiDescriptionTextview = POIView.findViewById(R.id.textview_poi_description);
        poiShowOnMapTextview = POIView.findViewById(R.id.poi_text_show_on_map);
        poiNavigateTextview = POIView.findViewById(R.id.poi_text_navigate);
        poiAddToFavorites = POIView.findViewById(R.id.poi_text_favorite);
        toolbarTextView = POIView.findViewById(R.id.poi_toolbar_textview);
        toolbarBackImage = POIView.findViewById(R.id.poi_toolbar_back_icon);
        appBarLayout = POIView.findViewById(R.id.poi_appbar);


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

            Picasso picasso = Picasso.with(MainActivity.mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(MainActivity.mainContext)
                    .load(poiImageUrl)
                    .into(poiImageView);
        }

        poiTitleTextview.setText(poiTitle);
        toolbarTextView.setText(poiTitle);
        poiCoordinateTextview.setText("É" + String.valueOf(poiLat) + "  " + "K" + String.valueOf(poiLong));
        //poiDescriptionTextview.setJustificationMode(1);
        poiDescriptionTextview.setText(poiDescription);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    Log.i("appbar", "collapsed");
                    toolbarTextView.setVisibility(View.VISIBLE);
                    toolbarBackImage.setColorFilter(Color.argb(255, 255, 255, 255));

                } else {
                    //Expanded
                    Log.i("appbar", "expanded");
                    toolbarTextView.setVisibility(View.GONE);
                    toolbarBackImage.setColorFilter(Color.argb(255, 255, 255, 255));
                }
            }
        });

        toolbarBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("poi"), "poi");
                transaction.commit();
                Log.i("wildlife", "onclick");
            }
        });


        //check if item is already favorited. If yes, set favorite icon active
        //get list of favorited items from sharedpreferences
        sharedpreferences = MainActivity.mainContext.getSharedPreferences("favoritePrefs", Context.MODE_PRIVATE);
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

                MainActivity.bottomNavigationView.getMenu().getItem(1).setChecked(true);
            }
        });

        poiNavigateTextview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + poiLat + "," + poiLong);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                //startActivity(Intent.createChooser(mapIntent, "Select application"));
                startActivity(mapIntent);

            }
        });

        poiAddToFavorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("POIFragment", "isItFav: " + isItFavorited);

                //Set<String> feedbackSet = favoritedItems;
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
