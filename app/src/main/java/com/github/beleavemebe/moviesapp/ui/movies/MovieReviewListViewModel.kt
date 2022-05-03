package com.github.beleavemebe.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.beleavemebe.moviesapp.model.MovieReview
import com.github.beleavemebe.moviesapp.repository.MovieReviewRepository
import com.github.beleavemebe.moviesapp.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MovieReviewListViewModel @Inject constructor(
    movieReviewRepository: MovieReviewRepository,
) : ViewModel(), ContainerHost<MovieReviewListState, MovieReviewListSideEffect> {

    override val container: Container<MovieReviewListState, MovieReviewListSideEffect> =
        container(MovieReviewListState(isLoading = true))

    init {
        movieReviewRepository.getMovieReviews()
            .cachedIn(viewModelScope)
            .onEach(::updateState)
            .launchIn(viewModelScope)
    }

    private fun updateState(pagingData: PagingData<MovieReview>) = intent {
        reduce {
            state.copy(
                pagingData = pagingData
            )
        }
    }

    fun onDataLoadError() = intent {
        postSideEffect(MovieReviewListSideEffect.ShowRetryLoadingSnackbar)
    }

    fun retry() = intent {
        postSideEffect(MovieReviewListSideEffect.RetryLoading)
    }

    fun onLoadingState(isLoading: Boolean) = intent {
        log { "onLoadingState $isLoading" }
        reduce {
            state.copy(isLoading = isLoading)
        }
    }
}
