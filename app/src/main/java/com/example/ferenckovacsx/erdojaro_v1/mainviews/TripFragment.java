package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.graphics.Bitmap;
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

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.Utils;

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
        final String tripTitle = tripBundle.getString("trip_title");
        double tripDistance = tripBundle.getDouble("trip_distance");
        int tripFavoriteCount = tripBundle.getInt("trip_favorite_count");
        String tripDescription = tripBundle.getString("trip_description");
        final double[] latitudes = tripBundle.getDoubleArray("trip_latitudes");
        final double[] longitudes = tripBundle.getDoubleArray("trip_longitudes");

        if (tripImageID != 0) {
            tripImageView.setImageResource(tripImageID);
        } else {
            String bitmapFileName = "Trip_" + tripId + ".jpg";
            Log.i("TripFragment", "bitmapfile: " + bitmapFileName);
            Bitmap tripBitmap = Utils.getBitmapFromFile(bitmapFileName, getActivity());
            tripImageView.setImageBitmap(tripBitmap);
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

                MapFragment mapFragment = new MapFragment();
                mapFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mapFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return tripView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and Name
        void messageFromChildFragment(Uri uri);
    }

}
