package com.github.beleavemebe.moviesapp.ui.movies

import androidx.paging.PagingData
import com.github.beleavemebe.moviesapp.model.MovieReview
import com.github.beleavemebe.moviesapp.repository.MovieReviewRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
class MovieReviewListViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `on item clicked, post 'open review' side effect`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val repository = mockk<MovieReviewRepository>()
            every { repository.getMovieReviews() } returns flow {
                emit(PagingData.empty())
            }

            val initialState = MovieReviewListState(true)
            val subject = MovieReviewListViewModel(repository).test(initialState)
            val movieReview = mockk<MovieReview>()
            subject.runOnCreate()
            subject.testIntent {
                onReviewClicked(movieReview)
            }

            subject.assert(initialState) {
                postedSideEffects(
                    MovieReviewListSideEffect.OpenMovieReview(movieReview)
                )
            }
        }
    }
}
