package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ferenckovacsx.erdojaro_v1.R;

public class FavoritesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View favoritesView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return favoritesView;
    }
}