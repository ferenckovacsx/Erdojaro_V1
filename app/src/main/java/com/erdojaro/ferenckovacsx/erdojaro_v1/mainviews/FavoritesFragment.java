package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.adapters.FavoritesListAdapter;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.FavoritedItem;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.POI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class FavoritesFragment extends Fragment {

    ListView favoritesListView;
    ImageView removeFromFavorites;
    ArrayList<FavoritedItem> favoritesArrayList;
    SharedPreferences sharedPreferences;
    TextView noResultsTextView;

    SharedPreferences sharedpreferences;
    Set<String> favoritedItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View favoritesView = inflater.inflate(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.fragment_favorites, container, false);
        MainActivity.bottomNavigationView.getMenu().getItem(2).setChecked(true);

        FavoritesListAdapter listAdapter;

        favoritesListView = favoritesView.findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.favorites_listview);
        noResultsTextView = favoritesView.findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.favorites_no_results_textview);
        removeFromFavorites  = favoritesView.findViewById(R.id.favorite_delete);
        favoritesArrayList = new ArrayList<>();

        sharedPreferences = MainActivity.mainContext.getSharedPreferences("favoritePrefs", MODE_PRIVATE);
        favoritedItems = sharedPreferences.getStringSet("favoritedPoi", new HashSet<String>());
        Log.i("favoritedPoi", "id: " + favoritedItems);

        Log.i("FavoritesFragment", "poiList" + DiscoverPOI.poiList);


        for (int i = 0; i < DiscoverPOI.poiList.size(); i++) {
            for (String object : favoritedItems) {
                String element = (String) object;
                if (DiscoverPOI.poiList.get(i).getId() == Integer.valueOf(element)) {
                    Log.i("FavoritesFragment", "its a mach" + DiscoverPOI.poiList.get(i).toString());
                    FavoritedItem favoritedItem = new FavoritedItem(DiscoverPOI.poiList.get(i).getId(), "Poi", DiscoverPOI.poiList.get(i).getName(), DiscoverPOI.poiList.get(i).getImageInt());
                    favoritesArrayList.add(favoritedItem);
                }
            }
        }

        Log.i("FavoritesFragment", "listOfFavoritedItems" + favoritesArrayList);

//        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View v, int position,
//                                    long arg3) {
//
//                Object POIrawObject = adapter.getAdapter().getItem(position);
//                POI poiItem = (POI) POIrawObject;
//
//                int poiID = ((POI) POIrawObject).getId();
//                int poiImageID = poiItem.getImageInt();
//                String poiTitle = poiItem.getName();
//                String poiImageUrl = poiItem.getImageUrl();
//                double poiCoordLat = poiItem.getLatitude();
//                double poiCoordLong = poiItem.getLongitude();
//                String poiDescription = poiItem.getDescription();
//
//                Log.i("POIclickListener", "Title: " + poiTitle);
//                Log.i("POIclickListener", "gps latitude: " + poiCoordLat);
//                Log.i("POIclickListener", "gps longiuted: " + poiCoordLong);
//                Log.i("POIclickListener", "Description: " + poiDescription);
//
//                Bundle fragmentArgs = new Bundle();
//
////                try {
////                    int poiImageID = poiItem.getImageInt();
////                    fragmentArgs.putInt("poi_imageid", poiImageID);
////                } catch (NullPointerException np) {
////                    np.printStackTrace();
////                }
//
//                fragmentArgs.putString("poi_imageurl", poiImageUrl);
//                fragmentArgs.putInt("poi_imageid", poiImageID);
//                fragmentArgs.putInt("poi_id", poiID);
//                fragmentArgs.putString("poi_title", poiTitle);
//                fragmentArgs.putDouble("poi_lat", poiCoordLat);
//                fragmentArgs.putDouble("poi_long", poiCoordLong);
//                fragmentArgs.putString("poi_description", poiDescription);
//
//                POIFragment poiFragment = new POIFragment();
//                poiFragment.setArguments(fragmentArgs);
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.fragment_container, poiFragment, "poiFragment");
//                transaction.addToBackStack("poiFragment");
//                transaction.commit();
//            }
//        });


        listAdapter = new FavoritesListAdapter(favoritesArrayList, getActivity().getApplicationContext());
        if (favoritesArrayList.size() < 1) {
            noResultsTextView.setVisibility(View.VISIBLE);
            favoritesListView.setVisibility(View.GONE);
        } else {
            noResultsTextView.setVisibility(View.GONE);
            favoritesListView.setVisibility(View.VISIBLE);
            favoritesListView.setAdapter(listAdapter);
        }


        return favoritesView;
    }
}