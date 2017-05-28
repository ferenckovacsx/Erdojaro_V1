package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.ferenckovacsx.erdojaro_v1.BottomNavigationViewHelper;
import com.example.ferenckovacsx.erdojaro_v1.R;

public class HomeActivity
        extends AppCompatActivity
        implements
        DiscoverFragment.OnFragmentInteractionListener,
        DiscoverPOI.OnFragmentInteractionListener,
        DiscoverTrips.OnFragmentInteractionListener,
        DiscoverPrograms.OnFragmentInteractionListener,
        MoreOptionsFragment.OnFragmentInteractionListener,
        WildlifeFragment.OnFragmentInteractionListener,
        WildlifeFloraFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = new HomeFragment();
                                break;
                            case R.id.navigation_discover:
                                selectedFragment = new DiscoverFragment();
                                break;
                            case R.id.navigation_map:
                                selectedFragment = new MapFragment();
                                break;
                            case R.id.navigation_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;
                            case R.id.navigation_more_options:
                                selectedFragment = new MoreOptionsFragment();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, HomeFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }

    @Override
    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
