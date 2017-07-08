package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.AccomodationsPagerAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Accomodation;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.FirstAid;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.erdojaro.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;


public class AccomodationsFragment extends Fragment {

    private ViewPager viewPager;
    private static AccomodationsPagerAdapter adapter;
    ArrayList<Accomodation> accomodationArrayList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View accomodationsView = inflater.inflate(R.layout.fragment_accomodations, container, false);

        int descriptionResId;
        int nameResId;
        int latResId;
        int longResId;
        int drawableId;
        accomodationArrayList = new ArrayList<>();
        for (int i = 1; i < 12; i++){
            descriptionResId = mainContext.getResources().getIdentifier("accomodation_" + i + "_description", "string", mainContext.getPackageName());
            nameResId = mainContext.getResources().getIdentifier("accomodation_" + i + "_name", "string", mainContext.getPackageName());
            latResId = mainContext.getResources().getIdentifier("accomodation_" + i + "_latitude", "string", mainContext.getPackageName());
            longResId = mainContext.getResources().getIdentifier("accomodation_" + i + "_longitude", "string", mainContext.getPackageName());
            drawableId = mainContext.getResources().getIdentifier("accomodation_" + i, "drawable", mainContext.getPackageName());
            Accomodation accomodation = new Accomodation(getResources().getString(nameResId), getResources().getString(descriptionResId), drawableId, Double.parseDouble(getResources().getString(latResId)), Double.parseDouble(getResources().getString(longResId)));
            accomodationArrayList.add(accomodation);
        }

        Log.i("AccomFragment", "lista" + accomodationArrayList.toString());

        viewPager = accomodationsView.findViewById(R.id.accomodations_viewpager);
        adapter = new AccomodationsPagerAdapter(accomodationArrayList, getActivity().getApplicationContext());

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(30);

        viewPager.setAdapter(adapter);

        return accomodationsView;

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
        void onFragmentInteraction(Uri uri);
    }
}
