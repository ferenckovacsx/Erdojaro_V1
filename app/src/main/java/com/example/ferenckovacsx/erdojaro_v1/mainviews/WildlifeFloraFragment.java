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
        floraList.add(new Flora(5, "Csipkebogyó1", "Rosa Canis", R.drawable.csipkebogyo, getResources().getString(R.string.lorem_ipsum)));
        floraList.add(new Flora(6, "Csipkebogyó2", "Rosa Canis", R.drawable.csipkebogyo, "@string/lorem_ipsum"));
        floraList.add(new Flora(7, "Csipkebogyó3", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora(8, "Csipkebogyó4", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora(9, "Csipkebogyó5", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora(10, "Csipkebogyó6", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora(11, "Csipkebogyó7", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora(12, "Csipkebogyó8", "Rosa Canis", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));

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
