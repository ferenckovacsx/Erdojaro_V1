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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

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

        Bundle tripBundle = getArguments();
        int tripId = tripBundle.getInt("trip_id");
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
        tripDistanceTextview.setText(String.valueOf(tripDistance) + " km");
        tripFavoritesCountTextview.setText(String.valueOf(tripFavoriteCount));
        tripDescriptionTextview.setText(tripDescription);

        tripShowOnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("bundle_type", "trip");
                fragmentArgs.putString("trip_title", tripTitle);
                fragmentArgs.putDoubleArray("trip_longitudes", longitudes);
                fragmentArgs.putDoubleArray("trip_latitudes", latitudes);

                if (latitudes != null || longitudes != null) {
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
