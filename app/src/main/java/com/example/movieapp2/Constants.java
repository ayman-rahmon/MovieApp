package com.example.movieapp2;

import com.example.movieapp2.repository.storage.model.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class Constants {

    // Network Stuff ...
    public static final String MOVIES_ARRAY_DATA_TAG = "results";
    public static final String BASE_POPULAR_MOVIE_URL = "http://api.themoviedb.org/3/movie/popular" ;
    public static final String BASE_TOP_RATED_MOVIE_URL = "http://api.themoviedb.org/3/movie/top_rated" ;
    //  https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=<<api_key>>&language=en-US&page=1
    public static final String BASE_MOVIE_REVIEWS  = "https://api.themoviedb.org/3/movie" ;
    // api ke
    public static final String API_KEY_LABEL = "api_key";
    public static final String API_KEY = "02125890263c5f322accfbe297beef06";
    public static final String PAGE_PARAMETER = "page" ;
    public static final String POPULAR = "popular" ;
    public static final String TOP_RATED = "topRated";
    public static String requested = POPULAR;
    public static final Type MOVIE_ARRAY_LIST_CLASS_TYPE = (new ArrayList<Movie>()).getClass();
    public static final int LOADING_PAGE_SIZE = 20;
    public static final int NUMBERS_OF_THREADS = 3;



    //Database
    public static final String DATABASE_NAME = "movies.db";





    // intents ... movies ...
    public static final String INTENT_MOVIE_ID = "movieID" ;
    public static final String INTENT_ORIGINAL_FILM_NAME = "originalName" ;
    public static final String INTENT_MOVIE_NAME = "movieName" ;
    public static final String INTENT_MOVIE_POSTER = "moviePoster" ;
    public static final String INTENT_MOVIE_BACKGROUND = "background" ;
    public static final String INTENT_OVERVIEW = "overView" ;
    public static final String INTENT_VOTE_AVARAGE = "avarage" ;
    public static final String INTENT_RELEASE_DATE = "releaseDate" ;
    // intents ... moview reviews ...
    public static final String INTENT_REVIEWS_ID ="ID" ;
    public static final String INTENT_REVIEW_AUTHOR = "author" ;
    public static final String INTENT_REVIEW_CONTENT = "content" ;
    public static final String INTENT_REVIEW_URL = "URL " ;

    //   links ...
    public static final String movieImage = "https://image.tmdb.org/t/p/w500" ; // IMG URL...





}
