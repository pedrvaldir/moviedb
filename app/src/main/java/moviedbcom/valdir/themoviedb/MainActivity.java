package moviedbcom.valdir.themoviedb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

import moviedbcom.valdir.themoviedb.utils.Constants;
import moviedbcom.valdir.themoviedb.utils.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeRecyclerView();

        movieAsyncTask task = new movieAsyncTask();

        task.execute(Constants.urlMdb+Constants.urlPopularity+Constants.keyMdb);

    }

    private void initializeRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        mRecyclerView = findViewById(R.id.rv_movies);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

    }

    private class movieAsyncTask extends AsyncTask<String, Void,  ArrayList<Movie>>{



        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {

            ArrayList<Movie> result= Query.queryMovies(strings[0]);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> moviesList) {

            final MovieAdapter mMovieAdapter = new MovieAdapter(moviesList);

            mRecyclerView.setAdapter(mMovieAdapter);

        }
    }
}