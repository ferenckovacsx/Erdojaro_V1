package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.example.ferenckovacsx.erdojaro_v1.adapters.ProgramPagerAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;

import static android.support.design.R.id.left;
import static android.support.design.R.id.right;


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
        ProgramList.add(new Program("\n" +
                "Why do we use it?\n" +
                "\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                "\n" +
                "Where does it come from?\n" +
                "\n" +
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", R.drawable.fauna));

        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + ProgramList.toString());

        viewPager = (ViewPager) programView.findViewById(R.id.wiewPager);
        adapter = new ProgramPagerAdapter(ProgramList, getActivity().getApplicationContext());

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(5);

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
