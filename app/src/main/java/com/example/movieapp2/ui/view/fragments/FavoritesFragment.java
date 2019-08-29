package com.example.movieapp2.ui.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp2.R;
import com.example.movieapp2.repository.storage.AppDatabase;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.ui.adapters.FavoriteMoviesAdapter;
import com.example.movieapp2.ui.viewmodel.MovieDetailsViewModel;
import com.example.movieapp2.ui.viewmodel.MovieListViewModel;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoriteMoviesAdapter.OnFavoriteMovieClicked {

    private static final String TAG = FavoritesFragment.class.getSimpleName() ;

    private RecyclerView recyclerView ;
    private FavoriteMoviesAdapter adapter ;
    private AppDatabase database ;
    private MovieListViewModel viewModel ;
    private MovieDetailsViewModel movieDetailsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment , container , false );
        recyclerView = container.findViewById(R.id.moviesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 3 ));
        adapter = new FavoriteMoviesAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        database = AppDatabase.getInstance(getActivity().getApplicationContext());

        setUpViewModel();







        return view;
    }


    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(MovieListViewModel.class);
        viewModel.getFavs().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                Log.d(TAG , "updating favorites from live data in view model ...") ;
                adapter.setFavs(movies);
            }
        });
    }

    @Override
    public void onFavoriteClicked(Movie movie) {
        // pass the movie  to the details fragment and start transmission ,,.
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
