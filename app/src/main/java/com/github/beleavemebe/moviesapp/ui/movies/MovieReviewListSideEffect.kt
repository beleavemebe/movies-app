package com.github.beleavemebe.moviesapp.ui.movies

import com.github.beleavemebe.moviesapp.model.MovieReview

sealed class MovieReviewListSideEffect {
    object RetryLoading : MovieReviewListSideEffect()
    object ShowRetryLoadingSnackbar : MovieReviewListSideEffect()
    object TriggerRefresh : MovieReviewListSideEffect()
    data class OpenMovieReview(val movieReview: MovieReview) : MovieReviewListSideEffect()
    object ShowUnableToOpenTheLinkToast : MovieReviewListSideEffect()
}
