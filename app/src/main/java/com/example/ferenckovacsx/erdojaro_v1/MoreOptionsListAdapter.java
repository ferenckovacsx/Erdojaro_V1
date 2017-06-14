package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.OptionListItem;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-05-18.
 */

public class MoreOptionsListAdapter extends ArrayAdapter<OptionListItem> implements View.OnClickListener {

    private ArrayList<OptionListItem> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView optionText;
        ImageView optionIcon;
    }

    public MoreOptionsListAdapter(ArrayList<OptionListItem> data, Context context) {
        super(context, R.layout.custom_moreoptions_listitem, data);
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
        OptionListItem OptionItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_moreoptions_listitem, parent, false);
            viewHolder.optionText = (TextView) convertView.findViewById(R.id.option_textview);
            viewHolder.optionIcon = (ImageView) convertView.findViewById(R.id.option_icon);


            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.optionText.setText(OptionItem.getTitle());
        viewHolder.optionIcon.setImageResource(OptionItem.getIconID());

        // Return the completed view to render on screen
        return convertView;
    }

}
