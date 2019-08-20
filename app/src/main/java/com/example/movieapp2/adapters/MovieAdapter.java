package com.example.movieapp2.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp2.Constants;
import com.example.movieapp2.R;
import com.example.movieapp2.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final String TAG = MovieAdapter.class.getSimpleName() ;


    private ArrayList<Movie> movies ;
    private Context mContext ;
    private OnMovieClickedListener listener ;


    public MovieAdapter(Context context  , OnMovieClickedListener onMovieClickedListener) {
        this.mContext = context ;
        this.listener = onMovieClickedListener ;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInglater = LayoutInflater.from(mContext);
        View view = mInglater.inflate(R.layout.movie_item_layout , viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // setting views ...
        // setting movie title ...
        viewHolder.movieTitle.setText(movies.get(i).getOriginalTitle());
        // loading movie poster ...
        Picasso.get().load(Constants.movieImage + movies.get(i).getPoster_path()).into(viewHolder.moviePoster);
    }


    @Override
    public int getItemCount() {
        if(movies == null) {
            return 0 ;
        } else {
            return movies.size();
        }
    }


    public void setData(ArrayList<Movie> movieList) {
        this.movies = movieList ;
        notifyDataSetChanged();
    }



    public interface OnMovieClickedListener {
        void onMovieClicked(Movie clickedItem) ;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView movieTitle ;
    ImageView moviePoster ;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        movieTitle =  itemView.findViewById(R.id.movie_title_id);
        moviePoster = itemView.findViewById(R.id.movie_img_id);
        itemView.setOnClickListener(this);
    }


        @Override
        public void onClick(View v) {
            Movie movie = movies.get(getAdapterPosition());
            Log.d(TAG , "movie clicked :: (pos) " + getAdapterPosition() );
            listener.onMovieClicked(movie);
        }
    }





}
