package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.res.AssetManager;
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
import android.widget.Toast;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;
import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class TripFragment extends Fragment {

    ImageView tripImageView;
    TextView tripShowOnMap;
    TextView tripTitleTextview;
    TextView tripDistanceTextview;
    TextView tripFavoritesCountTextview;
    TextView tripDescriptionTextview;

    TextView toolbarTextView;
    ImageView toolbarBackImage;
    private AppBarLayout appBarLayout;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        // Inflate the layout for this fragment
        View tripView = inflater.inflate(R.layout.fragment_trip, container, false);

        tripImageView = (ImageView) tripView.findViewById(R.id.trip_image);
        tripTitleTextview = (TextView) tripView.findViewById(R.id.trip_title);
        tripDistanceTextview = (TextView) tripView.findViewById(R.id.trip_distance);
        tripFavoritesCountTextview = (TextView) tripView.findViewById(R.id.trip_saved_count);
        tripDescriptionTextview = (TextView) tripView.findViewById(R.id.trip_description);
        tripShowOnMap = (TextView) tripView.findViewById(R.id.text_show_on_map);
        toolbarTextView = tripView.findViewById(R.id.trip_toolbar_textview);
        toolbarBackImage = tripView.findViewById(R.id.trip_toolbar_back_icon);
        appBarLayout = tripView.findViewById(R.id.trip_appbar);

        Bundle tripBundle = getArguments();
        final int tripId = tripBundle.getInt("trip_id");
        int tripImageID = tripBundle.getInt("trip_imageid");
        final String tripImageUrl = tripBundle.getString("trip_imageurl");
        final String tripTitle = tripBundle.getString("trip_title");
        double tripDistance = tripBundle.getDouble("trip_distance");
        int tripFavoriteCount = tripBundle.getInt("trip_favorite_count");
        String tripDescription = tripBundle.getString("trip_description");
        final double[] latitudes = tripBundle.getDoubleArray("trip_latitudes");
        final double[] longitudes = tripBundle.getDoubleArray("trip_longitudes");

        if (tripImageID != 0) {
            tripImageView.setImageResource(tripImageID);
        } else {
            Picasso picasso = Picasso.with(mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(mainContext)
                    .load(tripImageUrl)
                    .into(tripImageView);
        }

        tripTitleTextview.setText(tripTitle);
        toolbarTextView.setText(tripTitle);
        tripDistanceTextview.setText(String.valueOf(tripDistance) + " km");
        tripFavoritesCountTextview.setText("");
        tripDescriptionTextview.setText(tripDescription);

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
                transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("trip"), "trip");
                transaction.commit();
            }
        });


        tripShowOnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("bundle_type", "trip");
                fragmentArgs.putInt("trip_id", tripId);
                fragmentArgs.putString("trip_title", tripTitle);
                fragmentArgs.putDoubleArray("trip_longitudes", longitudes);
                fragmentArgs.putDoubleArray("trip_latitudes", latitudes);

                boolean hasWaypoints;

                //check if there is a tripwaypoints.xml available
                AssetManager mg = getResources().getAssets();
                InputStream is;
                hasWaypoints = true;
                try {
                    is = mg.open("trip_" + tripId + "_waypoints.xml");
                    is.close();
                    //File exists so do something with it
                } catch (IOException ex) {
                    hasWaypoints = false;
                }

                if (hasWaypoints) {
                    MapFragment mapFragment = new MapFragment();
                    mapFragment.setArguments(fragmentArgs);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, mapFragment, "mapTripFragment");
                    //transaction.addToBackStack("tripFragment");
                    transaction.commit();

                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                } else {
                    Toast.makeText(getActivity(), "Nincs elérhető útvonaltérkép.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        // TODO: Update argument type and Name
        void messageFromChildFragment(Uri uri);
    }

}
