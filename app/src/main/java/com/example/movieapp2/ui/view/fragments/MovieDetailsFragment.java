package com.example.movieapp2.ui.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.R;
import com.example.movieapp2.ui.viewmodel.MovieDetailsViewModel;
import com.example.movieapp2.databinding.MovieDetailsFragmentBinding;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel viewModel ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MovieDetailsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getMovie().observe(this, binding::setMovie);
        return view;
    }





    public static void setImageUrl(ImageView view  , String url ) {
        if(url!=null){
            Picasso.get().load(Constants.BIG_IMAGE_URL_PREFIX + url).into(view) ;
        }
    }
}
