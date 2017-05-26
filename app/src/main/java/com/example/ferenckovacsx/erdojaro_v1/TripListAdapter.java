package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.Trip;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class TripListAdapter
        extends ArrayAdapter<Trip> implements View.OnClickListener {

    private ArrayList<Trip> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView TripImage;
        ImageView TripCyclingIcon;
        ImageView TripHikingIcon;
        ImageView TanosvenyIcon;
        ImageView FullHeartIcon;
        ImageView EmptyHeartIcon;
        TextView TripTitle;
        TextView TripDistance;
        TextView TripFavoriteCount;

    }

    public TripListAdapter(ArrayList<Trip> data, Context context) {
        super(context, R.layout.custom_trip_listitem, data);
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
        Trip TripItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_trip_listitem, parent, false);
            viewHolder.TripImage = (ImageView) convertView.findViewById(R.id.trip_listitem_image);
            viewHolder.TripTitle = (TextView) convertView.findViewById(R.id.trip_listitem_title);
            viewHolder.TripDistance = (TextView) convertView.findViewById(R.id.trip_distance);
            viewHolder.TripFavoriteCount = (TextView) convertView.findViewById(R.id.trip_saved_count);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.TripImage.setImageResource(TripItem.getImageID());
        viewHolder.TripTitle.setText(TripItem.getName());


        // Return the completed view to render on screen
        return convertView;
    }

}