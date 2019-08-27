package com.example.movieapp2.repository.storage.paging;

import android.arch.paging.DataSource;

import com.example.movieapp2.repository.storage.MovieDAO;

public class DBMoviesPageKeyedDataFactory extends DataSource.Factory {

    private static final String TAG = DBMoviesPageKeyedDataFactory.class.getSimpleName() ;
    private DBMoviePageKeyedDataSource moviePageKeyedDataSource ;



    public DBMoviesPageKeyedDataFactory(MovieDAO movieDAO) {
        moviePageKeyedDataSource = new DBMoviePageKeyedDataSource(movieDAO);
    }

    @Override
    public DataSource create() {
        return moviePageKeyedDataSource;
    }
}
