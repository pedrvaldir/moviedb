package moviedbcom.valdir.themoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView =  findViewById(R.id.rv_movies);

        ArrayList<Movie> exampleMovie = new ArrayList<>();

        MovieAdapter mMovieAdapter;


        exampleMovie.add(new Movie("antotio", "teste@anritio.com", "adf","sadf","sadf"));
        exampleMovie.add(new Movie("2antotio", "2teste@anritio.com", "adf","sadf","sadf"));
        exampleMovie.add(new Movie("2antotio", "2teste@anritio.com", "adf","sadf","sadf"));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(exampleMovie);

        //  movieListView.setAdapter(mMovieAdapter);

       mRecyclerView.setAdapter(mMovieAdapter);
    }
}
