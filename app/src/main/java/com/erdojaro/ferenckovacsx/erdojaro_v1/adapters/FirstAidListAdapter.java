package com.erdojaro.ferenckovacsx.erdojaro_v1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.FirstAid;

import java.util.ArrayList;

/**
 * Created by ferenckovacsx on 2017-07-08.
 */

public class FirstAidListAdapter extends ArrayAdapter<FirstAid>  {

    private ArrayList<FirstAid> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView firstAidTextView;
    }

    public FirstAidListAdapter(ArrayList<FirstAid> data, Context context) {
        super(context, R.layout.listitem_more_options, data);
        this.dataSet = data;
        this.mContext = context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        FirstAid firstAidItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem_firstaid, parent, false);
            viewHolder.firstAidTextView = convertView.findViewById(R.id.firstaid_listitem_title_textview);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.firstAidTextView.setText(firstAidItem.getName());

        // Return the completed view to render on screen
        return convertView;
    }

}
