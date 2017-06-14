package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.HomeActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class POIListAdapter extends ArrayAdapter<POI> {

    private ArrayList<POI> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView POIimage;
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
        POI POIitem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_poi_listitem, parent, false);
            viewHolder.POIimage = (ImageView) convertView.findViewById(R.id.poi_listitem_image);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;


        if (POIitem.getImageInt() != null) {
            Log.i("POIListAdapter", "poi from res");

            viewHolder.POIimage.setImageResource(POIitem.getImageInt());
        } else {

            Picasso picasso = Picasso.with(mainContext);
            picasso.setIndicatorsEnabled(true);

            Picasso.with(mainContext)
                    .load(POIitem.getImageUrl())
                    .into(viewHolder.POIimage);


//            String bitmapFileName = "Poi_" + POIitem.getId() + ".jpg";
//            Log.i("POIListAdapter", "bitmapfile: " + bitmapFileName);
//            Bitmap poiBitmap = Utils.getBitmapFromFile(bitmapFileName, mContext);
//            viewHolder.POIimage.setImageBitmap(poiBitmap);
            //Picasso.with(mContext).load(POIitem.getImageUrl()).into(viewHolder.POIimage);
        }
        viewHolder.POIimage.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
