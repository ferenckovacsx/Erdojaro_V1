package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.FirstAidListAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.FirstAid;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.POI;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-07-08.
 */

public class FirstAidListFragment extends Fragment {

    ListView firstAidListView;
    public static ArrayList<FirstAid> firstAidArrayList;
    private static FirstAidListAdapter adapter;

    public static ArrayList<String> firstAidTitleArrayList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View firstAidListFragmentView = inflater.inflate(R.layout.fragment_firstaid_list, container, false);

        firstAidListView = firstAidListFragmentView.findViewById(R.id.firstaid_listview);

        int descriptionResId;
        int nameResId;
        firstAidArrayList = new ArrayList<>();
        for (int i = 1; i < 14; i++){
            descriptionResId = mainContext.getResources().getIdentifier("firstaid_" + i + "_description", "string", mainContext.getPackageName());
            nameResId = mainContext.getResources().getIdentifier("firstaid_" + i + "_name", "string", mainContext.getPackageName());
            FirstAid firstAid = new FirstAid(getResources().getString(nameResId), getResources().getString(descriptionResId));
            firstAidArrayList.add(firstAid);
        }

        firstAidListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {

                Object rawObject = adapter.getAdapter().getItem(position);
                FirstAid firstAidItem = (FirstAid) rawObject;


                String firstAidTitle = firstAidItem.getName();
                String firstAidDescription = firstAidItem.getDescription();

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("firstaid_title", firstAidTitle);
                fragmentArgs.putString("firstaid_description", firstAidDescription);

                FirstAidItemFragment firstAidItemFragment = new FirstAidItemFragment();
                firstAidItemFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.fragment_container, firstAidItemFragment, "firstAidItemFragment");
                transaction.addToBackStack("firstAidItemFragment");
                transaction.commit();
            }
        });

        adapter = new FirstAidListAdapter(firstAidArrayList, getActivity().getApplicationContext());
        firstAidListView.setFastScrollEnabled(true);
        firstAidListView.setScrollingCacheEnabled(false);
        firstAidListView.setAdapter(adapter);

        return firstAidListFragmentView;
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
        void messageFromChildFragment(Uri uri);
    }

    public ArrayList<POI> readPoiFromFile() {
        ArrayList<POI> poiFromFile;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(getActivity().getFilesDir(), "/poi.dat")));
            poiFromFile = (ArrayList<POI>) ois.readObject();
            Log.i("DiscoverPOI", "POI from file" + poiFromFile);
            return poiFromFile;
        } catch (
                Exception ex)

        {
            ex.printStackTrace();
        }
        return null;
    }
}
