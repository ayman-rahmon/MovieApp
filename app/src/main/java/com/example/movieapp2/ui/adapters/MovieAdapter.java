package com.example.movieapp2.ui.adapters;


import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp2.R;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;
import com.example.movieapp2.ui.view.viewholder.MovieViewHolder;
import com.example.movieapp2.ui.view.viewholder.NetworkStateItemViewHolder;
import com.example.movieapp2.ui.listeners.OnMovieItemClicked ;

public class MovieAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder> {

    private final String TAG = MovieAdapter.class.getSimpleName() ;



    private NetworkState networkState ;
    private OnMovieItemClicked listener ;

    public MovieAdapter(OnMovieItemClicked listener) {
        super(Movie.DIFF_CALLBACK);
        this.listener = listener ;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    LayoutInflater  inflater = LayoutInflater.from(viewGroup.getContext());
    if( viewType  == R.layout.movie_item_layout){
        View view = inflater.inflate(R.layout.movie_item_layout,viewGroup,false );
        MovieViewHolder holder = new MovieViewHolder(view,listener);
        return holder;
    }else if (viewType == R.layout.network_state_item) {
        View view = inflater.inflate(R.layout.network_state_item,viewGroup,false);
        return new NetworkStateItemViewHolder(view);
    }else{
        throw new IllegalArgumentException("unknown view type..");
    }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch(getItemViewType(position)) {
            case R.layout.movie_item_layout :
                ((MovieViewHolder)viewHolder).bindTo(getItem(position));
               break ;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) viewHolder).bindView(networkState);
                break;
        }
    }

    private boolean hasExtraRow() {
        if(networkState != null &&  networkState != NetworkState.LOADED){
            return  true ;
        } else {
            return false ;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(hasExtraRow() && position == getItemCount() - 1 ) {
            return R.layout.network_state_item ;
        } else{
            return R.layout.movie_item_layout ;
        }
    }



    public void setNetworkState(NetworkState newnetworkState) {
        NetworkState previousState = this.networkState ;
        boolean previousExtraRow = hasExtraRow() ;
        this.networkState = newnetworkState ;
        boolean newExtraRow = hasExtraRow() ;
        if (previousExtraRow != newExtraRow) {
            if(previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newnetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }





}













