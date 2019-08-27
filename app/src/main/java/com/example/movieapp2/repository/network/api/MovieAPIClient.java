package com.example.movieapp2.repository.network.api;


import com.example.movieapp2.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieAPIClient {


    private static String requestedArrangement = Constants.BASE_POPULAR_MOVIE_URL ;

public static MovieAPIInterface getInstance() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    // create okhttpClient and register an interceptor ...
    OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

    Gson gson = new GsonBuilder().registerTypeAdapter(Constants.MOVIE_ARRAY_LIST_CLASS_TYPE, new MoviesJsonDeserializer()).create();


    Retrofit.Builder builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(client).baseUrl(requestedArrangement);

return builder.build().create(MovieAPIInterface.class);
}


public void setRequestedArrangement(String arrangement) {
this.requestedArrangement  = arrangement ;
}



}
