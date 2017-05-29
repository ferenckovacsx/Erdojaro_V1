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

public class POIFragment extends Fragment {

    ImageView POIImageView;
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

        Bundle POIbundle = getArguments();
        int poiImageID = POIbundle.getInt("poi_imageid");
        String poiTitle = POIbundle.getString("poi_title");
        String poiCoord = POIbundle.getString("poi_coord");
        String poiDescription = POIbundle.getString("poi_description");

        POIImageView.setImageResource(poiImageID);
        POITitleTextview.setText(poiTitle);
        POICoordinateTextview.setText(poiCoord);
        POIDescriptionTextview.setText(poiDescription);

        return POIView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }


}
