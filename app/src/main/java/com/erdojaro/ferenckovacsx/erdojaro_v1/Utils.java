package com.erdojaro.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews.SplashActivity.splashContext;

/**
 * Created by ferenckovacsx on 2017-06-12.
 */

public class Utils {


    static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) splashContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean hasActiveInternetConnection() {
        if (isNetworkConnected()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("Utils", "Error checking internet connection", e);
            }
        } else {
            Log.d("Utils", "No network available!");
        }
        return false;
    }

//    public static boolean isOnWifi() {
//        ConnectivityManager connManager = (ConnectivityManager) mainContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//    }

    public static Bitmap getBitmapFromFile(String sourceFile, Context context) {

        File imgFile = new File(context.getFilesDir() + "/erdojaroIMG/" + sourceFile);
        Log.i("POIListAdatper", "loadFilename: " + imgFile);


        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            return myBitmap;
        }
        return null;
    }
}
