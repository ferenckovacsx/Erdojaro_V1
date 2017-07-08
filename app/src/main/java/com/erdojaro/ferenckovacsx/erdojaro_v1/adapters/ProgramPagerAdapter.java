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
import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.MainActivity.mainContext;

public class ProgramPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Program> dataObjectList;
    private LayoutInflater layoutInflater;

    public ProgramPagerAdapter(List<Program> dataObjectList, Context context) {
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
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = this.layoutInflater.inflate(R.layout.custom_program_item, container, false);
        ImageView displayImage = view.findViewById(R.id.program_image);
        TextView programTitle = view.findViewById(R.id.program_title);
        TextView programDescription = view.findViewById(R.id.program_description);
        TextView programSubTitle = view.findViewById(R.id.program_subtitle);


        if (this.dataObjectList.get(position).getImageId() != 0) {
            Picasso.with(mainContext).load(this.dataObjectList.get(position).getImageId()).into(displayImage);
        }
        //displayImage.setImageResource(this.dataObjectList.get(position).getImageId());
        programTitle.setText(this.dataObjectList.get(position).getTitle());
        programDescription.setText(this.dataObjectList.get(position).getDescription());
        programSubTitle.setText(this.dataObjectList.get(position).getSubTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}