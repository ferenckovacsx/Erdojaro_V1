package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.erdojaro.ferenckovacsx.erdojaro_v1.BottomNavigationViewHelper;
import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
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
        AccomodationsFragment.OnFragmentInteractionListener,
        DownloadMapFragment.OnFragmentInteractionListener,
        FirstAidListFragment.OnFragmentInteractionListener,
        FirstAidItemFragment.OnFragmentInteractionListener {

    Fragment selectedFragment;
    String selectedFragmentTag;
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

//        ImageView cameraIcon = (ImageView) findViewById(R.id.toolbar_camera_icon);
//        cameraIcon.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                //check for camera permission
//                if (ContextCompat.checkSelfPermission(mainContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},
//                            11);
//                }
//
//                //check for storage permission
//                if (ContextCompat.checkSelfPermission(mainContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            12);
//                }
//
//                Intent i = new Intent(MainActivity.this, CameraPreviewActivity.class);
//                startActivity(i);
//            }
//        });

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

//                                if (!item.isChecked()) {
                                selectedFragment = new DiscoverFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                selectedFragmentTag = "discoverFragment";
//                                }
                                break;

                            case R.id.navigation_map:

                                if (!item.isChecked()) {
                                    selectedFragment = new MapFragment();
                                    selectedFragment.setRetainInstance(true);
                                    replaceFragment = true;
                                    selectedFragmentTag = "mapFragment";

                                }
                                break;

                            case R.id.navigation_favorites:

                                selectedFragment = new FavoritesFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                break;

                            case R.id.navigation_more_options:

//                                if (!item.isChecked()) {
                                selectedFragment = new MoreOptionsFragment();
                                selectedFragment.setRetainInstance(true);
                                replaceFragment = true;
                                selectedFragmentTag = "moreOptionsFragment";

//                                }
                                break;
                        }

                        if (replaceFragment) {
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, selectedFragment, selectedFragmentTag);
                            transaction.commit();
                        }
                        return true;
                    }

                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("poi"), "discoverFragment");
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

    @Override
    public void onBackPressed() {

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentManager childFragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
            Fragment currentChildFragment = childFragmentManager.findFragmentById(R.id.child_fragment_container);

            Log.i("onBackPressed", "current fragment is:" + currentFragment.getTag());
            //Log.i("currentChildFragment", "is:" + currentChildFragment.getTag());

            //if (currentFragment.getTag() != null) {
            if (currentFragment.getTag() != null && currentFragment.getTag().equals("tripFragment")) {
                Log.i("newInstance", "tripFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("trip"), "discoverTrip");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("poiFragment")) {
                Log.i("newInstance", "poiFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("poi"), "discoverPoi");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("poiFragment")) {
                Log.i("newInstance", "poiFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, DiscoverFragment.newInstance("poi"), "discoverPoi");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("floraFragment")) {
                Log.i("newInstance", "floraFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, WildlifeFragment.newInstance("flora"), "wildlifeFlora");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("faunaFragment")) {
                Log.i("newInstance", "faunaFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, WildlifeFragment.newInstance("fauna"), "wildlifeFauna");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("funghiFragment")) {
                Log.i("newInstance", "funghiFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, WildlifeFragment.newInstance("funghi"), "funghiFragment");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("firstAidItemFragment")) {
                Log.i("newInstance", "firstAidItemFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new FirstAidListFragment(), "firstAidListFragment");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("firstAidListFragment")) {
                Log.i("newInstance", "firstAidListFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new MoreOptionsFragment(), "moreOptionsFragment");
                transaction.commit();
            } else if (currentFragment.getTag() != null && currentFragment.getTag().equals("accomodationsFragment")) {
                Log.i("newInstance", "accomodationsFragment");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new MoreOptionsFragment(), "moreOptionsFragment");
                transaction.commit();
            }
            //}
        } catch (NullPointerException np) {
            np.printStackTrace();
        }


//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
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
