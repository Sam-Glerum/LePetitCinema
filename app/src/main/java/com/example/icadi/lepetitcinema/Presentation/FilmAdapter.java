package com.example.icadi.lepetitcinema.Presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.ApplicationLogic.ImageManager;
import com.example.icadi.lepetitcinema.Domain.Film;
import com.example.icadi.lepetitcinema.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by icadi on 27-3-18.
 */

public class FilmAdapter extends BaseAdapter {

    // TAG used for logging
    private static final String TAG = FilmAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Film> films;

    public FilmAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Film> films) {
        super();
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.films = films;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Log.i(TAG, "getView " + position);

        // Create ViewHolder Object
        ViewHolder viewHolder;

        // Create a new view or use a recycled one
        if (convertView == null) {
            // If convertView is non-existent, create a new one
            convertView = layoutInflater.inflate(R.layout.films_listview_row, null);

            // Create ViewHolder object
            viewHolder = new ViewHolder();
            viewHolder.filmImage = (ImageView) convertView.findViewById(R.id.filmImage);
            viewHolder.filmTitle = (TextView) convertView.findViewById(R.id.filmTitle);
            // Link the view to the viewholder
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Fill the viewHolder with the right film
        Film film = films.get(position);
        // Set the title of the film
        viewHolder.filmTitle.setText(film.getName());
        Log.i(TAG, film.getName());

        Picasso.with(context).load(film.getPosterImageUrl()).into(viewHolder.filmImage);

        return convertView;
    }

    private static class ViewHolder {
        public ImageView filmImage;
        public TextView filmTitle;
    }

    private class ImageLoader extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageLoader(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bitmap = null;
            try {
                // Try to get an InputStream from the url
                InputStream in = new java.net.URL(imageURL.toLowerCase()).openStream();
                // Create a new BitMap from the supplied InputStream
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("ImageLoader", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
