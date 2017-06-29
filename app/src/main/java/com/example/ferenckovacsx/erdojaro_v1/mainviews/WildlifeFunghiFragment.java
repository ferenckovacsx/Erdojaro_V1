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
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Funghi;

import java.util.ArrayList;


public class WildlifeFunghiFragment extends Fragment {

    GridView funghiGridView;
    private static WildlifeGridAdapter adapter;
    ArrayList<Funghi> funghiList;
    ArrayList<Integer> funghiImageList;
    ArrayList<String> funghiNameList;
    int[] funghiImageArray;
    String[] funghiNameArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View funghiView = inflater.inflate(R.layout.fragment_wildlife_funghi_grid, container, false);

        funghiGridView = (GridView) funghiView.findViewById(R.id.funghi_gridview);

        funghiList = new ArrayList<>();
        funghiList.add(new Funghi(1, getString(R.string.funghi_1_name), getString(R.string.funghi_1_latin_name), 1, R.drawable.funghi_1, getString(R.string.funghi_1_description)));
        funghiList.add(new Funghi(2, getString(R.string.funghi_2_name), getString(R.string.funghi_2_latin_name), 1, R.drawable.funghi_2, getString(R.string.funghi_2_description)));
        funghiList.add(new Funghi(3, getString(R.string.funghi_3_name), getString(R.string.funghi_3_latin_name), 1, R.drawable.funghi_3, getString(R.string.funghi_3_description)));
        funghiList.add(new Funghi(4, getString(R.string.funghi_4_name), getString(R.string.funghi_4_latin_name), 1, R.drawable.funghi_4, getString(R.string.funghi_4_description)));
        funghiList.add(new Funghi(5, getString(R.string.funghi_5_name), getString(R.string.funghi_5_latin_name), 2, R.drawable.funghi_5, getString(R.string.funghi_5_description)));
        funghiList.add(new Funghi(6, getString(R.string.funghi_6_name), getString(R.string.funghi_6_latin_name), 2, R.drawable.funghi_6, getString(R.string.funghi_6_description)));
        funghiList.add(new Funghi(7, getString(R.string.funghi_7_name), getString(R.string.funghi_7_latin_name), 1, R.drawable.funghi_7, getString(R.string.funghi_7_description)));
        funghiList.add(new Funghi(8, getString(R.string.funghi_8_name), getString(R.string.funghi_8_latin_name), 3, R.drawable.funghi_8, getString(R.string.funghi_8_description)));
        funghiList.add(new Funghi(9, getString(R.string.funghi_9_name), getString(R.string.funghi_9_latin_name), 1, R.drawable.funghi_9, getString(R.string.funghi_9_description)));
        funghiList.add(new Funghi(10, getString(R.string.funghi_10_name), getString(R.string.funghi_10_latin_name), 1, R.drawable.funghi_10, getString(R.string.funghi_10_description)));
        funghiList.add(new Funghi(11, getString(R.string.funghi_11_name), getString(R.string.funghi_11_latin_name), 3, R.drawable.funghi_11, getString(R.string.funghi_11_description)));
        funghiList.add(new Funghi(12, getString(R.string.funghi_12_name), getString(R.string.funghi_12_latin_name), 1, R.drawable.funghi_12, getString(R.string.funghi_12_description)));
        funghiList.add(new Funghi(13, getString(R.string.funghi_13_name), getString(R.string.funghi_13_latin_name), 1, R.drawable.funghi_13, getString(R.string.funghi_13_description)));
        funghiList.add(new Funghi(14, getString(R.string.funghi_14_name), getString(R.string.funghi_14_latin_name), 3, R.drawable.funghi_14, getString(R.string.funghi_14_description)));
        funghiList.add(new Funghi(15, getString(R.string.funghi_15_name), getString(R.string.funghi_15_latin_name), 2, R.drawable.funghi_15, getString(R.string.funghi_15_description)));
        funghiList.add(new Funghi(16, getString(R.string.funghi_16_name), getString(R.string.funghi_16_latin_name), 2, R.drawable.funghi_16, getString(R.string.funghi_16_description)));
        funghiList.add(new Funghi(17, getString(R.string.funghi_17_name), getString(R.string.funghi_17_latin_name), 2, R.drawable.funghi_17, getString(R.string.funghi_17_description)));
        funghiList.add(new Funghi(18, getString(R.string.funghi_18_name), getString(R.string.funghi_18_latin_name), 2, R.drawable.funghi_18, getString(R.string.funghi_18_description)));
        funghiList.add(new Funghi(19, getString(R.string.funghi_19_name), getString(R.string.funghi_19_latin_name), 2, R.drawable.funghi_19, getString(R.string.funghi_19_description)));
        funghiList.add(new Funghi(20, getString(R.string.funghi_20_name), getString(R.string.funghi_20_latin_name), 2, R.drawable.funghi_20, getString(R.string.funghi_20_description)));
        funghiList.add(new Funghi(21, getString(R.string.funghi_21_name), getString(R.string.funghi_21_latin_name), 2, R.drawable.funghi_21, getString(R.string.funghi_21_description)));
        funghiList.add(new Funghi(22, getString(R.string.funghi_22_name), getString(R.string.funghi_22_latin_name), 2, R.drawable.funghi_22, getString(R.string.funghi_22_description)));
        funghiList.add(new Funghi(23, getString(R.string.funghi_23_name), getString(R.string.funghi_23_latin_name), 2, R.drawable.funghi_23, getString(R.string.funghi_23_description)));
        funghiList.add(new Funghi(24, getString(R.string.funghi_24_name), getString(R.string.funghi_24_latin_name), 2, R.drawable.funghi_24, getString(R.string.funghi_24_description)));
        funghiList.add(new Funghi(25, getString(R.string.funghi_25_name), getString(R.string.funghi_25_latin_name), 2, R.drawable.funghi_25, getString(R.string.funghi_25_description)));
        funghiList.add(new Funghi(26, getString(R.string.funghi_26_name), getString(R.string.funghi_26_latin_name), 2, R.drawable.funghi_26, getString(R.string.funghi_26_description)));
        funghiList.add(new Funghi(27, getString(R.string.funghi_27_name), getString(R.string.funghi_27_latin_name), 2, R.drawable.funghi_27, getString(R.string.funghi_27_description)));
        funghiList.add(new Funghi(28, getString(R.string.funghi_28_name), getString(R.string.funghi_28_latin_name), 2, R.drawable.funghi_28, getString(R.string.funghi_28_description)));

        funghiImageList = new ArrayList<>();
        for (int i = 0; i < funghiList.size(); i++) {
            funghiImageList.add(funghiList.get(i).getImageID());
        }

        funghiNameList = new ArrayList<>();
        for (int i = 0; i < funghiList.size(); i++) {
            funghiNameList.add(funghiList.get(i).getName());
        }

        //faunaImageArray = faunaImageList.toArray(faunaImageArray);
        funghiImageArray = convertIntegers(funghiImageList);
        funghiNameArray = funghiNameList.toArray(new String[0]);


        Log.i("funghi image", "list: " + funghiImageList);
        Log.i("funghi image", "array: " + funghiImageArray);
        Log.i("funghiGrid", "lista" + funghiList.toString());

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), funghiImageArray, funghiNameArray);

        funghiGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Funghi selectedFloraItem = funghiList.get(position);

                Log.i("selectedFaunaItem", "Item: " + selectedFloraItem);

                int floraId = selectedFloraItem.getId();
                int floraImageId = selectedFloraItem.getImageID();
                int eatability = selectedFloraItem.getFogyasztas();
                String floraName = selectedFloraItem.getName();
                String floraLatinName = selectedFloraItem.getLatinName();
                String floraDescription = selectedFloraItem.getDescription();


                Log.i("floraGridOnItemClick", "name: " + floraName);
                Log.i("floraGridOnItemClick", "latin: " + floraLatinName);
                Log.i("floraGridOnItemClick", "desc: " + floraDescription);

                Bundle fragmentArgs = new Bundle();
                fragmentArgs.putString("wildlife_type", "funghi");
                fragmentArgs.putInt("wildlife_id", floraId);
                fragmentArgs.putInt("wildlife_imageId", floraImageId);
                fragmentArgs.putString("wildlife_title", floraName);
                fragmentArgs.putString("wildlife_latin_title", floraLatinName);
                fragmentArgs.putString("wildlife_description", floraDescription);
                fragmentArgs.putInt("funghi_eatability", eatability);

                WildlifeItemFragment wildlifeItemFragment = new WildlifeItemFragment();
                wildlifeItemFragment.setArguments(fragmentArgs);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, wildlifeItemFragment, "funghiFragment");
                transaction.addToBackStack("funghiFragment");
                transaction.commit();
            }
        });

        funghiGridView.setAdapter(adapter);
        return funghiView;

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
