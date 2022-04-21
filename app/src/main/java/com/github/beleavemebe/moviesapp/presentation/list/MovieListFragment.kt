package com.github.beleavemebe.moviesapp.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)
    }
}
