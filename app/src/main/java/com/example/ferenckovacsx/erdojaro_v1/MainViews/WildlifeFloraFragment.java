package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.Flora;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.WildlifeGridAdapter;

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

        View floraView = inflater.inflate(R.layout.fragment_wildlife_flora, container, false);

        floraGridView = (GridView) floraView.findViewById(R.id.flora_gridview);

        floraList = new ArrayList<>();
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));
        floraList.add(new Flora("Csipkebogyó", R.drawable.csipkebogyo, "Ez itt a csipkebogyó"));

        floraImageList = new ArrayList<>();
        for (int i = 0; i < floraList.size(); i++){
            floraImageList.add(floraList.get(i).getImageID());
        }

        //floraImageArray = floraImageList.toArray(floraImageArray);
        floraImageArray = convertIntegers(floraImageList);

        Log.i("flora image", "list: " + floraImageList);
        Log.i("flora image", "array: " + floraImageArray);


        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + floraList.toString());

        adapter = new WildlifeGridAdapter(getActivity().getApplicationContext(), floraImageArray);

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
