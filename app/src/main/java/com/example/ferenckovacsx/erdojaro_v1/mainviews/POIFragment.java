package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class POIFragment extends Fragment {

    ImageView poiImageView;
    TextView poiShowOnMapTextview;
    TextView poiTitleTextview;
    TextView poiCoordinateTextview;
    TextView poiDescriptionTextview;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        final View POIView = inflater.inflate(R.layout.fragment_poi, container, false);

        poiImageView = (ImageView) POIView.findViewById(R.id.poi_image);
        poiTitleTextview = (TextView) POIView.findViewById(R.id.poi_title);
        poiCoordinateTextview = (TextView) POIView.findViewById(R.id.poi_gps_coord);
        poiDescriptionTextview = (TextView) POIView.findViewById(R.id.textview_poi_description);
        poiShowOnMapTextview = (TextView) POIView.findViewById(R.id.poi_text_show_on_map);

        Bundle POIbundle = getArguments();
        int poiID = POIbundle.getInt("poi_id");
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
        poiDescriptionTextview.setText(poiDescription);


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
                transaction.replace(R.id.fragment_container, mapFragment);
                transaction.addToBackStack("poiFragment");
                transaction.commit();

                bottomNavigationView.getMenu().getItem(1).setChecked(true);

//                BottomNavigationView bottomNavigationView;
//                bottomNavigationView = (BottomNavigationView)getView().findViewById(R.id.navigation);
//                bottomNavigationView.getMenu().getItem(2).setChecked(true);
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
