package com.example.movieapp2.utils;

import android.util.Log;


import com.example.movieapp2.repository.storage.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

public static final  String TAG = "JSONError" ;

    public Movie parsingJSON(JSONObject jmovie){
        Log.d("parsing:", "parsing started! ");
        Movie movie = null ;
        try {
            // TODO (2) parse the movie object here ...
            int id  =  jmovie.getInt("id");
//               int coteCount ;
                 String poster_path = jmovie.getString("poster_path") ;
//               Double popularity ;
//               String originalLanguage ;

            String originalTitle =jmovie.getString("original_title")  ;
            String backgrounpath = jmovie.getString("backdrop_path") ;
//               boolean adult ;
            String overview = jmovie.getString("overview") ;
            String releaseDate =  jmovie.getString("release_date");
            Double voteAvarage  = jmovie.getDouble("vote_average") ;

             movie = new Movie(id,poster_path,originalTitle,backgrounpath,overview,releaseDate,voteAvarage) ;

//            Log.d("Movie:"," "+movie.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG , "JSON Exception :: " + e.getMessage());
        }
        return movie ;
    }




/*
    public static MovieReview parseReviewJSON(JSONObject jReview) {
        MovieReview  rev= null ;
        try{
            String id = jReview.optString("id");
            String author = jReview.optString("author");
            String content = jReview.optString("content");
            String url = jReview.optString("url");
            rev = new MovieReview(id, author,content,url);
        }catch(Exception e) {
            e.printStackTrace();
            Log.e("ERROR","problem parsing the review.. JSON");
        }
        return rev ;
        }
*/

}
