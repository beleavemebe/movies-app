package com.github.beleavemebe.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieReviewListBinding
import com.github.beleavemebe.moviesapp.ui.movies.recycler.MovieReviewAdapter
import com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate.MovieReviewLoadStateAdapter
import com.github.beleavemebe.moviesapp.utils.repeatWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

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
        initRecyclerView()
        subscribeToViewModel()
    }

    private fun initRecyclerView() {
        binding.rvMovieReviews.adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = MovieReviewLoadStateAdapter(),
                footer = MovieReviewLoadStateAdapter()
            )
    }

    private fun subscribeToViewModel() {
        viewModel.content
            .onEach(adapter::submitData)
            .repeatWhenStarted(viewLifecycleOwner.lifecycle)
    }
}
