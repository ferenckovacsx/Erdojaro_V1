package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
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

/**
 * Created by ferenckovacsx on 2017-07-08.
 */

public class FirstAidItemFragment extends Fragment {

    ImageView poiImageView;
    TextView poiShowOnMapTextview;
    TextView poiNavigateTextview;
    TextView poiAddToFavorites;
    TextView firstaidTitleTextview;
    TextView poiCoordinateTextview;
    TextView firstAidDescriptionTextview;

    TextView toolbarTextView;
    ImageView toolbarBackImage;
    private AppBarLayout appBarLayout;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //MainActivity.bottomNavigationView.getMenu().getItem(3).setChecked(true);

        final View firstAidView = inflater.inflate(R.layout.fragment_firstaid_item, container, false);

        firstaidTitleTextview = firstAidView.findViewById(R.id.firstaid_item_title_textview);
        firstAidDescriptionTextview = firstAidView.findViewById(R.id.firstaid_description_textview);
        toolbarTextView = firstAidView.findViewById(R.id.firstaid_toolbar_textview);
        toolbarBackImage = firstAidView.findViewById(R.id.firstaid_toolbar_back_icon);
        appBarLayout = firstAidView.findViewById(R.id.firstaid_appbar);

        Bundle firstAidBundle = getArguments();
        String firstAidTitle = firstAidBundle.getString("firstaid_title");
        String firstAidDescription = firstAidBundle.getString("firstaid_description");

        toolbarTextView.setText(firstAidTitle);
        firstaidTitleTextview.setText(firstAidTitle);
        firstAidDescriptionTextview.setText(firstAidDescription);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    //Collapsed
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
                transaction.replace(R.id.fragment_container, new FirstAidListFragment(), "firstAidListFragment");
                transaction.commit();
            }
        });

        return firstAidView;
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
