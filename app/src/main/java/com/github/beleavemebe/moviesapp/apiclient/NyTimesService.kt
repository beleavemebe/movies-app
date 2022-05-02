package com.github.beleavemebe.moviesapp.apiclient

import com.github.beleavemebe.moviesapp.apiclient.model.AllMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NyTimesService {
    @GET("reviews/all.json")
    suspend fun getAllMovies(
        @Query("offset") offset: Int,
    ): AllMoviesResponse
}
