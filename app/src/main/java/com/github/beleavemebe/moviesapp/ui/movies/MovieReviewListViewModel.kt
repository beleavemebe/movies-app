package com.github.beleavemebe.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.beleavemebe.moviesapp.repository.MovieReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieReviewListViewModel @Inject constructor(
    movieReviewRepository: MovieReviewRepository,
) : ViewModel() {
    val content = movieReviewRepository.getMovieReviews()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}
