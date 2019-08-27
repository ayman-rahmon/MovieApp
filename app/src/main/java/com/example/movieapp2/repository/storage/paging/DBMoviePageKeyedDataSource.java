package com.example.movieapp2.repository.storage.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.movieapp2.repository.storage.MovieDAO;
import com.example.movieapp2.repository.storage.model.Movie;

import java.util.List;

public class DBMoviePageKeyedDataSource extends PageKeyedDataSource<String , Movie> {

    public static final String TAG =  DBMoviePageKeyedDataSource.class.getSimpleName() ;
    private final MovieDAO movieDAO ;

    public DBMoviePageKeyedDataSource(MovieDAO movieDAO) {this.movieDAO = movieDAO ; }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {
        Log.i(TAG , "Loading Intial Rang , Count " + params.requestedLoadSize ) ;
        List<Movie> movies = movieDAO.getMovies() ;
        if(movies.size() != 0 ){
            callback.onResult(movies,Integer.toString(0),Integer.toString(1));
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }
}
