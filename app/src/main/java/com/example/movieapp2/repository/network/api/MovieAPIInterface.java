package com.example.movieapp2.repository.network.api;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.repository.storage.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIInterface {



    @GET("popular/")
    Call<ArrayList<Movie>> getMovies(@Query(Constants.API_KEY_LABEL) String apiKey,
                                     @Query(Constants.PAGE_PARAMETER) int page);


}
