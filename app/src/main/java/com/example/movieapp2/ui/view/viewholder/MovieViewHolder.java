package com.example.movieapp2.ui.view.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.R;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;
import com.example.movieapp2.ui.listeners.OnMovieItemClicked;
import com.squareup.picasso.Picasso;


public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Movie movie ;
    private NetworkState networkState ;
    private OnMovieItemClicked listener ;
    private TextView title ;
    private ImageView posterimg ;




    public MovieViewHolder(@NonNull View itemView ,  OnMovieItemClicked listener) {
        super(itemView);
        this.listener = listener ;
        this.title = itemView.findViewById(R.id.movie_title_id);
        this.posterimg = itemView.findViewById(R.id.movie_img_id );
        itemView.setOnClickListener(this);
    }


    public void bindTo(Movie movie) {
        this.movie = movie  ;
        title.setText(movie.getOriginalTitle());

        if(movie.getPosterPath()!= null)
        Picasso.get().load(Constants.movieImage + movie.getPosterPath()).into(posterimg);

    }


    @Override
    public void onClick(View v) {
   if(listener != null)
    listener.onMovieClicked(movie);
    }


}
