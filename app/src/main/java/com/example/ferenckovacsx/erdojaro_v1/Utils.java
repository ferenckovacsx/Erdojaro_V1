package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

/**
 * Created by ferenckovacsx on 2017-06-12.
 */

public class Utils {

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
