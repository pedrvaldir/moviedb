package moviedbcom.valdir.themoviedb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private ArrayList<Movie> mListMovies;

    public MovieAdapter(ArrayList<Movie> listMovies) {
        mListMovies = listMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_item_list;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mListMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mListMovies.size();
    }


    class MovieViewHolder extends  RecyclerView.ViewHolder{

        TextView titleMovie;
        TextView overViewMovie;
        TextView voteMovie;
        TextView dateReleaseMovie;

        public MovieViewHolder(View itemView) {

            super(itemView);

            titleMovie = (TextView) itemView.findViewById(R.id.tv_item_title);
            overViewMovie = (TextView) itemView.findViewById(R.id.tv_item_overview);
            voteMovie = (TextView) itemView.findViewById(R.id.tv_item_vote);
            dateReleaseMovie = (TextView) itemView.findViewById(R.id.tv_item_release_date);
        }

        void bind(Movie movie) {

            titleMovie.setText(movie.getTitle());
            overViewMovie.setText(movie.getOverview());
            voteMovie.setText(movie.getVote_overage());
            dateReleaseMovie.setText(movie.getDate_release());
        }
    }
}
