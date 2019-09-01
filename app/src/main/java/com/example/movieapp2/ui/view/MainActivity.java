package com.example.movieapp2.ui.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.movieapp2.R;
import com.example.movieapp2.ui.view.fragments.FavoritesFragment;
import com.example.movieapp2.ui.view.fragments.MovieListFragment;
import com.example.movieapp2.ui.adapters.TabAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private static final String TAG = MainActivity.class.getSimpleName() ;
    private DrawerLayout drawerLayout ;
    private Toolbar toolbar ;
    private TabLayout tabLayout ;
    private TabAdapter tabAdapter ;
    private ViewPager viewPager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpToolbar();
        setUpTabsLayout() ;
        setUpDrawer();
        // setting up the movie list fragment ....
/*
        if(findViewById(R.id.fragmentsContainer)!= null) {
            if(savedInstanceState != null ){
                return  ;
            }
            MovieListFragment listFragment = new MovieListFragment() ;
            listFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentsContainer,listFragment).commit();
        }
*/
}

    private void setUpDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    private void setUpToolbar() {
    toolbar = (Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setTitle(getString(R.string.app_name));
    toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }


    private void setUpTabsLayout() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabAdapter =  new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new MovieListFragment(),getString(R.string.movie_list_fragment_ttitle));
        tabAdapter.addFragment(new FavoritesFragment() , getString(R.string.favorite_fragment));
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.popular_movies :
                // change the url to popular movies
                Log.d(TAG , "popular movies clicked ");
                return true ;
            case R.id.most_rated :
                // change the url to most rated ...
                Log.d(TAG , "most rated clicked ");
                return true ;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId() ;

        switch(id) {
            case R.id.nav_camera :
                Toast.makeText(this,"home drawer item selected ...",Toast.LENGTH_SHORT).show();
            break ;

            default :
                Toast.makeText(this,"selected something else  ...",Toast.LENGTH_SHORT).show();
            break  ;
        }

        // close down the drawer layout ...
        drawerLayout.closeDrawer(Gravity.START);

        return true;
    }
}

