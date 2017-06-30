package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.adapters.FavoritesListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.adapters.POIListAdapter;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.FavoritedItem;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.DiscoverPOI.poiList;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.bottomNavigationView;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

public class FavoritesFragment extends Fragment {

    ListView favoritesListView;
    ArrayList<FavoritedItem> favoritesArrayList;
    SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View favoritesView = inflater.inflate(R.layout.fragment_favorites, container, false);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        FavoritesListAdapter listAdapter;

        favoritesListView = favoritesView.findViewById(R.id.favorites_listview);
        favoritesArrayList = new ArrayList<>();

        sharedPreferences = mainContext.getSharedPreferences("favoritePrefs", MODE_PRIVATE);
        Set<String> favoritedItems = sharedPreferences.getStringSet("favoritedPoi", new HashSet<String>());
        Log.i("favoritedPoi", "id: " + favoritedItems);

        Log.i("FavoritesFragment", "poiList" + poiList);


        for (int i = 0; i < poiList.size(); i++) {
            for (String object : favoritedItems) {
                String element = (String) object;
                if (poiList.get(i).getId() == Integer.valueOf(element)) {
                    Log.i("FavoritesFragment", "its a mach" + poiList.get(i).toString());
                    FavoritedItem favoritedItem = new FavoritedItem("Poi", poiList.get(i).getName(), poiList.get(i).getImageInt());
                    favoritesArrayList.add(favoritedItem);
                }
            }
        }

        Log.i("FavoritesFragment", "listOfFavoritedItems" + favoritesArrayList);


        listAdapter = new FavoritesListAdapter(favoritesArrayList, getActivity().getApplicationContext());
        favoritesListView.setAdapter(listAdapter);
        return favoritesView;
    }
}