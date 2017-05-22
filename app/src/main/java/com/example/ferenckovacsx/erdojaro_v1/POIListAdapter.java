package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.POI;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class POIListAdapter extends ArrayAdapter<POI> implements View.OnClickListener {

    private ArrayList<POI> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView POIname;
        ImageView POIimage;
    }

    public POIListAdapter(ArrayList<POI> data, Context context) {
        super(context, R.layout.custom_poi_listitem, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

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

            Log.i("POIadapter", "megtortent");

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_poi_listitem, parent, false);
            //viewHolder.POIname = (TextView) convertView.findViewById(R.id.poi_listitem_title);
            viewHolder.POIimage = (ImageView) convertView.findViewById(R.id.poi_listitem_image);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        //viewHolder.POIname.setText(POIitem.getName());
        viewHolder.POIimage.setImageResource(POIitem.getImageID());
        viewHolder.POIimage.setOnClickListener(this);
        viewHolder.POIimage.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
