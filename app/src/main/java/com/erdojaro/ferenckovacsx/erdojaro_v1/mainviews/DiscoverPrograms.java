package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.ProgramPagerAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.erdojaro.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;


public class DiscoverPrograms extends Fragment {

    private ViewPager viewPager;
    private static ProgramPagerAdapter adapter;
    ArrayList<Program> programList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View programView = inflater.inflate(R.layout.fragment_discover_programs, container, false);

        programList = new ArrayList<>();
        programList.add(new Program(1, getString(R.string.program_1_name), getString(R.string.program_1_date), R.drawable.program_1, getString(R.string.program_1_description)));
        programList.add(new Program(2, getString(R.string.program_2_name), getString(R.string.program_no_date), R.drawable.program_2, getString(R.string.program_2_description)));
        programList.add(new Program(3, getString(R.string.program_3_name), getString(R.string.program_3_date), R.drawable.program_3, getString(R.string.program_3_description)));
        programList.add(new Program(4, getString(R.string.program_4_name), getString(R.string.program_4_date), R.drawable.program_4, getString(R.string.program_4_description)));
        programList.add(new Program(5, getString(R.string.program_5_name), getString(R.string.program_5_date), R.drawable.program_5, getString(R.string.program_5_description)));
        programList.add(new Program(6, getString(R.string.program_6_name), getString(R.string.program_6_date), R.drawable.program_6, getString(R.string.program_6_description)));
        programList.add(new Program(6, getString(R.string.program_7_name), getString(R.string.program_7_date), R.drawable.program_7, getString(R.string.program_7_description)));

        Log.i("POIFragment", "lista" + programList.toString());

        viewPager = programView.findViewById(R.id.wiewPager);
        adapter = new ProgramPagerAdapter(programList, getActivity().getApplicationContext());

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(30);

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
