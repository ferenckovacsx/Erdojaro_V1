package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ferenckovacsx.erdojaro_v1.GetContentAsync;
import com.example.ferenckovacsx.erdojaro_v1.R;

public class SplashActivity extends AppCompatActivity {

    TextView loadingTextView;
    public static Context splashContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashContext = getApplicationContext();

//        final String[] loadingTextArray = {
//                getString(R.string.loading_text_1),
//                getString(R.string.loading_text_2),
//                getString(R.string.loading_text_3),
//                getString(R.string.loading_text_4)};
//
//        loadingTextView = (TextView) findViewById(R.id.loading_variable_textview);
//        for (int i = 0; i < loadingTextArray.length; i++) {
//            loadingTextView.setText(loadingTextArray[i]);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ie) {
//                ie.printStackTrace();
//            }
//        }

        new GetContentAsync(getApplicationContext()).execute();

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }

//        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
//        this.startActivity(i);


    }
}
