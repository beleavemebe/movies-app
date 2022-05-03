package com.github.beleavemebe.moviesapp.ui.movies

sealed class MovieReviewListSideEffect {
    object RetryLoading : MovieReviewListSideEffect()
    object ShowRetryLoadingSnackbar : MovieReviewListSideEffect()
}
