package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class TripFragment extends Fragment {

    ImageView tripImageView;
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

        Bundle tripBundle = getArguments();
        int tripImageID = tripBundle.getInt("trip_imageid");
        String tripTitle = tripBundle.getString("trip_title");
        int tripDistance = tripBundle.getInt("trip_distance");
        int tripFavoriteCount = tripBundle.getInt("trip_favorite_count");
        String tripDescription = tripBundle.getString("trip_description");

        tripImageView.setImageResource(tripImageID);
        tripTitleTextview.setText(tripTitle);
        tripDistanceTextview.setText(String.valueOf(tripDistance) + " km");
        tripFavoritesCountTextview.setText(String.valueOf(tripFavoriteCount));
        tripDescriptionTextview.setText(tripDescription);

        return tripView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }


}
