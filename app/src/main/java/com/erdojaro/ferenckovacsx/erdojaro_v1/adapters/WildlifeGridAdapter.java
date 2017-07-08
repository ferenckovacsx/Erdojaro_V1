package com.erdojaro.ferenckovacsx.erdojaro_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Flora;
import com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
            gridView = inflater.inflate(R.layout.custom_grid_item, parent, false);

            GridView grid = (GridView) parent;
            int size = grid.getRequestedColumnWidth();

        } else {

            gridView = convertView;
        }

        wildlifeImageView = gridView.findViewById(R.id.wildlife_grid_image);
        wildlifeTextView = gridView.findViewById(R.id.wildlife_grid_textview);


        if (wildlifeImageId[position] != 0) {
            Picasso.with(MainActivity.mainContext).load(wildlifeImageId[position]).into(wildlifeImageView);
            //viewHolder.poiImageView.setImageResource(poiItem.getImageInt());
        }
        // wildlifeImageView.setImageResource(wildlifeImageId[position]);
        wildlifeTextView.setText(wildlifeNames[position]);

        return gridView;
    }
}
