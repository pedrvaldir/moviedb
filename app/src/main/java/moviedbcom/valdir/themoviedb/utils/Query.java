package moviedbcom.valdir.themoviedb.utils;

import android.content.Context;
import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;

import moviedbcom.valdir.themoviedb.MainActivity;
import moviedbcom.valdir.themoviedb.Movie;

public class Query {

    public static final String  LOG_TAG = MainActivity.class.getName();

    private static Context mContext;

    public Query() {
    }

    public static ArrayList<Movie> queryMovies(String requestUrl){

        URL url = createUrl(requestUrl);

        String resultJson = null;

        try {
            resultJson = requestHttp(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro ao fechar o fluxo de entrada", e);
        }

        ArrayList<Movie> movies = extrArrayJson(resultJson);

        return movies;
    }

    private static URL createUrl(String StringUrl) {

        URL url = null;

        try {
            url = new URL(StringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Erro com a criação de URL ", e);
        }
        return url;
    }

    private static String requestHttp(URL url) throws IOException {
        String resultJson = "";

        if (url == null) {
            return resultJson;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milisegundos */);
            urlConnection.setConnectTimeout(15000 /* milisegundos */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                resultJson = readStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Código de resposta de erro: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema ao recuperar os resultados JSON", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return resultJson;
    }

    private static ArrayList<Movie> extrArrayJson(String resultJson) {

        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject objetoRespostaJson = null;

        try {
            objetoRespostaJson = new JSONObject(resultJson);

            JSONArray moviesArray = objetoRespostaJson.getJSONArray("results");

            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject currentMovie = moviesArray.getJSONObject(i);

                String title = currentMovie.getString("title");
                String vote =  currentMovie.getString("vote_average");
                String popularity = currentMovie.getString("popularity");
                String imgUrl = currentMovie.getString("poster_path");
                String overview = currentMovie.getString("overview");
                String date = currentMovie.getString("release_date");

                Movie movie = new Movie(title, imgUrl, overview, vote, date);
                movies.add(movie);
            }

            return new ArrayList<Movie>(movies);

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problema ao analisar os resultados dos JSON", e);
        }
        return null;

    }

    private static String readStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
