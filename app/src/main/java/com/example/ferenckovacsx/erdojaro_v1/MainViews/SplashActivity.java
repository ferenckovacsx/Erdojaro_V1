package com.example.ferenckovacsx.erdojaro_v1.MainViews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ferenckovacsx.erdojaro_v1.GetContentAsync;
//import com.example.ferenckovacsx.erdojaro_v1.GetPoiList;
import com.example.ferenckovacsx.erdojaro_v1.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new GetContentAsync(this).execute();

    }
}
