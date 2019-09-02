package com.example.movieapp2.repository.storage;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.movieapp2.repository.storage.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    /**
     * Get the Movies from the table.
     * -------------------------------
     * Since the DB use as caching, we don't return LiveData.
     * We don't need to get update every time the database update.
     * We using the get query when application start. So, we able to display
     * data fast and in case we don't have connection to work offline.
     * @return the movies from the table
     */
   @Query("SELECT * FROM movies")
    List<Movie> getMovies() ;


   @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie) ;



   // delete ... delete cache database ...()
    //TODO (1) delete everything in cache database except FAVS ...
   @Query("DELETE FROM movies")
   abstract void deleteAllMovies()  ;


    @Query("select * from movies where is_favorite = 1 ")
   LiveData<List<Movie>> loadFavorites() ;


    @Query("select * from movies where id = :id")
    LiveData<Movie> loadMovieByID(int id);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Movie updatedMovie) ;




}
