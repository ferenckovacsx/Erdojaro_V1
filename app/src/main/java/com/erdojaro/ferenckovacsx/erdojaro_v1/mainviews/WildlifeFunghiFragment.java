package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Funghi;

import java.util.ArrayList;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;


public class WildlifeFunghiFragment extends Fragment {

    GridView funghiGridView;

    private com.github.clans.fab.FloatingActionButton toxicFAB;
    private com.github.clans.fab.FloatingActionButton nonEdibleFAB;
    private com.github.clans.fab.FloatingActionButton edibleFAB;
    private com.github.clans.fab.FloatingActionMenu floatingActionMenu;

    private static WildlifeGridAdapter adapter;

    ArrayList<Funghi> funghiList;
    ArrayList<Integer> funghiImageList;
    ArrayList<String> funghiNameList;
    int[] funghiImageArray;
    static int lastPosition;
    String[] funghiNameArray;

    SharedPreferences sharedPreferences;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View funghiView = inflater.inflate(R.layout.fragment_wildlife_funghi_grid, container, false);

        funghiGridView = funghiView.findViewById(R.id.funghi_gridview);
        floatingActionMenu = funghiView.findViewById(R.id.floating_action_menu);
        toxicFAB = funghiView.findViewById(R.id.floating_menu_toxic);
        nonEdibleFAB = funghiView.findViewById(R.id.floating_menu_non_edible);
        edibleFAB = funghiView.findViewById(R.id.floating_menu_edible);


        toxicFAB.setImageResource(R.drawable.ic_funghi_filter_toxic_on);
        nonEdibleFAB.setImageResource(R.drawable.ic_funghi_filter_non_edible_on);
        edibleFAB.setImageResource(R.drawable.ic_funghi_filter_edible_on);

        floatingActionMenu.setClosedOnTouchOutside(true);


//        sharedPreferences = mainContext.getSharedPreferences("floraGridPosition", MODE_PRIVATE);
//        lastPosition = sharedPreferences.getInt("lastPos", 0);

        funghiGridView.setSelection(lastPosition);
        Log.i("funghiGrid", "lastPosFromSP " + lastPosition);


        int drawableId;
        int descriptionResId;
        int nameResId;
        int latinNameResId;
        int eatabilityResId;
        String eatibility;
        int eatabilityIndex = 2;

        funghiList = new ArrayList<>();

        for (int i = 1; i < 101; i++) {
            drawableId = mainContext.getResources().getIdentifier("funghi_" + i, "drawable", mainContext.getPackageName());
            descriptionResId = mainContext.getResources().getIdentifier("funghi_" + i + "_description", "string", mainContext.getPackageName());
            nameResId = mainContext.getResources().getIdentifier("funghi_" + i + "_name", "string", mainContext.getPackageName());
            latinNameResId = mainContext.getResources().getIdentifier("funghi_" + i + "_latin_name", "string", mainContext.getPackageName());
            eatabilityResId = mainContext.getResources().getIdentifier("funghi_" + i + "_eatability", "string", mainContext.getPackageName());
            eatibility = getResources().getString(eatabilityResId);
            if (eatibility.equalsIgnoreCase("mérgező gomba")) {
                eatabilityIndex = 3;
            } else if (eatibility.equalsIgnoreCase("nem étkezési gomba")) {
                eatabilityIndex = 2;
            } else if (eatibility.equalsIgnoreCase("ehető gomba")) {
                eatabilityIndex = 1;
            }
            Funghi funghi = new Funghi(i, getResources().getString(nameResId), getResources().getString(latinNameResId), eatabilityIndex, drawableId, getResources().getString(descriptionResId));
            funghiList.add(funghi);
        }

        Log.i("FunghiFragment", "funghiList size: " + funghiList.size());


        funghiImageList = new ArrayList<>();
        for (int i = 0; i < funghiList.size(); i++) {
            funghiImageList.add(funghiList.get(i).getImageID());
        }

        funghiNameList = new ArrayList<>();
        for (int i = 0; i < funghiList.size(); i++) {
            funghiNameList.add(funghiList.get(i).getName());
        }

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

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("lastPos", funghiGridView.getFirstVisiblePosition());
//                editor.apply();

                lastPosition = funghiGridView.getFirstVisiblePosition();
                Log.i("funghiGrid", "lastPosOnClick " + lastPosition);

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
