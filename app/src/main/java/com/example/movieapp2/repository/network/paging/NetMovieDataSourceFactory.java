package com.example.movieapp2.repository.network.paging;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.movieapp2.repository.storage.model.Movie;
import rx.subjects.ReplaySubject;

public class NetMovieDataSourceFactory extends DataSource.Factory{

    private static final String TAG = NetMovieDataSourceFactory.class.getSimpleName();
    private MutableLiveData<NetMoviePageKeyedDataSource> networkStatus ;
    private NetMoviePageKeyedDataSource moviePageKeyedDataSource ;

    public NetMovieDataSourceFactory(){
        this.networkStatus = new MutableLiveData<>() ;
        moviePageKeyedDataSource = new NetMoviePageKeyedDataSource() ;
    }

    @Override
    public DataSource create() {
        networkStatus.postValue(moviePageKeyedDataSource);
        return moviePageKeyedDataSource;
    }

    public MutableLiveData<NetMoviePageKeyedDataSource> getNetworkStatus() {
        return networkStatus ;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviePageKeyedDataSource.getMovies() ;
    }


}
