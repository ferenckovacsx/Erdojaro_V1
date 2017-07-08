package com.erdojaro.ferenckovacsx.erdojaro_v1.adapters;

/**
 * Created by ferenckovacsx on 2017-06-30.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.FavoritedItem;
import com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class FavoritesListAdapter extends ArrayAdapter<FavoritedItem> {

    private ArrayList<FavoritedItem> favoritedItems;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        ImageView FavoritedItemImageView;
        TextView FavoritedItemTextView;
    }

    public FavoritesListAdapter(ArrayList<FavoritedItem> data, Context context) {
        super(context, R.layout.listitem_favorite_item, data);
        this.favoritedItems = data;
        this.mContext = context;
        notifyDataSetChanged();
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        final FavoritedItem favoritedItem = getItem(position);

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
            if (favoritedItem.getImageId() != 0) {
                Picasso.with(mainContext).load(favoritedItem.getImageId()).into(viewHolder.FavoritedItemImageView);
                //viewHolder.FavoritedItemImageView.setImageResource(favoritedItem.getImageInt());
            }
        }

        ImageView deleteImageView = convertView.findViewById(R.id.favorite_delete);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sharedPreferences;
                Set<String> favoritedItemsStringSet;
                sharedPreferences = MainActivity.mainContext.getSharedPreferences("favoritePrefs", MODE_PRIVATE);
                favoritedItemsStringSet = new HashSet<>(sharedPreferences.getStringSet("favoritedPoi", new HashSet<String>()));

//                favoritedItemsStringSet = sharedPreferences.getStringSet("favoritedPoi", new HashSet<String>());
                Log.i("favoritedPoi", "id: " + favoritedItemsStringSet);

                String typeOfSelectedItem;
//                int idOfSelectedItem = favoritedItem.ge

                SharedPreferences.Editor editor = sharedPreferences.edit();
                favoritedItemsStringSet.remove(String.valueOf(favoritedItem.getId()));
                //update sharedpref with new list
                editor.putStringSet("favoritedPoi", favoritedItemsStringSet);
                editor.apply();

                FavoritesListAdapter.this.remove(favoritedItem);
                FavoritesListAdapter.this.notifyDataSetChanged();

                Log.i("cancel", "click: " + favoritedItem.getName());
            }
        });
        viewHolder.FavoritedItemTextView.setText(favoritedItem.getName());
        viewHolder.FavoritedItemImageView.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }

    private void refreshListView() {
        // this.clear();
        this.notifyDataSetChanged();
        //this.addAll();
    }

}
