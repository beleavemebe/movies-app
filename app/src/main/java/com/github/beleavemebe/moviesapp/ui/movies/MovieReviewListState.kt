package com.github.beleavemebe.moviesapp.ui.movies

import androidx.paging.PagingData
import com.github.beleavemebe.moviesapp.model.MovieReview

data class MovieReviewListState(
    val isLoading: Boolean,
    val pagingData: PagingData<MovieReview> = PagingData.empty()
)
