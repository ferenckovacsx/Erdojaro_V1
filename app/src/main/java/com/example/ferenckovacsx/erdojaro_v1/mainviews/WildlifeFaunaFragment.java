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
    int[] faunaImageArray;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View faunaView = inflater.inflate(R.layout.fragment_wildlife_fauna_grid, container, false);

        faunaGridView = (GridView) faunaView.findViewById(R.id.fauna_gridview);

        faunaArrayList = new ArrayList<>();
        faunaArrayList.add(new Fauna(1, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(2, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(3, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(4, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(5, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(6, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(7, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        faunaArrayList.add(new Fauna(8, "Csipkebogyó", "latinnev", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));

        faunaImageList = new ArrayList<>();
        for (int i = 0; i < faunaArrayList.size(); i++) {
            faunaImageList.add(faunaArrayList.get(i).getImageID());
        }

        //faunaImageArray = faunaImageList.toArray(faunaImageArray);
        faunaImageArray = convertIntegers(faunaImageList);

        Log.i("fauna image", "list: " + faunaImageList);
        Log.i("fauna image", "array: " + faunaImageArray);
        Log.i("faunaFragment", "lista" + faunaArrayList.toString());

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), faunaImageArray);

        faunaGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fauna selectedFloraItem = faunaArrayList.get(position);

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
