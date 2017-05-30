package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class POIFragment extends Fragment {

    ImageView POIImageView;
    ImageView POIshowOnMapIcon;
    TextView POITitleTextview;
    TextView POICoordinateTextview;
    TextView POIDescriptionTextview;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View POIView = inflater.inflate(R.layout.fragment_poi, container, false);

        POIImageView = (ImageView) POIView.findViewById(R.id.poi_image);
        POITitleTextview = (TextView) POIView.findViewById(R.id.poi_title);
        POICoordinateTextview = (TextView) POIView.findViewById(R.id.poi_gps_coord);
        POIDescriptionTextview = (TextView) POIView.findViewById(R.id.textview_poi_description);
        POIshowOnMapIcon = (ImageView) POIView.findViewById(R.id.poi_show_on_map_icon);

        Bundle POIbundle = getArguments();
        int poiImageID = POIbundle.getInt("poi_imageid");
        final String poiTitle = POIbundle.getString("poi_title");
        final double poiLat = POIbundle.getDouble("poi_lat");
        final double poiLong = POIbundle.getDouble("poi_long");
        String poiDescription = POIbundle.getString("poi_description");

        POIImageView.setImageResource(poiImageID);
        POITitleTextview.setText(poiTitle);
        POICoordinateTextview.setText("É" + String.valueOf(poiLat) + " " + "K" + String.valueOf(poiLong));
        POIDescriptionTextview.setText(poiDescription);

        POIshowOnMapIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("poi_title", poiTitle);
                fragmentArgs.putDouble("poi_lat", poiLat);
                fragmentArgs.putDouble("poi_long", poiLong);

                MapFragment mapFragment = new MapFragment();
                mapFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mapFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return POIView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }


}
