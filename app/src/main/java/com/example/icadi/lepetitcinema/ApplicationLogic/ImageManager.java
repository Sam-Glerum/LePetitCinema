package com.example.icadi.lepetitcinema.ApplicationLogic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Bas Cijsouw on 21/02/2018.
 */

public class ImageManager extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private static final String TAG = "ImageManager";
    public ImageManager(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        Log.i(TAG, "Image wordt geladen");
        String imageURL = urls[0];
        System.out.println(imageURL);
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bitmap = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
