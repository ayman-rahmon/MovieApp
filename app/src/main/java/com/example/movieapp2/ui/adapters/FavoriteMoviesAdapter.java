package com.example.movieapp2.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.R;
import com.example.movieapp2.repository.storage.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavViewHolder> {


    private Context mContext ;
    private List<Movie> movies ;
    private OnFavoriteMovieClicked  listener ;



    public FavoriteMoviesAdapter(Context context , OnFavoriteMovieClicked listener ) {
        this.mContext = context ;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public FavoriteMoviesAdapter.FavViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item_layout,viewGroup,false);

        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMoviesAdapter.FavViewHolder favViewHolder, int i) {

        Movie movie = movies.get(i);
        favViewHolder.title.setText(movie.getOriginalTitle());

        if(movie.getPosterPath()!= null)
            Picasso.get().load(Constants.movieImage + movie.getPosterPath()).into(favViewHolder.posterimg);
    }

    @Override
    public int getItemCount() {
        if(movies ==null) {
            return 0;
        }
        return movies.size() ;
    }


    public List<Movie> getFavMovies() {
        return this.movies ;
    }

    public void setFavs(List<Movie> favsMovies) {
        movies = favsMovies ;
        notifyDataSetChanged();
    }


    public interface OnFavoriteMovieClicked {
        void onFavoriteClicked(Movie movie) ;
    }


    class FavViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title ;
        private ImageView posterimg ;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.movie_title_id);
            this.posterimg = itemView.findViewById(R.id.movie_img_id );
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Movie movie = movies.get(getAdapterPosition());
            listener.onFavoriteClicked(movie);
        }
    }



}
