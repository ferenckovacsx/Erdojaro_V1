package com.erdojaro.ferenckovacsx.erdojaro_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.CircleTransform;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Trip;
import com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity;
import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class TripListAdapter extends ArrayAdapter<Trip> {

    private ArrayList<Trip> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView tripImage;
        ImageView tripCyclingIcon;
        ImageView tripHikingIcon;
        ImageView tripTanosvenyIcon;
        ImageView fullHeartIcon;
        ImageView emptyHeartIcon;
        TextView tripTitle;
        TextView tripDistance;
        TextView tripFavoriteCount;

    }

    public TripListAdapter(ArrayList<Trip> data, Context context) {
        super(context, R.layout.custom_trip_listitem, data);
        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Trip tripItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_trip_listitem, parent, false);
            viewHolder.tripImage = (ImageView) convertView.findViewById(R.id.trip_listitem_image);
            viewHolder.tripTitle = (TextView) convertView.findViewById(R.id.trip_listitem_title);
            viewHolder.tripDistance = (TextView) convertView.findViewById(R.id.trip_distance_textview);
            viewHolder.tripFavoriteCount = (TextView) convertView.findViewById(R.id.trip_favorite_text);
            viewHolder.tripCyclingIcon = (ImageView) convertView.findViewById(R.id.trip_cycling_icon);
            viewHolder.tripHikingIcon = (ImageView) convertView.findViewById(R.id.trip_hiking_icon);
            viewHolder.tripTanosvenyIcon = (ImageView) convertView.findViewById(R.id.trip_tanosveny_icon);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;


        if (tripItem.isHiking()) {
            viewHolder.tripHikingIcon.setImageResource(R.drawable.ic_trip_hiking);
        } else {
            viewHolder.tripHikingIcon.setImageResource(R.drawable.ic_trip_hiking_disabled);
        }

        if (tripItem.isCycling()) {
            viewHolder.tripCyclingIcon.setImageResource(R.drawable.ic_trip_cycling);
        } else {
            viewHolder.tripCyclingIcon.setImageResource(R.drawable.ic_trip_cycling_disabled);
        }

        if (tripItem.isTanosveny()) {
            viewHolder.tripTanosvenyIcon.setImageResource(R.drawable.ic_trip_tanosveny);
        } else {
            viewHolder.tripTanosvenyIcon.setImageResource(R.drawable.ic_trip_tanosveny_disabled);
        }


        if (tripItem.getImageId() != 0) {
            Picasso.with(MainActivity.mainContext).cancelRequest(viewHolder.tripImage);
            Picasso.with(MainActivity.mainContext).load(tripItem.getImageId()).transform(new CircleTransform()).into(viewHolder.tripImage);
            //viewHolder.tripImage.setImageResource(tripItem.getImageId());
        } else {
            Picasso picasso = Picasso.with(MainActivity.mainContext);
            picasso.setIndicatorsEnabled(false);

            Picasso.with(MainActivity.mainContext)
                    .load(tripItem.getImageUrl())
                    .into(viewHolder.tripImage);
        }

        String distanceText = String.valueOf(tripItem.getDistance()) + "km";
        viewHolder.tripDistance.setText(distanceText);
        //viewHolder.tripFavoriteCount.setText(String.valueOf(tripItem.getFavoriteCount()));
        viewHolder.tripTitle.setText(tripItem.getName());


        // Return the completed view to render on screen
        return convertView;
    }

}
