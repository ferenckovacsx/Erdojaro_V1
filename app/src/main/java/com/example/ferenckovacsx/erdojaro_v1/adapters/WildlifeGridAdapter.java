package com.example.ferenckovacsx.erdojaro_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Flora;

import java.util.ArrayList;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-26.
 */

public class WildlifeGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Flora> dataSet;
    int[] wildlifeImageId;
    String[] wildlifeNames;
    ImageView wildlifeImageView;
    TextView wildlifeTextView;

    private static class ViewHolder {


    }

    public WildlifeGridAdapter(Context context, int[] wildlifeImages, String[] wildlifeNames) {
        mContext = context;
        this.wildlifeImageId = wildlifeImages;
        this.wildlifeNames = wildlifeNames;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return wildlifeImageId.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.custom_grid_item, null);

            GridView grid = (GridView)parent;
            int size = grid.getRequestedColumnWidth();

        } else {

            gridView = convertView;
        }

        wildlifeImageView = gridView.findViewById(R.id.wildlife_grid_image);
        wildlifeTextView = gridView.findViewById(R.id.wildlife_grid_textview);
        wildlifeImageView.setImageResource(wildlifeImageId[position]);
        wildlifeTextView.setText(wildlifeNames[position]);

        return gridView;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
////        GridView gridP = (GridView)parent;
////        int size = gridP.getRequestedColumnWidth();
////
////        TextView text = new TextView(mainContext);
////        text.setLayoutParams(new GridView.LayoutParams(size, size));
//
//
//        View grid;
//        Object object = getItem(position);
//        Flora floraItem = (Flora) object;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//
//
//
//            grid = new View(mContext);
//            grid = inflater.inflate(R.layout.custom_grid_item, parent, false);
//
//        } else {
//            grid = (View) convertView;
//        }
//        wildlifeImageView = (ImageView) grid.findViewById(R.id.wildlife_grid_image);
//        wildlifeTextView = grid.findViewById(R.id.wildlife_grid_textview);
//        wildlifeImageView.setImageResource(wildlifeImageId[position]);
//        wildlifeImageView.setImageResource(wildlifeImageId[position]);
//        wildlifeTextView.setText(wildlifeNames[position]);
//        return grid;
//    }
}
