package com.example.movieapp2.repository.storage;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.movieapp2.Constants;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.paging.DBMoviePageKeyedDataSource;
import com.example.movieapp2.repository.storage.paging.DBMoviesPageKeyedDataFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.movieapp2.Constants.NUMBERS_OF_THREADS;


@Database(entities = {Movie.class},version =1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

private static AppDatabase instance ;
public abstract MovieDAO movieDao() ;

private static final Object sLock = new Object() ;
private LiveData<PagedList<Movie>> moviesPaged ;
    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, Constants.DATABASE_NAME)
                        .build();
                instance.init();

            }
            return instance;
        }
    }

    private void init() {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        DBMoviesPageKeyedDataFactory dataSourceFactory = new DBMoviesPageKeyedDataFactory(movieDao());
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        moviesPaged = livePagedListBuilder.setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return moviesPaged;
    }


}
