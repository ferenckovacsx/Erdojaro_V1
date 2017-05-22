package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.POI;
import com.example.ferenckovacsx.erdojaro_v1.POIListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;


public class DiscoverPOI extends Fragment {

    ListView poiListView;
    private static POIListAdapter adapter;
    ArrayList<POI> POIList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View poiView = inflater.inflate(R.layout.fragment_discover_poi, container, false);

        poiListView = (ListView) poiView.findViewById(R.id.POI_listview);

        POIList = new ArrayList<>();
        POIList.add(new POI("Nagymező", R.drawable.poi_nagymezo, "123456", false, "Ez itt a nagymezo"));
        POIList.add(new POI("blabla", R.drawable.fauna, "123456", true, "Pelda kep"));
        POIList.add(new POI("blabla", R.drawable.trek, "123456", true, "Pelda kep"));


        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + POIList.toString());

        adapter = new POIListAdapter(POIList, getActivity().getApplicationContext());

        poiListView.setAdapter(adapter);
        return poiView;

    }


//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//
//        poiListView = (ListView) view.findViewById(R.id.POI_listview);
//
//        POIList = new ArrayList<>();
//        POIList.add(new POI("Nagymező", R.drawable.poi_nagymezo, "123456", false, "Ez itt a nagymezo" ));
//
//        adapter = new POIListAdapter(POIList, getContext());
//
//        poiListView.setAdapter(adapter);
//        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                DataModel dataModel= dataModels.get(position);
////
////                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
////                        .setAction("No action", null).show();
//            }
//        });
//    }

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
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }
}
