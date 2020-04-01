package com.doiliomatsinhe.popularmovies.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.doiliomatsinhe.popularmovies.R;
import com.doiliomatsinhe.popularmovies.adapter.MovieAdapter;
import com.doiliomatsinhe.popularmovies.databinding.ActivityMainBinding;
import com.doiliomatsinhe.popularmovies.model.Movie;
import com.doiliomatsinhe.popularmovies.model.MovieRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private MainViewModel viewModel;
    private MovieAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initComponentes();

        initAdapter();

        decideWhatToShow();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String filter;
        switch (item.getItemId()) {
            case R.id.ic_popular:
                filter = getString(R.string.popular_filter);
                recoverMovies(filter);
                break;
            case R.id.ic_rating:
                filter = getString(R.string.top_rated_filter);
                recoverMovies(filter);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void recoverMovies(String category) {
        binding.swipeRefresh.setRefreshing(true);
        viewModel.setFilter(category);
        viewModel.getMovies(category).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovieList(movies);
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void initAdapter() {
        adapter = new MovieAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerMovies.setLayoutManager(layoutManager);
        binding.recyclerMovies.setHasFixedSize(true);
        binding.recyclerMovies.setAdapter(adapter);
    }

    private void initComponentes() {

        binding.swipeRefresh.setOnRefreshListener(this);

        // Initializing the repository, factory and the ViewModel
        MovieRepository repository = new MovieRepository(getString(R.string.api_key));
        MainViewModelFactory factory = new MainViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @Override
    public void onRefresh() {
        decideWhatToShow();
    }

    private void decideWhatToShow() {
        if (viewModel.getFilter() == null) {
            viewModel.setFilter(getString(R.string.popular_filter));
            recoverMovies(viewModel.getFilter());
        } else {
            recoverMovies(viewModel.getFilter());
        }
        if (viewModel.getFilter().equalsIgnoreCase(getString(R.string.popular_filter))) {
            recoverMovies(getString(R.string.popular_filter));
        } else {
            recoverMovies(getString(R.string.top_rated_filter));
        }
    }
}
