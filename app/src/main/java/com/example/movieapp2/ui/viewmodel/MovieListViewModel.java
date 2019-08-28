package com.example.movieapp2.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.movieapp2.repository.MovieRepository;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;

public class MovieListViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);

    }


    public LiveData<PagedList<Movie>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

}
