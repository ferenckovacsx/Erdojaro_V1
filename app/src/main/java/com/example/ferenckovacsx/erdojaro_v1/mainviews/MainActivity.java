package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ferenckovacsx.erdojaro_v1.BottomNavigationViewHelper;
import com.example.ferenckovacsx.erdojaro_v1.R;
import com.mapbox.mapboxsdk.Mapbox;

public class MainActivity
        extends AppCompatActivity
        implements
        DiscoverFragment.OnFragmentInteractionListener,
        DiscoverPOI.OnFragmentInteractionListener,
        DiscoverTrips.OnFragmentInteractionListener,
        DiscoverPrograms.OnFragmentInteractionListener,
        MoreOptionsFragment.OnFragmentInteractionListener,
        WildlifeFragment.OnFragmentInteractionListener,
        WildlifeFloraFragment.OnFragmentInteractionListener,
        WildlifeFaunaFragment.OnFragmentInteractionListener,
        WildlifeFunghiFragment.OnFragmentInteractionListener,
        WildlifeItemFragment.OnFragmentInteractionListener,
        POIFragment.OnFragmentInteractionListener,
        TripFragment.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener,
        DownloadMapFragment.OnFragmentInteractionListener {

    Fragment selectedFragment;
    public static Context mainContext;
    public static BottomNavigationView bottomNavigationView;
    public boolean replaceFragment;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = getApplicationContext();
        context = this;

        //get access token for MapBox
        Mapbox.getInstance(this, getString(R.string.access_token));

        ImageView cameraIcon = (ImageView) findViewById(R.id.toolbar_camera_icon);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CameraPreviewActivity.class);
                startActivity(i);
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        selectedFragment = null;
                        replaceFragment = false;
                        switch (item.getItemId()) {
//                            case R.id.navigation_home:
//
//                                if (!item.isChecked()) {
//                                    selectedFragment = new HomeFragment();
//                                    selectedFragment.setRetainInstance(true);
//                                    replaceFragment = true;
//                                }
//                                break;

                            case R.id.navigation_discover:

                                // if (!item.isChecked()) {
                                selectedFragment = new DiscoverFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                // }
                                break;

                            case R.id.navigation_map:

                                //if (!item.isChecked()) {
                                selectedFragment = new MapFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                // }
                                break;

                            case R.id.navigation_favorites:

                                //if (!item.isChecked()) {
                                selectedFragment = new FavoritesFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                // }
                                break;

                            case R.id.navigation_more_options:

                                //if (!item.isChecked()) {
                                selectedFragment = new MoreOptionsFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                // }
                                break;
                        }

                        //if (replaceFragment) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, selectedFragment);
                        transaction.commit();
                        //}
                        return true;
                    }

                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance());
        transaction.commit();

    }

    @Override
    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }

    @Override
    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment" + uri);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    //     @Override
//    public void onBackPressed() {
//        if (selectedFragment.equals(DiscoverPOI) && fragmentA.hasExpandedRow()) {
//            fragmentA.collapseRow();
//        } else if (selectedFragment.equals(fragmentA) && fragmentA.isShowingLoginView()) {
//            fragmentA.hideLoginView();
//        } else if (selectedFragment.equals(fragmentA)) {
//            popBackStack();
//        } else if (selectedFragment.equals(fragmentB) && fragmentB.hasCondition1()) {
//            fragmentB.reverseCondition1();
//        } else if (selectedFragment.equals(fragmentB) && fragmentB.hasCondition2()) {
//            fragmentB.reverseCondition2();
//        } else if (selectedFragment.equals(fragmentB)) {
//            getFragmentManager().popBackStack();
//        } else {
//            // handle by activity
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public void onBackPressed() {
//
//        // If the fragment exists and has some back-stack entry
//        if (selectedFragment != null && selectedFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
//            // Get the fragment fragment manager - and pop the backstack
//            selectedFragment.getChildFragmentManager().popBackStack();
//        }
//        // Else, nothing in the direct fragment back stack
//        else{
//            // Let super handle the back press
//            super.onBackPressed();
//        }
//    }
}
