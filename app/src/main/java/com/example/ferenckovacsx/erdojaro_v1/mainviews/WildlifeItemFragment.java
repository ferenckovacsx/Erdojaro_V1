package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class WildlifeItemFragment extends Fragment {

    ImageView wildlifeImageView;
    TextView wildlifeTitleTextview;
    TextView wildlifeTitleLatinTextview;
    TextView wildlifeDescriptionTextview;
    TextView wildlifeItemToolbarText;
    CollapsingToolbarLayout collapsingToolbarLayout;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View wildlifeItemView = inflater.inflate(R.layout.fragment_wildlife_item, container, false);

        wildlifeImageView = (ImageView) wildlifeItemView.findViewById(R.id.wildlife_image);
        wildlifeTitleTextview = (TextView) wildlifeItemView.findViewById(R.id.wildlife_item_title);
        wildlifeTitleLatinTextview = (TextView) wildlifeItemView.findViewById(R.id.wildlife_item_latin);
        wildlifeDescriptionTextview = (TextView) wildlifeItemView.findViewById(R.id.wildlife_item_description);
        //wildlifeItemToolbarText = (TextView) wildlifeItemView.findViewById(R.id.wildlife_toolbar_textview);
        collapsingToolbarLayout = (CollapsingToolbarLayout) wildlifeItemView.findViewById(R.id.collapsing);

        Bundle wildlifeBundle = getArguments();
        int wildlifeId = wildlifeBundle.getInt("wildlife_id");
        int wildlifeImageId = wildlifeBundle.getInt("wildlife_imageId");
        String wildlifeImageUrl = wildlifeBundle.getString("wildlife_imageUrl");
        final String wildlifeItemTitle = wildlifeBundle.getString("wildlife_title");
        final String wildlifeItemLatinTitle = wildlifeBundle.getString("wildlife_latin_title");
        String wildlifeItemDescription = wildlifeBundle.getString("wildlife_description");

        Log.i("WildlifeItemFragment", "Bundle" + wildlifeItemTitle);

        if (wildlifeImageId != 0) {
            wildlifeImageView.setImageResource(wildlifeImageId);
        } else {

            Picasso picasso = Picasso.with(mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(mainContext)
                    .load(wildlifeImageUrl)
                    .into(wildlifeImageView);
        }

        wildlifeTitleTextview.setText(wildlifeItemTitle);
        wildlifeTitleLatinTextview.setText(wildlifeItemLatinTitle);
        wildlifeDescriptionTextview.setText(wildlifeItemDescription);
        //wildlifeItemToolbarText.setText(wildlifeItemTitle);
        collapsingToolbarLayout.setTitle(wildlifeItemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(mainContext, android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(mainContext, R.color.colorTextDark));

        return wildlifeItemView;
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
