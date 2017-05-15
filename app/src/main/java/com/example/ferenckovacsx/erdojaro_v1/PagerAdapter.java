package com.example.ferenckovacsx.erdojaro_v1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ferenckovacsx on 2017-05-15.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // tab titles
    private String[] tabTitles = new String[]{"Holiday planner", "Notification settings"};

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

//        switch (position) {
//            case 0:
//                DayOffRequestFragment dayOffRequestFragment = new DayOffRequestFragment();
//                return dayOffRequestFragment;
//
//            case 1:
//                SettingsFragment settingsFragment = new SettingsFragment();
//                return settingsFragment;
//
//            default:
//                return null;
//        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


}
