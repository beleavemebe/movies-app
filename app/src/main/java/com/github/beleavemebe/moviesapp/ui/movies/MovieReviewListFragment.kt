package com.github.beleavemebe.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieReviewListBinding
import com.github.beleavemebe.moviesapp.ui.movies.recycler.MovieReviewAdapter
import com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate.MovieReviewLoadStateAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MovieReviewListFragment : Fragment(R.layout.fragment_movie_review_list) {
    private var _binding: FragmentMovieReviewListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieReviewListViewModel by viewModels()

    private val adapter by lazy {
        MovieReviewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieReviewListBinding.bind(view)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initSwipeRefreshLayout()
        subscribeToViewModel()
        subscribeToLoadState()
    }

    private fun initRecyclerView() {
        binding.rvMovieReviews.adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = MovieReviewLoadStateAdapter(),
                footer = MovieReviewLoadStateAdapter { viewModel.retry() }
            )
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.retry()
        }
    }

    private fun subscribeToViewModel() {
        viewModel.observe(
            viewLifecycleOwner,
            ::renderState,
            ::handleSideEffect,
        )
    }

    private fun renderState(state: MovieReviewListState) {
        binding.circularProgress.isVisible = state.isLoading
        binding.swipeRefreshLayout.isRefreshing = state.isLoading
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(state.pagingData)
        }
    }

    private fun handleSideEffect(sideEffect: MovieReviewListSideEffect) =
        when (sideEffect) {
            is MovieReviewListSideEffect.RetryLoading -> retryLoading()
            is MovieReviewListSideEffect.ShowRetryLoadingSnackbar -> showRetryLoadingSnackbar()
        }

    private fun retryLoading() {
        adapter.retry()
    }

    private fun showRetryLoadingSnackbar() {
        Snackbar.make(
            binding.root,
            R.string.could_not_load_data,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.retry) {
            viewModel.retry()
        }.show()
    }

    private fun subscribeToLoadState() {
        adapter.addLoadStateListener { state ->
            viewModel.onLoadingState(state.refresh == LoadState.Loading)
            if (state.refresh is LoadState.Error) {
                viewModel.onDataLoadError()
            }
        }
    }
}
