package com.github.beleavemebe.moviesapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.beleavemebe.moviesapp.apiclient.ApiConstants
import com.github.beleavemebe.moviesapp.apiclient.NyTimesService
import com.github.beleavemebe.moviesapp.model.MovieReview
import retrofit2.HttpException
import javax.inject.Inject

class MovieReviewPagingSource @Inject constructor(
    private val nyTimesService: NyTimesService
) : PagingSource<Int, MovieReview>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
        return try {
            val currPage = params.key ?: ApiConstants.FIRST_PAGE
            val response = nyTimesService.getAllMovies(currPage)
            val movies = response.movieReviews

            val prevKey = params.key.takeUnless { it == ApiConstants.FIRST_PAGE }
            val nextKey = if (!response.hasMore) {
                null
            } else if (prevKey != null) {
                prevKey + ApiConstants.PAGE_SIZE
            } else {
                ApiConstants.FIRST_PAGE
            }

            LoadResult.Page(movies, prevKey, nextKey)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
