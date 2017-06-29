package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ferenckovacsx.erdojaro_v1.R;

public class DiscoverFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TabLayout discoverTabs;
    private FrameLayout discoverChildrenFragmentContainer;
    static String tagFromBackStack;

    public static DiscoverFragment newInstance(String TAG) {
        DiscoverFragment fragment = new DiscoverFragment();
        tagFromBackStack = TAG;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_discover, container, false);

        //generate TabLayout
        discoverTabs = (TabLayout) fragmentView.findViewById(R.id.discoverTabLayout);
        discoverTabs.addTab(discoverTabs.newTab().setText("Látnivalók"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Túrák"));
        discoverTabs.addTab(discoverTabs.newTab().setText("Programok"));

        //set default fragment
        if (tagFromBackStack != null) {
            if (tagFromBackStack.equals("trip")){
                replaceFragment(new DiscoverTrips(), "discoverTrip");
                discoverTabs.getTabAt(1).select();
            } else if (tagFromBackStack.equals("poi")) {
                replaceFragment(new DiscoverPOI(), "discoverPoi");
            }
        } else {
            replaceFragment(new DiscoverPOI(), "discoverPoi");
        }

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //handling tab click event
        discoverTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new DiscoverPOI(), "discoverPoi");
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new DiscoverTrips(), "discoverTrip");
                } else if (tab.getPosition() == 2) {
                    replaceFragment(new DiscoverPrograms(), "discoverProgram");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

    private void replaceFragment(Fragment fragment, String TAG) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.child_fragment_container, fragment, TAG);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }


}