package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.Program;
import com.example.ferenckovacsx.erdojaro_v1.ProgramPagerAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;


public class DiscoverPrograms extends Fragment {

    private ViewPager viewPager;
    private static ProgramPagerAdapter adapter;
    ArrayList<Program> ProgramList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View programView = inflater.inflate(R.layout.fragment_discover_programs, container, false);

        ProgramList = new ArrayList<>();
        ProgramList.add(new Program("Nagymező", R.drawable.poi_nagymezo));
        ProgramList.add(new Program("blabla", R.drawable.fauna));

        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + ProgramList.toString());

        viewPager = (ViewPager) programView.findViewById(R.id.wiewPager);
        adapter = new ProgramPagerAdapter(ProgramList, getActivity().getApplicationContext());
        viewPager.setAdapter(adapter);

        return programView;

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
        void onFragmentInteraction(Uri uri);
    }
}
