package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.OptionListItem;
import com.example.ferenckovacsx.erdojaro_v1.adapters.MoreOptionsListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;

public class MoreOptionsFragment extends Fragment {

    ListView optionsListView;
    private static MoreOptionsListAdapter adapter;
    ArrayList<OptionListItem> OptionsList;
    private OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View optionsView = inflater.inflate(R.layout.fragment_more_options, container, false);

        optionsListView = (ListView) optionsView.findViewById(R.id.options_listview);

        OptionsList = new ArrayList<>();
        OptionsList.add(new OptionListItem("Élővilág", R.drawable.ic_wildlife));
        OptionsList.add(new OptionListItem("Elsősegélynyújtás", R.drawable.ic_firstaid));
        OptionsList.add(new OptionListItem("Szálláshelyek", R.drawable.ic_accommodations));
        OptionsList.add(new OptionListItem("Offline tartalom", R.drawable.ic_downloads));
        OptionsList.add(new OptionListItem("Beállítások", R.drawable.ic_settings));

        adapter = new MoreOptionsListAdapter(OptionsList, getActivity().getApplicationContext());

        optionsListView.setAdapter(adapter);

        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (position == 0) {

                    Fragment wildlifeFragment = new WildlifeFragment();

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, wildlifeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                if (position == 3) {

                    Fragment offlineMapDownloader = new DownloadMapFragment();

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, offlineMapDownloader);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


        return optionsView;
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
        void messageFromParentFragment(Uri uri);
    }
}