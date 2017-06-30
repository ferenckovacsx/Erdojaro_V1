package com.example.ferenckovacsx.erdojaro_v1.adapters;

/**
 * Created by ferenckovacsx on 2017-06-30.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.R;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.FavoritedItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class FavoritesListAdapter extends ArrayAdapter<FavoritedItem> {

    private ArrayList<FavoritedItem> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView FavoritedItemImageView;
        TextView FavoritedItemTextView;
    }

    public FavoritesListAdapter(ArrayList<FavoritedItem> data, Context context) {
        super(context, R.layout.listitem_favorite_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        FavoritedItem FavoritedItemItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View resultView;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem_favorite_item, parent, false);
            viewHolder.FavoritedItemImageView = convertView.findViewById(R.id.favorite_imageview);
            viewHolder.FavoritedItemTextView = convertView.findViewById(R.id.favorite_textview);

            resultView = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            resultView = convertView;
        }

        lastPosition = position;

        if (viewHolder.FavoritedItemImageView != null) {
            if (FavoritedItemItem.getImageId() != 0) {
                Picasso.with(mainContext).load(FavoritedItemItem.getImageId()).into(viewHolder.FavoritedItemImageView);
                //viewHolder.FavoritedItemImageView.setImageResource(FavoritedItemItem.getImageInt());
            }
        }

        viewHolder.FavoritedItemTextView.setText(FavoritedItemItem.getName());
        viewHolder.FavoritedItemImageView.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
