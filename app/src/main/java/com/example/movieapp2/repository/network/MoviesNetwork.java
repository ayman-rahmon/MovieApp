package com.example.movieapp2.repository.network;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.movieapp2.Constants;
import com.example.movieapp2.repository.network.paging.NetMovieDataSourceFactory;
import com.example.movieapp2.repository.network.paging.NetMoviePageKeyedDataSource;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MoviesNetwork {

    private static final String TAG =  MoviesNetwork.class.getSimpleName() ;
    private final LiveData<PagedList<Movie>> moviesPaged ;
    private final LiveData<NetworkState> networkState;

public MoviesNetwork(NetMovieDataSourceFactory dataSourceFactory , PagedList.BoundaryCallback<Movie> boundryCallback) {
    PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
            .setInitialLoadSizeHint(Constants.LOADING_PAGE_SIZE).setPageSize(Constants.LOADING_PAGE_SIZE).build() ;
    networkState = Transformations.switchMap(dataSourceFactory.getNetworkStatus(),
            (Function<NetMoviePageKeyedDataSource, LiveData<NetworkState>>)
                    NetMoviePageKeyedDataSource::getNetworkState);

    Executor executor = Executors.newFixedThreadPool(Constants.NUMBERS_OF_THREADS);
    LivePagedListBuilder livePAgedListBuilder = new LivePagedListBuilder(dataSourceFactory,pagedListConfig);
    moviesPaged = livePAgedListBuilder.setFetchExecutor(executor).setBoundaryCallback(boundryCallback).build() ;


}


public LiveData<PagedList<Movie>> getPagedMovies() { return moviesPaged  ; }

public LiveData<NetworkState> getNetworkState(){return networkState ; }



}
