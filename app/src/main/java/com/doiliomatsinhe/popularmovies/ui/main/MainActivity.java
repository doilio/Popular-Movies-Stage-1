package com.doiliomatsinhe.popularmovies.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.doiliomatsinhe.popularmovies.R;
import com.doiliomatsinhe.popularmovies.model.Movie;
import com.doiliomatsinhe.popularmovies.model.MovieRepository;
import com.doiliomatsinhe.popularmovies.model.MovieResponse;
import com.doiliomatsinhe.popularmovies.network.APIService;
import com.doiliomatsinhe.popularmovies.network.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String CATEGORY = "popular";
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainViewModel viewModel;
    private MainViewModelFactory factory;
    private MovieRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponentes();

        viewModel.getMovies(CATEGORY);
    }

    private void initComponentes() {

        // Initializing the repository, factory and the ViewModel
        repository = new MovieRepository();
        factory = new MainViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

}
