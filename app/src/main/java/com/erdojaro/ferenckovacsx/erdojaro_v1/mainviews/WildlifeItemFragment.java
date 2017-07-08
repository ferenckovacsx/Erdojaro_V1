package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ferenckovacsx on 2017-05-28.
 */

public class WildlifeItemFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    ImageView wildlifeImageView;
    TextView funghiEatabilityTextView;
    ImageView wildlifeBackButton;
    TextView wildlifeTitleTextview;
    TextView wildlifeTitleLatinTextview;
    TextView wildlifeDescriptionTextview;
    TextView wildlifeItemToolbarText;
    private boolean appBarIsExpanded = true;
    private AppBarLayout appBarLayout;
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
        wildlifeItemToolbarText = (TextView) wildlifeItemView.findViewById(R.id.wildlife_toolbar_textview);
        wildlifeBackButton = wildlifeItemView.findViewById(R.id.wildlife_toolbar_back_icon);
        funghiEatabilityTextView = wildlifeItemView.findViewById(R.id.funghi_eatability_textview);
        appBarLayout = wildlifeItemView.findViewById(R.id.appbar);

        //only show this if the current item is Funghi type
        funghiEatabilityTextView.setVisibility(View.GONE);

        Bundle wildlifeBundle = getArguments();
        final String wildlifeType = wildlifeBundle.getString("wildlife_type");
        int wildlifeId = wildlifeBundle.getInt("wildlife_id");
        int wildlifeImageId = wildlifeBundle.getInt("wildlife_imageId");
        String wildlifeImageUrl = wildlifeBundle.getString("wildlife_imageUrl");
        final String wildlifeItemTitle = wildlifeBundle.getString("wildlife_title");
        final String wildlifeItemLatinTitle = wildlifeBundle.getString("wildlife_latin_title");
        String wildlifeItemDescription = wildlifeBundle.getString("wildlife_description");
        final int wildlifeGombaEatability = wildlifeBundle.getInt("funghi_eatability"); //1-eheto, 2-nem etkezesi, 3-mergezo

        Log.i("WildlifeItemFragment", "Bundle" + wildlifeItemTitle);


        //if the instance is a Funghi, set icon VISIBLE and set icon according to bundle
        if (wildlifeGombaEatability != 0) {
            if (wildlifeGombaEatability == 1) {
                funghiEatabilityTextView.setText("Ehető");
                funghiEatabilityTextView.setTextColor(ContextCompat.getColor(MainActivity.mainContext, R.color.colorPrimary));
                funghiEatabilityTextView.setVisibility(View.VISIBLE);
                funghiEatabilityTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mushroom_edible, 0, 0);

            } else if (wildlifeGombaEatability == 2) {
                funghiEatabilityTextView.setText("Nem étkezési");
                funghiEatabilityTextView.setTextColor(ContextCompat.getColor(MainActivity.mainContext, R.color.colorTextDark));
                funghiEatabilityTextView.setVisibility(View.VISIBLE);
                funghiEatabilityTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mushroom_non_edible, 0, 0);

            } else if (wildlifeGombaEatability == 3) {
                funghiEatabilityTextView.setText("Mérgező");
                funghiEatabilityTextView.setTextColor(ContextCompat.getColor(MainActivity.mainContext, R.color.colorToxicRed));
                funghiEatabilityTextView.setVisibility(View.VISIBLE);
                funghiEatabilityTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mushroom_toxic, 0, 0);


            }
        }

        //set image
        if (wildlifeImageId != 0) {
            Picasso.with(MainActivity.mainContext).load(wildlifeImageId).into(wildlifeImageView);
        } else {

            Picasso picasso = Picasso.with(MainActivity.mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(MainActivity.mainContext)
                    .load(wildlifeImageUrl)
                    .into(wildlifeImageView);
        }

        wildlifeTitleTextview.setText(wildlifeItemTitle);
        wildlifeTitleLatinTextview.setText(wildlifeItemLatinTitle);
        wildlifeDescriptionTextview.setText(wildlifeItemDescription);
        wildlifeItemToolbarText.setText(wildlifeItemTitle);

        wildlifeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, WildlifeFragment.newInstance(wildlifeType), "wildlifeFauna");
                transaction.commit();
                Log.i("wildlife", "onclick");
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    Log.i("appbar", "collapsed");
                    wildlifeItemToolbarText.setVisibility(View.VISIBLE);
                    wildlifeBackButton.setColorFilter(Color.argb(255, 255, 255, 255));

                } else {
                    //Expanded
                    Log.i("appbar", "expanded");
                    wildlifeItemToolbarText.setVisibility(View.GONE);
                    wildlifeBackButton.setColorFilter(Color.argb(255, 255, 255, 255));
                }
            }
        });

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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.i("wildlife", "offset chanced" + verticalOffset);
    }

}
