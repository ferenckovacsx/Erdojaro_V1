package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.Flora;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-26.
 */

public class WildlifeGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Flora> dataSet;
    int[] imageId;

    private static class ViewHolder {

        ImageView floraImageView;
    }

    public WildlifeGridAdapter(Context context, int[] floraImages) {
        mContext = context;
        this.imageId = floraImages;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageId.length;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        Object object = getItem(position);
        Flora floraItem = (Flora) object;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.custom_grid_item, parent, false);
            ImageView imageView = (ImageView) grid.findViewById(R.id.wildlife_grid_image);
            imageView.setImageResource(imageId[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
