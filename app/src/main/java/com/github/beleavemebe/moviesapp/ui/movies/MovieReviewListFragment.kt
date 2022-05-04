package com.github.beleavemebe.moviesapp.ui.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieReviewListBinding
import com.github.beleavemebe.moviesapp.ui.movies.MovieReviewListSideEffect.*
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
        MovieReviewAdapter(viewModel::onReviewClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieReviewListBinding.bind(view)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initSwipeRefreshLayout()
        subscribeToLoadState()
        subscribeToViewModel()
    }

    private fun initRecyclerView() {
        binding.rvMovieReviews.adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = createLoadStateAdapter(),
                footer = createLoadStateAdapter()
            )
    }

    private fun createLoadStateAdapter() =
        MovieReviewLoadStateAdapter {
            viewModel.retry()
        }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun subscribeToLoadState() {
        adapter.addLoadStateListener { state ->
            val isLoading = state.refresh is LoadState.Loading
            viewModel.onLoadingState(isLoading)

            val isError = state.refresh is LoadState.Error
            if (isError) {
                viewModel.onDataLoadError()
            }
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
        binding.swipeRefreshLayout.isRefreshing = state.isLoading
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(state.pagingData)
        }
    }

    private fun handleSideEffect(sideEffect: MovieReviewListSideEffect) =
        when (sideEffect) {
            is RetryLoading -> retryLoading()
            is ShowRetryLoadingSnackbar -> showRetryLoadingSnackbar()
            is TriggerRefresh -> triggerRefresh()
            is OpenMovieReview -> openReview(sideEffect.movieReview.link.url)
            is ShowUnableToOpenTheLinkToast -> showUnableToOpenTheLinkToast()
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

    private fun triggerRefresh() {
        adapter.refresh()
    }
    
    private fun openReview(reviewUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reviewUrl))
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            requireContext().startActivity(intent)
        } else {
            viewModel.onOpenReviewFailed()
        }
    }

    private fun showUnableToOpenTheLinkToast() {
        Toast.makeText(
            requireContext(),
            R.string.unable_to_open_the_link,
            Toast.LENGTH_SHORT
        ).show()
    }
}
