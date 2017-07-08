package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.erdojaro.ferenckovacsx.erdojaro_v1.GetContentAsync;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    TextView loadingTextView;
    public static Context splashContext;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.activity_splash);

        splashContext = getApplicationContext();
        loadingTextView = (TextView) findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.loading_variable_textview);

        final String[] loadingTextArray = {
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_1),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_2),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_3),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_4),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_5),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_6),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_7),
                getString(com.erdojaro.ferenckovacsx.erdojaro_v1.R.string.loading_text_8)};

        new GetContentAsync(getApplicationContext()).execute();

//
//        final Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            public void run() {
//
//                if (count < 3) {
//                    String randomStr = loadingTextArray[new Random().nextInt(loadingTextArray.length)];
//                    loadingTextView.setText(randomStr);
//                    h.postDelayed(this, 1000);
//                } else {
//                    new GetContentAsync(getApplicationContext()).execute();
//                }
//                count++;
//
//            }
//
//        }, 3000);

    }
}
