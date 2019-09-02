package com.example.movieapp2.repository.network.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.repository.network.api.MovieAPIClient;
import com.example.movieapp2.repository.network.api.MovieAPIInterface;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.repository.storage.model.NetworkState;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;


public class NetMoviePageKeyedDataSource extends PageKeyedDataSource<String, Movie> {

    private static final String TAG = NetMoviePageKeyedDataSource.class.getSimpleName();
    private final MovieAPIInterface movieService ;
    private final MutableLiveData networkState;
    private final ReplaySubject<Movie> movieObservable ;


    NetMoviePageKeyedDataSource(){
        movieService  = MovieAPIClient.getClient() ;
        networkState = new MutableLiveData() ;
        movieObservable = ReplaySubject.create() ;
    }

    public MutableLiveData getNetworkState() { return networkState ; }

    public ReplaySubject<Movie> getMovies() {return movieObservable ; }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Movie> callback) {
        Log.i(TAG , "Loading initial Data , Count : " + params.requestedLoadSize);
        networkState.postValue(NetworkState.LOADING);
        Call<ArrayList<Movie>> callBack = movieService.getMovies(Constants.API_KEY, 1);
        callBack.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if(response.isSuccessful()){
                    callback.onResult(response.body(),Integer.toString(1),Integer.toString(2));
                    networkState.postValue(NetworkState.LOADED);
                    for (Movie movie : response.body()) {
                        movieObservable.onNext(movie);
                    }
                } else{
                    Log.e("API CALL", response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED,response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
            String errorMessage ;
            if(t.getMessage() == null){
                errorMessage = "unknown error" ;
            }else{
                errorMessage = t.getMessage() ;
            }
            networkState.postValue(new NetworkState(NetworkState.Status.FAILED , errorMessage));
            callback.onResult(new ArrayList<>(), Integer.toString(1) , Integer.toString(2));
            }
        });

    }


    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        Log.i(TAG , "Loading page" + params.key);
        networkState.postValue(NetworkState.LOADING);
        final AtomicInteger page = new AtomicInteger(0) ;
        try{
           page.set(Integer.parseInt(params.key));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Call<ArrayList<Movie>> callBack = movieService.getMovies(Constants.API_KEY,page.get());
        callBack.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if(response.isSuccessful()){
                    callback.onResult(response.body(),Integer.toString(page.get()+1));
                    networkState.postValue(NetworkState.LOADED);
                    for (Movie movie : response.body()) {
                        movieObservable.onNext(movie);
                    }
                }else{
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED,response.message()));
                    Log.e("API CALL" , response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                String errorMessage ;
                if(t.getMessage() == null) {
                   errorMessage = "unknown error";
                }else{
                    errorMessage = t.getMessage() ;
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED,errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(page.get()));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

}
