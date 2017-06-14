package com.example.ferenckovacsx.erdojaro_v1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.AsyncTaskResponseContent;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Fauna;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Flora;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Funghi;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.POI;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.example.ferenckovacsx.erdojaro_v1.javabeans.Trip;
import com.example.ferenckovacsx.erdojaro_v1.mainviews.HomeActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.example.ferenckovacsx.erdojaro_v1.mainviews.HomeActivity.mainContext;
import static com.example.ferenckovacsx.erdojaro_v1.mainviews.SplashActivity.splashContext;

/**
 * Created by ferenckovacsx on 2017-06-09.
 */

public class GetContentAsync extends AsyncTask<Void, Void, AsyncTaskResponseContent> {

    String responseFromServer;

    List<POI> poiList;
    List<Trip> tripList;
    List<Program> programList;
    List<Flora> floraList;
    List<Fauna> faunaList;
    List<Funghi> funghiList;

    private Context context;

    public GetContentAsync(Context context) {
        this.context = context;
    }

    public AsyncResponse delegate = null;

//    public GetContentAsync(AsyncResponse delegate){
//        this.delegate = delegate;
//    }

    @Override
    protected AsyncTaskResponseContent doInBackground(Void... args) {

        AsyncTaskResponseContent allContent;

        for (int l = 0; l <= 5000; l++) {
            Log.i("looping", ": " + l);
        }

        String poiResponse = sendHTTPRequest(Constants.GET_POI);
        String tripResponse = sendHTTPRequest(Constants.GET_TRIP);
//        String programResponse = sendHTTPRequest(Constants.GET_POI);
//        String floraResponse = sendHTTPRequest(Constants.GET_POI);
//        String faunaResponse = sendHTTPRequest(Constants.GET_POI);
//        String funghiResponse = sendHTTPRequest(Constants.GET_POI);


        try {
            poiList = JSONParser.parsePoiJson(poiResponse);
            tripList = JSONParser.parseTripJson(tripResponse);


            allContent = new AsyncTaskResponseContent(poiList, tripList, programList, floraList, faunaList, funghiList);

            return allContent;

        } catch (NullPointerException n) {
            n.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    protected void onPostExecute(AsyncTaskResponseContent contentFromJson) {
        Log.i("GetContentAsync", "data from onbackground: " + contentFromJson);
        //delegate.processFinish(contentFromJson);

        try {

            List<POI> poiListOnPost = contentFromJson.getPoiList();
            List<Trip> tripListOnPost = contentFromJson.getTripList();
            //List<POI> poiListWithBitmap = new ArrayList<>();

//
//            //save image url to local bitmap
//            for (int i = 0; i < poiListOnPost.size(); i++) {
//
//                String imageURL = poiListOnPost.get(i).getImageUrl();
//                int poiId = poiListOnPost.get(i).getId();
//                saveImageUrlToFile("Poi", imageURL, poiId);
//            }
//
//            for (int i = 0; i < tripListOnPost.size(); i++) {
//
//                String imageURL = tripListOnPost.get(i).getImageUrl();
//                int tripId = tripListOnPost.get(i).getId();
//                saveImageUrlToFile("Trip", imageURL, tripId);
//            }

            //save to .dat file
            savePoiToFile(poiListOnPost);
            saveTripToFile(tripListOnPost);

        } catch (NullPointerException np) {
            np.printStackTrace();
        }

        Intent i = new Intent(context, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


    private String sendHTTPRequest(String apiURL) {

        try {
            //send GET request to API to fetch content
            HttpURLConnection urlConnection;
            URL url = new URL(apiURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            int responseCode = urlConnection.getResponseCode();
            Log.i("GetContentAsync", "api url: " + apiURL);
            Log.i("GetContentAsync", "response code from server: " + responseCode);

            //read response JSON from server
            String line;
            responseFromServer = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((line = br.readLine()) != null) {
                responseFromServer += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return responseFromServer;
    }

//    private void saveImageUrlToFile(String type, String imageURL, int id) {
//
//        final String stringId = String.valueOf(id);
//        final String TYPE = type;
//
//        Picasso.with(splashContext)
//                .load(imageURL)
//                .into(new Target() {
//                          @Override
//                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                              Log.i("GetContentAsync", "onBitmapLoaded" + bitmap);
//
//                              try {
//                                  String root = Environment.getExternalStorageDirectory().toString();
//                                  File myDir = new File(context.getFilesDir(), "/erdojaroIMG");
//
//                                  if (!myDir.exists()) {
//                                      myDir.mkdirs();
//                                  }
//
//                                  String name = TYPE + "_" + stringId + ".jpg";
//                                  myDir = new File(myDir, name);
//                                  Log.i("GetContentAsync", "saveFilename: " + myDir);
//                                  FileOutputStream out = new FileOutputStream(myDir);
//                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//
//                                  out.flush();
//                                  out.close();
//                              } catch (Exception e) {
//                                  Log.e("GetContenAsync", "save unsuccessful");
//                              }
//                          }
//
//                          @Override
//                          public void onBitmapFailed(Drawable errorDrawable) {
//                              Log.d("GetContentAsync", "onBitmapFailed");
//                          }
//
//                          @Override
//                          public void onPrepareLoad(Drawable placeHolderDrawable) {
//                          }
//                      }
//                );
//    }

//    private Bitmap getBitmapFromFile(String sourceFile) {
//
//        File imgFile = new File(context.getFilesDir() + "/erdojaroIMG/" + sourceFile);
//        Log.i("GetContentAsync", "loadFilename: " + imgFile);
//
//
//        if (imgFile.exists()) {
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            return myBitmap;
//        }
//        return null;
//    }


    private void savePoiToFile(List<POI> poiList) {

        try {
            File myDir = new File(context.getFilesDir(), "/poi.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myDir));
            oos.writeObject(poiList);
            Log.i("GetContentAsync", "Poi.dat created");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveTripToFile(List<Trip> tripList) {
        try {
            File myDir = new File(context.getFilesDir(), "/trip.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myDir));
            oos.writeObject(tripList);
            Log.i("GetContentAsync", "Trip.dat created");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
