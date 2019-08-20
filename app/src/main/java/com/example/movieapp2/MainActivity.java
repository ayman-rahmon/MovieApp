package com.example.movieapp2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movieapp2.adapters.MovieAdapter;
import com.example.movieapp2.model.Movie;
import com.example.movieapp2.utils.JSONUtils;
import com.example.movieapp2.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickedListener {



    private static final String TAG = MainActivity.class.getSimpleName() ;


    static ProgressBar progressBar ;
    static RecyclerView listView ;


    private static MovieAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        listView = findViewById(R.id.recycler_view);

        adapter = new MovieAdapter(this, this );

        listView.setLayoutManager(new GridLayoutManager(this,3));
        listView.setAdapter(adapter);



        new DownloadMovies().execute();

    }

    @Override
    public void onMovieClicked(Movie clickedItem) {
    // movie clicked ...
        // start details activity...
        Intent startDetails = new Intent(MainActivity.this, MovieDetails.class);
        startDetails.putExtra(Constants.INTENT_MOVIE_ID , clickedItem.getId());
        Log.d("IDADAPTER",": " + clickedItem.getId());
        startDetails.putExtra(Constants.INTENT_ORIGINAL_FILM_NAME,clickedItem.getOriginalTitle());
        startDetails.putExtra(Constants.INTENT_MOVIE_POSTER , clickedItem.getPoster_path());
        startDetails.putExtra(Constants.INTENT_OVERVIEW , clickedItem.getOverview());
        startDetails.putExtra(Constants.INTENT_VOTE_AVARAGE ,clickedItem.getVoteAvarage());
        startDetails.putExtra(Constants.INTENT_MOVIE_BACKGROUND , clickedItem.getBackdropPath());
        startDetails.putExtra(Constants.INTENT_RELEASE_DATE,clickedItem.getReleaseDate());
        startActivity(startDetails);
    }


    static class DownloadMovies extends AsyncTask<Void,Void,ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            movies.clear();
            listView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected ArrayList<Movie>  doInBackground(Void... voids) {

            URL popularMoviesApp = NetworkUtils.getMoviesURL();
            ArrayList<Movie> movies = null;

            try {
                HttpURLConnection conn = (HttpURLConnection)popularMoviesApp.openConnection() ;
                conn.setRequestMethod("GET");
//                conn.setRequestProperty();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                int status = conn.getResponseCode();

                if(status == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer result = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        result.append(inputLine);
                    }
                    in.close();
                    Log.d("response :: ",  result.toString() );


                    // parse and store data locally...

                    JSONObject root = new JSONObject(result.toString());

                    JSONArray jsonArray = root.getJSONArray("results");
                     movies  = new ArrayList<>();
                    for(int i = 0  ; i<= jsonArray.length() ; i ++ ){

                        JSONObject jmovie = jsonArray.getJSONObject(i) ;
                        JSONUtils utils = new JSONUtils();
                        Movie movie = utils.parsingJSON(jmovie);
//                        Log.d("Movie:"," "+movie.toString());
                        movies.add(movie);
                    }
                }else {
                    Log.d("response :: ","error in the response !! OR no response ...");
                }

            } catch (IOException e) {
                Log.e(TAG ,"error downloading the data: " + e.getMessage());
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies ;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            adapter.setData(movies);
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

    }

}
