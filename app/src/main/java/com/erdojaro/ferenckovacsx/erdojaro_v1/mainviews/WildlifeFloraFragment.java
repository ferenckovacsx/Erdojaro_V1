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
import android.widget.GridView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.WildlifeGridAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Flora;

import java.util.ArrayList;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;


public class WildlifeFloraFragment extends Fragment {

    GridView floraGridView;
    private static WildlifeGridAdapter adapter;
    ArrayList<Flora> floraList;
    ArrayList<Integer> floraImageList;
    ArrayList<String> floraNameList;
    int[] floraImageArray;
    static int lastPosition;
    String[] floraNameStringArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View floraView = inflater.inflate(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.fragment_wildlife_flora_grid, container, false);

        floraGridView = floraView.findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.flora_gridview);

        floraGridView.setSelection(lastPosition);
        Log.i("floraGrid", "lastPosFromSP " + lastPosition);


        floraList = new ArrayList<>();

        int drawableId;
        int descriptionResId;
        int nameResId;
        int latinNameResId;

        for (int i = 2; i < 74; i++){
            drawableId = mainContext.getResources().getIdentifier("flora_" + i, "drawable", mainContext.getPackageName());
            descriptionResId = mainContext.getResources().getIdentifier("flora_" + i + "_description", "string", mainContext.getPackageName());
            nameResId = mainContext.getResources().getIdentifier("flora_" + i + "_name", "string", mainContext.getPackageName());
            latinNameResId = mainContext.getResources().getIdentifier("flora_" + i + "_latin_name", "string", mainContext.getPackageName());
            Flora flora = new Flora(i, getResources().getString(nameResId), getResources().getString(latinNameResId), drawableId, getResources().getString(descriptionResId));
            floraList.add(flora);
        }

        Log.i("FloraFragment", "floraList size: " + floraList.size());

        floraImageList = new ArrayList<>();
        for (int i = 0; i < floraList.size(); i++){
            floraImageList.add(floraList.get(i).getImageID());
        }

        floraNameList = new ArrayList<>();
        for (int i = 0; i < floraList.size(); i++){
            floraNameList.add(floraList.get(i).getName());
        }

        floraImageArray = convertIntegers(floraImageList);
        floraNameStringArray = floraNameList.toArray(new String[0]);

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), floraImageArray, floraNameStringArray);

        floraGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Flora selectedFloraItem = floraList.get(position);

                lastPosition = floraGridView.getLastVisiblePosition() - 2;
                Log.i("floraGrid", "lastPosOnClick " + lastPosition);


                int floraId = selectedFloraItem.getId();
                int floraImageId = selectedFloraItem.getImageID();
                String floraName = selectedFloraItem.getName();
                String floraLatinName = selectedFloraItem.getLatinName();
                String floraDescription = selectedFloraItem.getDescription();

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("wildlife_type", "flora");
                fragmentArgs.putInt("wildlife_id", floraId);
                fragmentArgs.putInt("wildlife_imageId", floraImageId);
                fragmentArgs.putString("wildlife_title", floraName);
                fragmentArgs.putString("wildlife_latin_title", floraLatinName);
                fragmentArgs.putString("wildlife_description", floraDescription);

                WildlifeItemFragment wildlifeItemFragment = new WildlifeItemFragment();
                wildlifeItemFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.fragment_container, wildlifeItemFragment, "floraFragment");

                transaction.addToBackStack("floraFragment");
                transaction.commit();
            }
        });

        floraGridView.setAdapter(adapter);
        return floraView;

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

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
