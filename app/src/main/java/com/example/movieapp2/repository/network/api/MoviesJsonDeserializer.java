package com.example.movieapp2.repository.network.api;

import android.util.Log;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.repository.storage.model.Movie;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoviesJsonDeserializer implements JsonDeserializer {

    private final String TAG = MoviesJsonDeserializer.class.getSimpleName() ;

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Movie> movies = null ;

        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray moviesJsonArray = jsonObject.getAsJsonArray(Constants.MOVIES_ARRAY_DATA_TAG);
            movies = new ArrayList<>(moviesJsonArray.size());
            for (int i = 0; i < moviesJsonArray.size(); i++) {
                Movie dematerialized = context.deserialize(moviesJsonArray.get(i), Movie.class);
                movies.add(dematerialized);

            }
        }catch (JsonParseException e){
            e.printStackTrace();
            Log.e(TAG , "Failed parsing object");
            return null ;
        }

        return movies;
    }



}
