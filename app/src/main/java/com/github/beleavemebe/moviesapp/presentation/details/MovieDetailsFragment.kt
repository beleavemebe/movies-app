package com.github.beleavemebe.moviesapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)
    }
}
