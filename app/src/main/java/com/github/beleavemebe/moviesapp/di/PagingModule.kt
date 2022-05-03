package com.github.beleavemebe.moviesapp.di

import androidx.paging.PagingConfig
import com.github.beleavemebe.moviesapp.apiclient.ApiConstants
import com.github.beleavemebe.moviesapp.repository.MovieReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @Provides
    fun providePagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = ApiConstants.PAGE_SIZE,
            prefetchDistance = MovieReviewRepository.PREFETCH_DISTANCE,
            maxSize = MovieReviewRepository.MAX_SIZE,
        )
    }
}
