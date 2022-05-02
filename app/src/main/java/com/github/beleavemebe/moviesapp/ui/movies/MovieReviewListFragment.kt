package com.github.beleavemebe.moviesapp.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieReviewListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieReviewListFragment : Fragment(R.layout.fragment_movie_review_list) {
    private var _binding: FragmentMovieReviewListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieReviewListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieReviewListBinding.bind(view)
    }
}
