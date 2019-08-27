package com.example.movieapp2.ui.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp2.R;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.ui.adapters.MovieAdapter;
import com.example.movieapp2.ui.listeners.OnMovieItemClicked;
import com.example.movieapp2.ui.viewmodel.MovieDetailsViewModel;
import com.example.movieapp2.ui.viewmodel.MovieListViewModel;

public class MovieListFragment extends Fragment implements OnMovieItemClicked {


    protected MovieListViewModel viewmodel ;
    private MovieDetailsViewModel movieDetailsViewModel  ;

    protected RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment , container , false );
        recyclerView = view.findViewById(R.id.moviesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        viewmodel = ViewModelProviders.of(getActivity()).get(MovieListViewModel.class);
        observersRegisters() ;
        return view;
    }


    private void observersRegisters() {
        final MovieAdapter adapter = new MovieAdapter(this);

        viewmodel.getMovies().observe(this , adapter ::submitList);

        viewmodel.getNetworkState().observe(this,networkState ->{
            adapter.setNetworkState(networkState);
        });
        recyclerView.setAdapter(adapter);
        movieDetailsViewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);

    }




    @Override
    public void onMovieClicked(Movie movie) {
        movieDetailsViewModel.getMovie().postValue(movie);
        if(!movieDetailsViewModel.getMovie().hasActiveObservers()){
            MovieDetailsFragment detailsFragment = new MovieDetailsFragment() ;
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction() ;
            transaction.replace(R.id.fragmentsContainer , detailsFragment );
            transaction.addToBackStack(null);
            transaction.commit() ;


        }

    }
}
