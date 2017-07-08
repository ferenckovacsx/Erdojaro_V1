package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import com.erdojaro.ferenckovacsx.erdojaro_v1.R;

/**
 * Created by ferenckovacsx on 2017-07-07.
 */

public class PreferencesActivity extends AppCompatPreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        checkValues();
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    private void checkValues()
    {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        String strUserName = sharedPrefs.getString("username", "NA");
//        boolean bAppUpdates = sharedPrefs.getBoolean("applicationUpdates",false);
//        String downloadType = sharedPrefs.getString("downloadType","1");
//
//        String msg = "Cur Values: ";
//        msg += "\n userName = " + strUserName;
//        msg += "\n bAppUpdates = " + bAppUpdates;
//        msg += "\n downloadType = " + downloadType;
//
//        Toaster.shortDebug(msg);
    }
}