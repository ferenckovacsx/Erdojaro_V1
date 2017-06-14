package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class FavoritesFragment extends Fragment {

    private ImageView demoImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View favoritesView = inflater.inflate(R.layout.fragment_favorites, container, false);

//        File imgFile = new File(getActivity().getFilesDir() + "/erdojaroIMG/Poi_3.jpg");
//        Log.i("FavoritesFragment", "Imagefile: " + imgFile);
//
//        if (imgFile.exists()) {
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//            demoImage.setImageBitmap(myBitmap);
//        }
        demoImage = (ImageView) favoritesView.findViewById(R.id.demo);

        Picasso.with(getActivity().getApplication()).load("http://i.imgur.com/DvpvklR.png").into(demoImage);

        return favoritesView;
    }
}