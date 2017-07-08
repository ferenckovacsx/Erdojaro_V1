package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;

public class WildlifeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TabLayout wildlifeTabs;
    private FrameLayout wildlifeChildrenFragmentContainer;
    static String tagFromBackStack;


    public static WildlifeFragment newInstance(String TAG) {

        WildlifeFragment fragment = new WildlifeFragment();
        tagFromBackStack = TAG;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_wildlife_tablayout, container, false);

        Log.i("WildlifeFragment", "tagFromBackStack" + tagFromBackStack);

        bottomNavigationView.getMenu().getItem(3).setChecked(true);

        //generate TabLayout
        wildlifeTabs = (TabLayout) fragmentView.findViewById(R.id.wildlifeTabLayout);
        wildlifeTabs.addTab(wildlifeTabs.newTab().setText("Növények"));
        wildlifeTabs.addTab(wildlifeTabs.newTab().setText("Állatok"));
        wildlifeTabs.addTab(wildlifeTabs.newTab().setText("Gombák"));

        //set default fragment//set default fragment
        if (tagFromBackStack != null) {
            if (tagFromBackStack.equals("flora")) {
                replaceFragment(new WildlifeFloraFragment(), "floraFragment");
                wildlifeTabs.getTabAt(0).select();
            } else if (tagFromBackStack.equals("fauna")) {
                replaceFragment(new WildlifeFaunaFragment(), "faunaFragment");
                wildlifeTabs.getTabAt(1).select();

            } else if (tagFromBackStack.equals("funghi")) {
                replaceFragment(new WildlifeFunghiFragment(), "funghiFragment");
                wildlifeTabs.getTabAt(2).select();

            }
        } else {
            replaceFragment(new WildlifeFloraFragment(), "floraFragment");
        }

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //handling tab click event
        wildlifeTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new WildlifeFloraFragment(), "floraGrid");
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new WildlifeFaunaFragment(), "faunaGrid");
                } else if (tab.getPosition() == 2) {
                    replaceFragment(new WildlifeFunghiFragment(), "funghiGrid");
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