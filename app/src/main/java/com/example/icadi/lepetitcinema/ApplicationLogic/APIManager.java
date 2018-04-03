package com.example.icadi.lepetitcinema.ApplicationLogic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.icadi.lepetitcinema.Domain.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Bas Cijsouw on 20/02/2018.
 */

//Class die paintings ophaalt met behulp van JSON en api

public class APIManager extends AsyncTask<String, Void, String> {

    private static final String TAG = "APIManager";
    private OnFilmAvailable listener;

    public APIManager(OnFilmAvailable listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responsCode = -1;
        // De URL die we via de .execute() meegeleverd krijgen
        String apiUrl = params[0];
        // Het resultaat dat we gaan retourneren
        String response = "";

        Log.i(TAG, "doInBackground aangeroepen");

        try {

            URL url = new URL(apiUrl);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }
            // Initialiseer een HTTP connectie
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");

            // Voer het request uit via de HTTP connectie op de URL
            httpConnection.connect();

            // Kijk of het gelukt is door de response code te checken
            responsCode = httpConnection.getResponseCode();
            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                // Log.i(TAG, "doInBackground response = " + response);
            } else {
                Log.e(TAG, "Error, invalid response");
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }

        // Hier eindigt deze methode.
        // Het resultaat gaat naar de onPostExecute methode.
        return response;
    }

    protected void onPostExecute(String response) {

        Log.i(TAG, "onPostExecute " + response);

        // Check of er een response is
        if (response == null || response == "") {
            Log.e(TAG, "onPostExecute kreeg een lege response!");
            return;
        }

        try {

            JSONObject currentPage = new JSONObject(response);
            JSONArray allInfo = currentPage.getJSONArray("results");
            for (int idx = 0; idx < allInfo.length(); idx++) {
                // array level objects and get user
                JSONObject oneFilm = allInfo.getJSONObject(idx);

                // Get info from plain JSONObject
                String title = oneFilm.getString("title");
                String description = oneFilm.getString("overview");
                String posterPath = oneFilm.getString("poster_path");
                String backPath = oneFilm.getString("backdrop_path");
                double rating = oneFilm.getDouble("vote_average");

                // Get image url
                String posterImageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
                String backgroundImageUrl = "https://image.tmdb.org/t/p/w500" + backPath;
                Log.i(TAG, "Got painting " + title);
                Log.i(TAG, "imageurl: " + posterImageUrl + backgroundImageUrl);

                // Create new painting object
                Film currentFilm = new Film(title, description, rating, posterImageUrl, backgroundImageUrl);

                //
                // call back with new painting data
                //
                listener.onFilmAvailable(currentFilm);

            }

        } catch (JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }


    //
    // convert InputStream to String
    //
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public interface OnFilmAvailable {
        void onFilmAvailable(Film film);
    }
}