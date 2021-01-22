package com.example.movieapp2.ui.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.movieapp2.repository.storage.AppDatabase;
import com.example.movieapp2.repository.storage.model.Movie;
import com.example.movieapp2.utils.AppExecutors;
import com.example.movieapp2.utils.Constants;
import com.example.movieapp2.R;
import com.example.movieapp2.ui.viewmodel.MovieDetailsViewModel;
import com.example.movieapp2.databinding.MovieDetailsFragmentBinding;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {
    private static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private MovieDetailsViewModel viewModel ;
    private  Movie movie  ;
    private ImageView favImg ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MovieDetailsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getMovie().observe(this, binding::setMovie);
        favImg = (ImageView) view.findViewById(R.id.fav_image);
        if(viewModel.getMovie().getValue() != null) {
            movie = viewModel.getMovie().getValue() ;
            if(movie.isFavorite()) {
                favImg.setImageDrawable(getActivity().getDrawable(R.drawable.ic_favorite_black_24dp));
            }else {
                favImg.setImageDrawable(getActivity().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            }
        } else {
            Log.e(TAG , "movie passed to details is null ...") ;
        }

        // set the add fave
        favImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(movie.isFavorite()) {
                            movie.setFavorite(false);
                            AppDatabase.getInstance(getActivity().getApplicationContext()).movieDao().update(movie);
                            favImg.setImageDrawable(getActivity().getDrawable(R.drawable.ic_favorite_border_black_24dp));
                        }else{
                            movie.setFavorite(true);
                            AppDatabase.getInstance(getActivity().getApplicationContext()).movieDao().update(movie);
                            favImg.setImageDrawable(getActivity().getDrawable(R.drawable.ic_favorite_black_24dp));

                        }

                    }
                });
            }
        });

        // (TODO) 2-- Adding tutorials bar ...

        return view;
    }







    public static void setImageUrl(ImageView view  , String url ) {
        if(url!=null){
            Picasso.get().load(Constants.BIG_IMAGE_URL_PREFIX + url).into(view) ;
        }
    }
}
