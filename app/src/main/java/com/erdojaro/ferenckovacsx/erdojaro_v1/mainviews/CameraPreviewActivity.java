package com.erdojaro.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.erdojaro.ferenckovacsx.erdojaro_v1.CameraSurfaceView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraPreviewActivity extends AppCompatActivity {

    private CameraSurfaceView mImageSurfaceView;
    private Camera camera;

    private FrameLayout cameraPreviewLayout;
    private ImageView capturedImageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.activity_camera_preview);

        //hide the status and action bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        try{
            actionBar.hide();
        } catch (NullPointerException np) {
            np.printStackTrace();
        }



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cameraPreviewLayout = (FrameLayout) findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.camera_preview);
        capturedImageHolder = (ImageView) findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.captured_image);

        camera = checkDeviceCamera();
        mImageSurfaceView = new CameraSurfaceView(CameraPreviewActivity.this, camera);
        cameraPreviewLayout.addView(mImageSurfaceView);

        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        View view = inflater.inflate(com.erdojaro.ferenckovacsx.erdojaro_v1.R.layout.camera_overlay, null);
        ViewGroup.LayoutParams layoutParamsControl= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        this.addContentView(view, layoutParamsControl);

        Button captureButton = (Button) findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.button);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, pictureCallback);
            }
        });
    }

    private Camera checkDeviceCamera() {
        Camera mCamera = null;
        try {
            mCamera = Camera.open(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            ImageView overlayImageView = (ImageView) findViewById(com.erdojaro.ferenckovacsx.erdojaro_v1.R.id.camera_overlay_image);
            BitmapDrawable drawable = (BitmapDrawable) overlayImageView.getDrawable();
            Bitmap overlayBitmap = drawable.getBitmap();

            Bitmap cameraBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap cameraScaledBitmap = Bitmap.createScaledBitmap(cameraBitmap, 1280, 720, true);
            int wid = cameraScaledBitmap.getWidth();
            int hgt = cameraScaledBitmap.getHeight();
            Bitmap newImage = Bitmap.createBitmap(wid, hgt, Bitmap.Config.ARGB_8888);
            Bitmap overlayScaledBitmap = Bitmap.createScaledBitmap(overlayBitmap, wid, hgt, true);
            Canvas canvas = new Canvas(newImage);
            canvas.drawBitmap(cameraScaledBitmap , 0, 0, null);
            canvas.drawBitmap(overlayScaledBitmap , 0, 0, null);

            File storagePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            storagePath.mkdirs();
            String finalName = Long.toString(System.currentTimeMillis());
            File myImage = new File(storagePath, finalName + ".jpg");

            String photoPath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + finalName + ".jpg";

            try {
                FileOutputStream fos = new FileOutputStream(myImage);
                newImage.compress(Bitmap.CompressFormat.JPEG, 80, fos);
                fos.close();
            } catch (IOException e) {
                Toast.makeText(CameraPreviewActivity.this, "Pic not saved", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(CameraPreviewActivity.this, "Pic saved in: " + photoPath, Toast.LENGTH_SHORT).show();

            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(mImageSurfaceView.getWidth(), mImageSurfaceView.getHeight());
            camera.setParameters(parameters);

            camera.startPreview();
            newImage.recycle();
            newImage = null;
            cameraBitmap.recycle();
            cameraBitmap = null;

//            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            if (bitmap == null) {
//                Toast.makeText(CameraPreviewActivity.this, "Captured image is empty", Toast.LENGTH_LONG).show();
//                return;
//            }
//            capturedImageHolder.setImageBitmap(scaleDownBitmapImage(newImage, 300, 200));
        }
    };

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }


}
