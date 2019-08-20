package com.example.movieapp2.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {



    public static final String TAG = "NetworkingError" ;

    private static final String BASE_POPULAR_MOVIE_URL = "http://api.themoviedb.org/3/movie/popular" ;
    private static final String BASE_TOP_RATED_MOVIE_URL = "http://api.themoviedb.org/3/movie/top_rated" ;
//  https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=<<api_key>>&language=en-US&page=1
    public static final String BASE_MOVIE_REVIEWS  = "https://api.themoviedb.org/3/movie" ;



    private static final String API_KEY_LABEL = "api_key";
    private static final String API_KEY = "02125890263c5f322accfbe297beef06";
    private static final String PAGE_PARAMETER = "page" ;

    public static final String POPULAR = "popular" ;
    public static final String TOP_RATED = "topRated";

    private static String requested = POPULAR;



    public static int page = 1 ;

    public static int revPage = 1 ;
public static URL getMoviesURL() {
    URL url = null;
    switch(requested) {
        case POPULAR :
            Uri  uri = Uri.parse(BASE_POPULAR_MOVIE_URL).buildUpon()
                    .appendQueryParameter(API_KEY_LABEL ,API_KEY )
                    .appendQueryParameter(PAGE_PARAMETER,String.valueOf(page)).build() ;

            try{
                url = new URL(uri.toString()) ;
            } catch (MalformedURLException e ){
                Log.e("url:", " popularMoviedURL");
            }
            Log.d("url:"," " + url.toString());
            break ;
        case TOP_RATED :
            Uri  uri2 = Uri.parse(BASE_TOP_RATED_MOVIE_URL).buildUpon().appendQueryParameter(API_KEY_LABEL ,API_KEY )
                    .appendQueryParameter(PAGE_PARAMETER,String.valueOf(page)).build() ;
            try{
                url = new URL(uri2.toString()) ;
            } catch (MalformedURLException e ){
                e.printStackTrace();
                Log.e(TAG , e.getMessage());
            }
            break ;

        default:
            Log.e("ERROR", "something went wrong");
    }
    return url ;
}


/*
    public static URL getReviewURL(String movieID) {
        URL url = null ;
        Log.d("revMovieID:" ," "+ movieID);
    String u = BASE_MOVIE_REVIEWS+"/"+movieID+"/reviews";
    Uri uri = Uri.parse(u).buildUpon()
            .appendQueryParameter(API_KEY_LABEL,API_KEY)
            .appendQueryParameter(PAGE_PARAMETER,String.valueOf(revPage)).build();

        try {
             url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG , e.getMessage());
        }
        return  url ;
    }
*/


    public static void revNextPage() {
    revPage++ ;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void setRequested(int i){
    // 1 = popular   || 2 = top rated ...

       if(i == 1 ) {
           requested = POPULAR;
       } else if (i == 2) {
           requested =TOP_RATED;
       }  else {
        Log.e("ERROR" , "wrong requested input");
       }

    }


    public static void loadNextPage() {
    page++ ;
    }

    public static void resetPages() {
    page = 1 ;
    }


}
