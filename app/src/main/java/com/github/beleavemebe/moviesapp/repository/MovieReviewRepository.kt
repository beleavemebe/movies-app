package com.github.beleavemebe.moviesapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.beleavemebe.moviesapp.apiclient.ApiConstants
import com.github.beleavemebe.moviesapp.model.MovieReview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Provider

class MovieReviewRepository @Inject constructor(
    private val pagingSourceProvider: Provider<out MovieReviewPagingSource>,
) {
    fun getMovieReviews(): Flow<PagingData<MovieReview>> = Pager(
        config = PagingConfig(
            pageSize = ApiConstants.PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            maxSize = MAX_SIZE,
        ),
        initialKey = 0,
        pagingSourceFactory = { pagingSourceProvider.get() }
    ).flow

    companion object {
        const val PREFETCH_DISTANCE = 5
        const val MAX_SIZE = 3 * ApiConstants.PAGE_SIZE
    }
}
