package com.example.movieapp2.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.paging.PagedList;
import android.content.Context;
import android.util.Log;

import com.example.movieapp2.repository.network.MoviesNetwork;
import com.example.movieapp2.repository.network.paging.NetMovieDataSourceFactory;
import com.example.movieapp2.repository.storage.AppDatabase;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;

import rx.schedulers.Schedulers;

public class MovieRepository {

    private static final String TAG = MovieRepository.class.getSimpleName() ;
    private static MovieRepository instance ;
    private final MoviesNetwork network ;
    private final AppDatabase database ;
    private final MediatorLiveData liveDataMerger;


    private MovieRepository(Context context){
        NetMovieDataSourceFactory dataSourceFactory = new NetMovieDataSourceFactory() ;
        network = new MoviesNetwork(dataSourceFactory, boundryCallback);
        database = AppDatabase.getInstance(context.getApplicationContext());
        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(network.getPagedMovies(), value -> {
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        // save the movies in the database ...
        dataSourceFactory.getMovies().observeOn(Schedulers.io()).subscribe(movie -> {
            database.movieDao().insertMovie(movie);
        });
    }

    private PagedList.BoundaryCallback boundryCallback = new PagedList.BoundaryCallback() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getMovies(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getMovies());
            });
        }
    };


    public static MovieRepository getInstance(Context context){
        if(instance == null){
            instance = new MovieRepository(context);
        }
        return instance;
    }


    public LiveData<PagedList<Movie>> getMovies() {return liveDataMerger;}

    public LiveData<NetworkState> getNetworkState() {
        return network.getNetworkState() ;
    }

}
