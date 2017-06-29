package com.example.ferenckovacsx.erdojaro_v1.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class POIListAdapter extends ArrayAdapter<POI> {

    private ArrayList<POI> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView poiImageView;
        TextView poiTextView;
    }

    public POIListAdapter(ArrayList<POI> data, Context context) {
        super(context, R.layout.custom_poi_listitem, data);
        this.dataSet = data;
        this.mContext = context;


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        POI poiItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View resultView;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_poi_listitem, parent, false);
            viewHolder.poiImageView = (ImageView) convertView.findViewById(R.id.poi_listitem_image);
            viewHolder.poiTextView = (TextView) convertView.findViewById(R.id.poi_listitem_textview);

            resultView = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            resultView = convertView;
        }

        lastPosition = position;

        if (viewHolder.poiImageView != null) {
            if (poiItem.getImageInt() != 0) {
                Picasso.with(mainContext).load(poiItem.getImageInt()).into(viewHolder.poiImageView);
                //viewHolder.poiImageView.setImageResource(poiItem.getImageInt());
            } else {

                Picasso picasso = Picasso.with(mainContext);
                //picasso.setIndicatorsEnabled(true);

                Picasso.with(mainContext)
                        .load(poiItem.getImageUrl())
                        .into(viewHolder.poiImageView);


//            String bitmapFileName = "Poi_" + POIitem.getId() + ".jpg";
//            Log.i("POIListAdapter", "bitmapfile: " + bitmapFileName);
//            Bitmap poiBitmap = Utils.getBitmapFromFile(bitmapFileName, mContext);
//            viewHolder.poiImageView.setImageBitmap(poiBitmap);
                //Picasso.with(mContext).load(POIitem.getImageUrl()).into(viewHolder.poiImageView);
            }
        }
        Typeface m_typeFace = Typeface.createFromAsset(mainContext.getAssets(), "fonts/kapra.otf");
        viewHolder.poiTextView.setTypeface(m_typeFace);
        viewHolder.poiTextView.setText(poiItem.getName());



        viewHolder.poiImageView.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
