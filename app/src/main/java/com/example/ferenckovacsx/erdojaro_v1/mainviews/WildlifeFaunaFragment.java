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

import com.example.ferenckovacsx.erdojaro_v1.javabeans.Fauna;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.adapters.WildlifeGridAdapter;

import java.util.ArrayList;


public class WildlifeFaunaFragment extends Fragment {

    GridView faunaGridView;
    private static WildlifeGridAdapter adapter;
    ArrayList<Fauna> faunaArrayList;
    ArrayList<Integer> faunaImageList;
    ArrayList<String> faunaNameList;
    int[] faunaImageArray;
    String[] faunaNameArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View faunaView = inflater.inflate(R.layout.fragment_wildlife_fauna_grid, container, false);

        faunaGridView = (GridView) faunaView.findViewById(R.id.fauna_gridview);

        faunaArrayList = new ArrayList<>();
        faunaArrayList.add(new Fauna(1, getResources().getString(R.string.fauna_1_name), getResources().getString(R.string.fauna_1_latin_name), R.drawable.fauna_1, getResources().getString(R.string.fauna_1_description)));
        faunaArrayList.add(new Fauna(2, getResources().getString(R.string.fauna_2_name), getResources().getString(R.string.fauna_2_latin_name), R.drawable.fauna_2, getResources().getString(R.string.fauna_2_description)));
        faunaArrayList.add(new Fauna(3, getResources().getString(R.string.fauna_3_name), getResources().getString(R.string.fauna_3_latin_name), R.drawable.fauna_3, getResources().getString(R.string.fauna_3_description)));
        faunaArrayList.add(new Fauna(4, getResources().getString(R.string.fauna_4_name), getResources().getString(R.string.fauna_4_latin_name), R.drawable.fauna_4, getResources().getString(R.string.fauna_4_description)));
        faunaArrayList.add(new Fauna(5, getResources().getString(R.string.fauna_5_name), getResources().getString(R.string.fauna_5_latin_name), R.drawable.fauna_5, getResources().getString(R.string.fauna_5_description)));
        faunaArrayList.add(new Fauna(6, getResources().getString(R.string.fauna_6_name), getResources().getString(R.string.fauna_6_latin_name), R.drawable.fauna_6, getResources().getString(R.string.fauna_6_description)));
        faunaArrayList.add(new Fauna(7, getResources().getString(R.string.fauna_7_name), getResources().getString(R.string.fauna_7_latin_name), R.drawable.fauna_7, getResources().getString(R.string.fauna_7_description)));
        faunaArrayList.add(new Fauna(8, getResources().getString(R.string.fauna_8_name), getResources().getString(R.string.fauna_8_latin_name), R.drawable.fauna_8, getResources().getString(R.string.fauna_8_description)));
        faunaArrayList.add(new Fauna(9, getResources().getString(R.string.fauna_9_name), getResources().getString(R.string.fauna_9_latin_name), R.drawable.fauna_9, getResources().getString(R.string.fauna_9_description)));
        faunaArrayList.add(new Fauna(10, getResources().getString(R.string.fauna_10_name), getResources().getString(R.string.fauna_10_latin_name), R.drawable.fauna_10, getResources().getString(R.string.fauna_10_description)));
        faunaArrayList.add(new Fauna(11, getResources().getString(R.string.fauna_11_name), getResources().getString(R.string.fauna_11_latin_name), R.drawable.fauna_11, getResources().getString(R.string.fauna_11_description)));
        faunaArrayList.add(new Fauna(12, getResources().getString(R.string.fauna_12_name), getResources().getString(R.string.fauna_12_latin_name), R.drawable.fauna_12, getResources().getString(R.string.fauna_12_description)));
        faunaArrayList.add(new Fauna(13, getResources().getString(R.string.fauna_13_name), getResources().getString(R.string.fauna_13_latin_name), R.drawable.fauna_13, getResources().getString(R.string.fauna_13_description)));
        faunaArrayList.add(new Fauna(14, getResources().getString(R.string.fauna_14_name), getResources().getString(R.string.fauna_14_latin_name), R.drawable.fauna_14, getResources().getString(R.string.fauna_14_description)));
        faunaArrayList.add(new Fauna(15, getResources().getString(R.string.fauna_15_name), getResources().getString(R.string.fauna_15_latin_name), R.drawable.fauna_15, getResources().getString(R.string.fauna_15_description)));
        faunaArrayList.add(new Fauna(16, getResources().getString(R.string.fauna_16_name), getResources().getString(R.string.fauna_16_latin_name), R.drawable.fauna_16, getResources().getString(R.string.fauna_16_description)));
        faunaArrayList.add(new Fauna(17, getResources().getString(R.string.fauna_17_name), getResources().getString(R.string.fauna_17_latin_name), R.drawable.fauna_17, getResources().getString(R.string.fauna_17_description)));
        faunaArrayList.add(new Fauna(18, getResources().getString(R.string.fauna_18_name), getResources().getString(R.string.fauna_18_latin_name), R.drawable.fauna_18, getResources().getString(R.string.fauna_18_description)));
        faunaArrayList.add(new Fauna(19, getResources().getString(R.string.fauna_19_name), getResources().getString(R.string.fauna_19_latin_name), R.drawable.fauna_19, getResources().getString(R.string.fauna_19_description)));
        faunaArrayList.add(new Fauna(20, getResources().getString(R.string.fauna_20_name), getResources().getString(R.string.fauna_20_latin_name), R.drawable.fauna_20, getResources().getString(R.string.fauna_20_description)));
        faunaArrayList.add(new Fauna(21, getResources().getString(R.string.fauna_21_name), getResources().getString(R.string.fauna_21_latin_name), R.drawable.fauna_21, getResources().getString(R.string.fauna_21_description)));
        faunaArrayList.add(new Fauna(22, getResources().getString(R.string.fauna_22_name), getResources().getString(R.string.fauna_22_latin_name), R.drawable.fauna_22, getResources().getString(R.string.fauna_22_description)));
        faunaArrayList.add(new Fauna(23, getResources().getString(R.string.fauna_23_name), getResources().getString(R.string.fauna_23_latin_name), R.drawable.fauna_23, getResources().getString(R.string.fauna_23_description)));

        faunaImageList = new ArrayList<>();
        for (int i = 0; i < faunaArrayList.size(); i++) {
            faunaImageList.add(faunaArrayList.get(i).getImageID());
        }

        faunaNameList = new ArrayList<>();
        for (int i = 0; i < faunaArrayList.size(); i++){
            faunaNameList.add(faunaArrayList.get(i).getName());
        }

        //faunaImageArray = faunaImageList.toArray(faunaImageArray);
        faunaImageArray = convertIntegers(faunaImageList);
        faunaNameArray = faunaNameList.toArray(new String[0]);


        Log.i("fauna image", "list: " + faunaImageList);
        Log.i("fauna image", "array: " + faunaImageArray);
        Log.i("faunaFragment", "lista" + faunaArrayList.toString());

        Log.i("fauna name", "list: " + faunaNameList);
        Log.i("fauna name", "array: " + faunaNameArray);

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), faunaImageArray, faunaNameArray);

        faunaGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fauna selectedFloraItem = faunaArrayList.get(position);

                Log.i("selectedFaunaItem", "Item: " + selectedFloraItem);

                int faunaId = selectedFloraItem.getId();
                int faunaImageId = selectedFloraItem.getImageID();
                String faunaName = selectedFloraItem.getName();
                String faunaLatinName = selectedFloraItem.getLatinName();
                String faunaDescription = selectedFloraItem.getDescription();

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("wildlife_type", "fauna");
                fragmentArgs.putInt("wildlife_id", faunaId);
                fragmentArgs.putInt("wildlife_imageId", faunaImageId);
                fragmentArgs.putString("wildlife_title", faunaName);
                fragmentArgs.putString("wildlife_latin_title", faunaLatinName);
                fragmentArgs.putString("wildlife_description", faunaDescription);

                WildlifeItemFragment wildlifeItemFragment = new WildlifeItemFragment();
                wildlifeItemFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, wildlifeItemFragment, "faunaFragment");
                transaction.addToBackStack("faunaFragment");
                transaction.commit();
            }
        });

        faunaGridView.setAdapter(adapter);
        return faunaView;

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

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
