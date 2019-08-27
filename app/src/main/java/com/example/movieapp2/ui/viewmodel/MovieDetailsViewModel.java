package com.example.movieapp2.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.movieapp2.repository.storage.model.Movie;

public class MovieDetailsViewModel extends ViewModel {


    private final MutableLiveData movie ;

    public MovieDetailsViewModel(){
        movie = new MutableLiveData() ;
    }


    public MutableLiveData<Movie> getMovie() {
        return movie ;
    }

}
