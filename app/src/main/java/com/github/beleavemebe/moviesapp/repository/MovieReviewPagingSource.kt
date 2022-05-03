package com.github.beleavemebe.moviesapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.beleavemebe.moviesapp.apiclient.ApiConstants
import com.github.beleavemebe.moviesapp.apiclient.NyTimesService
import com.github.beleavemebe.moviesapp.model.MovieReview
import com.github.beleavemebe.moviesapp.utils.log
import javax.inject.Inject

class MovieReviewPagingSource @Inject constructor(
    private val nyTimesService: NyTimesService
) : PagingSource<Int, MovieReview>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(ApiConstants.PAGE_SIZE) ?: page.nextKey?.minus(ApiConstants.PAGE_SIZE)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
        return try {
            val currKey = params.key ?: ApiConstants.FIRST_PAGE
            val response = nyTimesService.getAllMovies(currKey)
            val movies = response.movieReviews

            val prevKey = currKey.takeUnless {
                    it == ApiConstants.FIRST_PAGE
                }
                ?.minus(ApiConstants.PAGE_SIZE)

            val nextKey = if (!response.hasMore) {
                null
            } else  {
                currKey + ApiConstants.PAGE_SIZE
            }

            log("currKey $currKey, prevKey $prevKey, nextKey $nextKey")

            LoadResult.Page(movies, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
