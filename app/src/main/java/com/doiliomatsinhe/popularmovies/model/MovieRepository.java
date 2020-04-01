package com.doiliomatsinhe.popularmovies.model;


import com.doiliomatsinhe.popularmovies.network.APIService;
import com.doiliomatsinhe.popularmovies.network.ServiceBuilder;

import retrofit2.Call;

public class MovieRepository {

    private static final String API_KEY = "";
    private static final String LANGUAGE = "en-US";
    private static final int PAGE = 1;

    private static APIService service = ServiceBuilder.BuildService(APIService.class);

    public Call<MovieResponse> getMovies(String categoria) {

        return service.getMovies(categoria, API_KEY, LANGUAGE, PAGE);
    }

}
