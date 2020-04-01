package com.doiliomatsinhe.popularmovies.network;

import com.doiliomatsinhe.popularmovies.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    // https://api.themoviedb.org/3/movie/popular?api_key=64cea8d8a458dde2d8361705d61f6a9a&language=en-US&page=1
    @GET("/3/movie/{category}")
    Call<MovieResponse> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);
}
