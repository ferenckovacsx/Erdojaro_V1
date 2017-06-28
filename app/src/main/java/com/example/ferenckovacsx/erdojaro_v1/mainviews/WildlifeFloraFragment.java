package com.example.ferenckovacsx.erdojaro_v1.mainviews;

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

import com.example.ferenckovacsx.erdojaro_v1.javabeans.Flora;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.adapters.WildlifeGridAdapter;

import java.util.ArrayList;


public class WildlifeFloraFragment extends Fragment {

    GridView floraGridView;
    private static WildlifeGridAdapter adapter;
    ArrayList<Flora> floraList;
    ArrayList<Integer> floraImageList;
    int[] floraImageArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View floraView = inflater.inflate(R.layout.fragment_wildlife_flora_grid, container, false);

        floraGridView = (GridView) floraView.findViewById(R.id.flora_gridview);

        floraList = new ArrayList<>();
        floraList.add(new Flora(1, getResources().getString(R.string.flora_1_name), getResources().getString(R.string.flora_1_latin_name), R.drawable.flora_1_thumbnail, getResources().getString(R.string.flora_1_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_2_name), getResources().getString(R.string.flora_2_latin_name), R.drawable.flora_2_thumbnail, getResources().getString(R.string.flora_2_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_3_name), getResources().getString(R.string.flora_3_latin_name), R.drawable.flora_3_thumbnail, getResources().getString(R.string.flora_3_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_4_name), getResources().getString(R.string.flora_4_latin_name), R.drawable.flora_4_thumbnail, getResources().getString(R.string.flora_4_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_5_name), getResources().getString(R.string.flora_5_latin_name), R.drawable.flora_5_thumbnail, getResources().getString(R.string.flora_5_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_6_name), getResources().getString(R.string.flora_6_latin_name), R.drawable.flora_6_thumbnail, getResources().getString(R.string.flora_6_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_7_name), getResources().getString(R.string.flora_7_latin_name), R.drawable.flora_7_thumbnail, getResources().getString(R.string.flora_7_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_8_name), getResources().getString(R.string.flora_8_latin_name), R.drawable.flora_8_thumbnail, getResources().getString(R.string.flora_8_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_9_name), getResources().getString(R.string.flora_9_latin_name), R.drawable.flora_9_thumbnail, getResources().getString(R.string.flora_9_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_10_name), getResources().getString(R.string.flora_10_latin_name), R.drawable.flora_10_thumbnail, getResources().getString(R.string.flora_10_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_11_name), getResources().getString(R.string.flora_11_latin_name), R.drawable.flora_11_thumbnail, getResources().getString(R.string.flora_11_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_12_name), getResources().getString(R.string.flora_12_latin_name), R.drawable.flora_12_thumbnail, getResources().getString(R.string.flora_12_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_13_name), getResources().getString(R.string.flora_13_latin_name), R.drawable.flora_13_thumbnail, getResources().getString(R.string.flora_13_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_14_name), getResources().getString(R.string.flora_14_latin_name), R.drawable.flora_14_thumbnail, getResources().getString(R.string.flora_14_description)));
        floraList.add(new Flora(1, getResources().getString(R.string.flora_15_name), getResources().getString(R.string.flora_15_latin_name), R.drawable.flora_15_thumbnail, getResources().getString(R.string.flora_15_description)));


        floraImageList = new ArrayList<>();
        for (int i = 0; i < floraList.size(); i++){
            floraImageList.add(floraList.get(i).getImageID());
        }

        //faunaImageArray = faunaImageList.toArray(faunaImageArray);
        floraImageArray = convertIntegers(floraImageList);

        Log.i("flora image", "list: " + floraImageList);
        Log.i("flora image", "array: " + floraImageArray);
        Log.i("floraFragment", "lista" + floraList.toString());

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), floraImageArray);

        floraGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Flora selectedFloraItem = floraList.get(position);

                Log.i("selectedFloraItem", "Item: " + selectedFloraItem);

                int floraId = selectedFloraItem.getId();
                int floraImageId = selectedFloraItem.getImageID();
                String floraName = selectedFloraItem.getName();
                String floraLatinName = selectedFloraItem.getLatinName();
                String floraDescription = selectedFloraItem.getDescription();

                Log.i("floraGridOnItemClick", "name: " + floraName);
                Log.i("floraGridOnItemClick", "latin: " + floraLatinName);
                Log.i("floraGridOnItemClick", "desc: " + floraDescription);

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putInt("wildlife_id", floraId);
                fragmentArgs.putInt("wildlife_imageId", floraImageId);
                fragmentArgs.putString("wildlife_title", floraName);
                fragmentArgs.putString("wildlife_latin_title", floraLatinName);
                fragmentArgs.putString("wildlife_description", floraDescription);

                WildlifeItemFragment wildlifeItemFragment = new WildlifeItemFragment();
                wildlifeItemFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, wildlifeItemFragment);
                transaction.addToBackStack("discoverPoiFragment");
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
