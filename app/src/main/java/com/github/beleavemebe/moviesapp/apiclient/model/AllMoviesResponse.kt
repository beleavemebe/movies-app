package com.github.beleavemebe.moviesapp.apiclient.model

import com.github.beleavemebe.moviesapp.model.MovieReview
import com.google.gson.annotations.SerializedName

class AllMoviesResponse(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val movieReviews: List<MovieReview>
)
