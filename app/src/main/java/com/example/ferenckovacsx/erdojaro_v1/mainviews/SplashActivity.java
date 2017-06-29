package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.GetContentAsync;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    TextView loadingTextView;
    public static Context splashContext;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashContext = getApplicationContext();
        loadingTextView = (TextView) findViewById(R.id.loading_variable_textview);

        final String[] loadingTextArray = {
                getString(R.string.loading_text_1),
                getString(R.string.loading_text_2),
                getString(R.string.loading_text_3),
                getString(R.string.loading_text_4),
                getString(R.string.loading_text_5),
                getString(R.string.loading_text_6),
                getString(R.string.loading_text_7),
                getString(R.string.loading_text_8)};

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {

                if (count < 3) {
                    String randomStr = loadingTextArray[new Random().nextInt(loadingTextArray.length)];
                    loadingTextView.setText(randomStr);
                    h.postDelayed(this, 1000);
                } else {
                    new GetContentAsync(getApplicationContext()).execute();
                }
                count++;

            }

        }, 3000);

    }
}
