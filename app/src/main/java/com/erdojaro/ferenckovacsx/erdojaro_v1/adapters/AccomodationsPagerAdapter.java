package com.erdojaro.ferenckovacsx.erdojaro_v1.adapters;

/**
 * Created by ferenckovacsx on 2017-05-24.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Accomodation;
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

public class AccomodationsPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Accomodation> dataObjectList;
    private LayoutInflater layoutInflater;

    public AccomodationsPagerAdapter(List<Accomodation> dataObjectList, Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = this.layoutInflater.inflate(R.layout.custom_accomodation_item, container, false);
        ImageView displayImage = view.findViewById(R.id.accomodation_imageview);
        TextView accomodationTitleTextview = view.findViewById(R.id.accomodation_title_texview);
        TextView accomodationDescriptionTextview = view.findViewById(R.id.accomodation_description_textview);


        if (this.dataObjectList.get(position).getImageId() != 0) {
            Picasso.with(mainContext).load(this.dataObjectList.get(position).getImageId()).into(displayImage);
        }
        //displayImage.setImageResource(this.dataObjectList.get(position).getImageId());
        accomodationTitleTextview.setText(this.dataObjectList.get(position).getName());
        accomodationDescriptionTextview.setText(this.dataObjectList.get(position).getDescription());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}