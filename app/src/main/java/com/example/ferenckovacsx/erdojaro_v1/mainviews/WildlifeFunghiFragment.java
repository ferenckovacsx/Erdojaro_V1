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
    int[] funghiImageArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View funghiView = inflater.inflate(R.layout.fragment_wildlife_funghi_grid, container, false);

        funghiGridView = (GridView) funghiView.findViewById(R.id.funghi_gridview);

        funghiList = new ArrayList<>();
        funghiList.add(new Funghi(7, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(8, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(9, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(10, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(11, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(12, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(13, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        funghiList.add(new Funghi(14, "Csipkebogyó", "Latin nev",  R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));

        funghiImageList = new ArrayList<>();
        for (int i = 0; i < funghiList.size(); i++){
            funghiImageList.add(funghiList.get(i).getImageID());
        }

        //faunaImageArray = faunaImageList.toArray(faunaImageArray);
        funghiImageArray = convertIntegers(funghiImageList);

        Log.i("funghi image", "list: " + funghiImageList);
        Log.i("funghi image", "array: " + funghiImageArray);
        Log.i("funghiGrid", "lista" + funghiList.toString());

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), funghiImageArray);

        funghiGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Funghi selectedFloraItem = funghiList.get(position);

                Log.i("selectedFaunaItem", "Item: " + selectedFloraItem);

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
